package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

import service.Socket_service;

public class ReadUTF_Thread implements Runnable {

	int count = -1;
	public String name;// 用户名
	int num_click;// 点击后的位置
	String u_click = "";// 左还是右
	private int num = -1;// 点之前的位置
	private String u = "first";// 点之前是还是右
	private boolean whether_close = false;// 是否关闭线程判断
	String check;// 读取过来的数据
	Socket socket;// 客户端对应的socket
	public Vector<Object> msg_list = new Vector<Object>();
	private DataInputStream input;// socket对应的输出流
	private DataOutputStream output;// socket对应的输入流
	private String iconnum;

	public ReadUTF_Thread(Socket socket) {

		this.socket = socket;
		Socket_service.socket_list.add(socket);
		try {
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {

		while (!whether_close) {

			try {
				check = input.readUTF();
				if (check.equals("want_f")) {// 客户端请求刷新
					for (int i = 0; i < 15; i++) {

						output.writeUTF("table" + ";"
								+ Socket_service.table[i].left + ";" + i + ";"
								+ "left"+";"+Socket_service.table[i].left_iconNum);
						output.writeUTF("table" + ";"
								+ Socket_service.table[i].right + ";" + i + ";"
								+ "right"+";"+Socket_service.table[i].right_iconNum);
					}
				} else if (check.contains("ready")) {// 坐下
					String a[] = check.split(";");
					if (!a[3].equals("-1")) {// 将之前的置为false
						if (a[4].equals("left")) {

							Socket_service.table[Integer.parseInt(a[3])].left_ready = false;
						}
						if (a[4].equals("right")) {

							Socket_service.table[Integer.parseInt(a[3])].right_ready = false;
						}
					}
					if (a[2].equals("left")) {

						Socket_service.table[Integer.parseInt(a[1])].left_ready = true;
						if (Socket_service.table[Integer.parseInt(a[1])].right_ready == false||Socket_service.table[Integer.parseInt(a[1])].right==false) {

							output.writeUTF("red");
						} else if (Socket_service.table[Integer.parseInt(a[1])].right_ready
								&& Socket_service.table[Integer.parseInt(a[1])].right) {
							output.writeUTF("black");
							Socket_service.table[Integer.parseInt(a[1])].output_right
									.writeUTF("begin");
						}
					} else if (a[2].equals("right")) {

						Socket_service.table[Integer.parseInt(a[1])].right_ready = true;
						if (Socket_service.table[Integer.parseInt(a[1])].left_ready == false||Socket_service.table[Integer.parseInt(a[1])].left==false) {

							output.writeUTF("red");
						} else if (Socket_service.table[Integer.parseInt(a[1])].left_ready
								&& Socket_service.table[Integer.parseInt(a[1])].left) {
							output.writeUTF("black");
							Socket_service.table[Integer.parseInt(a[1])].output_left
									.writeUTF("begin");

						}
					}
				} else if (check.equals("close")) {// 客户端关闭
					
					if (u_click.equals("left")) {
						Socket_service.table[num_click].left = false;
					} else if (u_click.equals("right")) {
						Socket_service.table[num_click].right = false;
					}
					whether_close = true;
				} else if (check.contains("us")) {

					String b[] = check.split(";");
					name = b[1];
				} else if (check.contains("icon")) {

					String f[] = check.split(";");
					iconnum = f[1];
				} else if (check.contains("num_and_table")) {

					String c[] = check.split(";");
					num_click = Integer.parseInt(c[1]);
					u_click = c[2];
					num = Integer.parseInt(c[3]);
					u = c[4];
					if (num != -1) {// 将之前的座位变为无人坐
						if (u.equals("left")) {

							Socket_service.table[num].left = false;
						}
						if (u.equals("right")) {

							Socket_service.table[num].right = false;
						}
					}
					if (u_click.equals("left")) {

						Socket_service.table[num_click].left = true;
						Socket_service.table[num_click].left_iconNum=iconnum;
						Socket_service.table[num_click].output_left = output;
						if (Socket_service.table[num_click].right) {// 对方存在
							Socket_service.table[num_click].output_right
									.writeUTF("icon" + ";" + iconnum + ";"
											+ name);
							Socket_service.table[num_click].output_right
									.writeUTF("want_i");
						}

					} else if (u_click.equals("right")) {

						Socket_service.table[num_click].right = true;
						Socket_service.table[num_click].right_iconNum=iconnum;
						Socket_service.table[num_click].output_right = output;
						if (Socket_service.table[num_click].left) {
							Socket_service.table[num_click].output_left
									.writeUTF("icon" + ";" + iconnum + ";"
											+ name);
							Socket_service.table[num_click].output_left
									.writeUTF("want_i");// 得到对方的图标
						}

					}

					send_flush();

				} else if (check.contains("zou_qi")) {

					if (u_click.equals("left")) {
						Socket_service.table[num_click].output_right
								.writeUTF(check);
					}
					if (u_click.equals("right")) {
						Socket_service.table[num_click].output_left
								.writeUTF(check);
					}

				} else if (check.equals("give_i")) {

					if (u_click.equals("left")) {
						Socket_service.table[num_click].output_right
								.writeUTF("icon" + ";" + iconnum + ";" + name);
					}
					if (u_click.equals("right")) {
						Socket_service.table[num_click].output_left
								.writeUTF("icon" + ";" + iconnum + ";" + name);
					}

				} else if (check.contains("speak")) {

					if (u_click.equals("left")) {
						Socket_service.table[num_click].output_right
								.writeUTF(check);
					}
					if (u_click.equals("right")) {
						Socket_service.table[num_click].output_left
								.writeUTF(check);
					}
				} else {

					msg_list.add(check);
				}
			} catch (IOException e) {

			}
		}
	}

	private void send_flush() {

		Iterator<Socket> it = Socket_service.socket_list.iterator();

		while (it.hasNext()) {

			Socket sok = it.next();
			try {
				DataOutputStream out = new DataOutputStream(
						sok.getOutputStream());
				out.writeUTF("flush" + ";" + num_click + ";" + u_click + ";"
						+ num + ";" + u + ";" + iconnum);
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
	}

	public String get_Read() {

		count++;
		while (count >= msg_list.size())
			;
		return (String) msg_list.get(count);
	}

}
