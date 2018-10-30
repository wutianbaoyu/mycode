package thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

import check.Table_Check;
import service.Socket_service;

public class Client_thread implements Runnable {

	Socket socket;
	public String name;
	private DataOutputStream output;
	int num_click;// 点击后的位置
	String u_click = "";
	boolean check = false;
	private ReadUTF_Thread read;
	private int num=-1;
	private String u="first";
	public Client_thread(Socket socket) {

		this.socket = socket;
		Socket_service.socket_list.add(socket);
		read = new ReadUTF_Thread(socket);
		Thread read_thread = new Thread(read);
		read_thread.start();
		try {
			output = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {

		}
		name = read.get_Read();
	}

	@Override
	public void run() {
		while (true) {

			try {
				num_click = Integer.parseInt(read.get_Read());//桌子号
				u_click = read.get_Read();//左或右
				num = Integer.parseInt(read.get_Read());
				u = read.get_Read();

				if (u_click.equals("left")) {

					
					Socket_service.table[num_click].left = true;
					Socket_service.table[num_click].output_left=output;
					
				} else if (u_click.equals("right")) {

					Socket_service.table[num_click].right = true;
					Socket_service.table[num_click].output_right=output;

				}
				
				send_flush();
				if(num!=-1){
					if(u.equals("left")){
						
						Socket_service.table[num].left = false;
					}
					if(u.equals("right")){
						
						Socket_service.table[num].right = false;
					}
				}
				
			} catch (Exception e) {

			}
		}
	}

	private void send_flush() {

		Iterator<Socket> it = Socket_service.socket_list.iterator();

		while (it.hasNext()) {
			
			Socket sok=it.next();
			try {
				DataOutputStream out=new DataOutputStream(sok.getOutputStream());
				out.writeUTF("flush"+";"+num_click+";"+u_click+";"+num+";"+u);
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
	}

	/**
	 * @throws IOException
	 */
	// private void send_Table() throws IOException {
	// for (int i = 0; i < 15; i++) {
	//
	// input.readUTF();
	// output.writeUTF(Socket_service.table[i].left + "");
	// input.readUTF();
	// output.writeUTF(Socket_service.table[i].right + "");
	//
	// }
	// }

}
