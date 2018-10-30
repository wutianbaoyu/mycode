package thread;

import gui.CheckerBoard;
import gui.ChecksGame;
import gui.Log_In_Window;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import qizi_zoufa.Qizi;
import check.Table_Check;

public class ReadUTF_Thread implements Runnable {

	// String check;
	// Socket socket;
	String check;
	public Vector<Object> msg_list = new Vector<Object>();
	private AudioClip audio;

	// private DataInputStream input;
	// private DataOutputStream output;

	// @Override
	// public ReadUTF_Thread(Socket socket) {
	//
	// // this.socket = socket;
	// try {
	// input = new DataInputStream(socket.getInputStream());
	// output = new DataOutputStream(socket.getOutputStream());
	// } catch (Exception e) {
	// }
	// }

	public void run() {
		//
		 while(true){
			 try {
					check=Log_In_Window.input.readUTF();
					if(check.contains("flush")){//刷新座位
						
						String a[]=check.split(";");
						if(a[2].equals("left")){
							
							Table_Check.table[Integer.parseInt(a[1]) ].left = true;
							Table_Check.table[Integer.parseInt(a[1]) ].left_iconNum = a[5];
						}
						else if(a[2].equals("right")){
							
							Table_Check.table[Integer.parseInt(a[1]) ].right = true;
							Table_Check.table[Integer.parseInt(a[1]) ].right_iconNum = a[5];
						}
						if(!a[3].equals("-1")){
							if(a[4].equals("left")){
								
								Table_Check.table[Integer.parseInt(a[3]) ].left = false;
							}
							else if(a[4].equals("right")){
								
								Table_Check.table[Integer.parseInt(a[3]) ].right = false;
							}
						}
						Table_Check.ergodic_Table();
					}
					else if(check.contains("table")){//最初启动时刷新
						String b[]=check.split(";");
						if(b[3].equals("left")){
							
							Table_Check.table[Integer.parseInt(b[2]) ].left = Boolean.parseBoolean(b[1]) ;
							Table_Check.table[Integer.parseInt(b[2]) ].left_iconNum=b[4];
						}
						else if(b[3].equals("right")){
							
							Table_Check.table[Integer.parseInt(b[2]) ].right = Boolean.parseBoolean(b[1]);
							Table_Check.table[Integer.parseInt(b[2]) ].right_iconNum=b[4];
							
						}
						if(b[2].equals("14")){
							Table_Check.ergodic_Table();
						}
					}
					else if(check.equals("red")){
						
						CheckerBoard.color="red";
						CheckerBoard.ready=true;
						ChecksGame.jLabel3.setIcon(new ImageIcon("./res/img/shuai(1).jpg"));
						
						ChecksGame.board.repaint();
					}
					else if(check.equals("black")){
						
						Table_Check.game_star=true;
						ChecksGame.board.guize=new Qizi();
						ChecksGame.board.chess_able=false;
						CheckerBoard.color="black";
						CheckerBoard.ready=true;	
						ChecksGame.jButton1.setEnabled(true);
						ChecksGame.jLabel3.setIcon(new ImageIcon("./res/img/jiang.jpg"));
						ChecksGame.board.repaint();
						File f = new File("./res/audio/begin.wav");
						audio = Applet.newAudioClip(f.toURI().toURL());
						audio.play();
					}
					else if(check.equals("begin")){
						
						ChecksGame.board.chess_able=true;//开始后才能走棋
						ChecksGame.board.guize=new Qizi();
						JOptionPane.showMessageDialog(null, "游戏开始",
								"提示", JOptionPane.INFORMATION_MESSAGE);
						Table_Check.game_star=true;//不能再移动座位
						ChecksGame.jButton1.setEnabled(true);
						File f = new File("./res/audio/begin.wav");
						audio = Applet.newAudioClip(f.toURI().toURL());
						audio.play();
					}
					else if(check.contains("zou_qi")){
						
						String c[]= check.split(";");
						int qizi=ChecksGame.board.pos[Integer.parseInt(c[1])][Integer.parseInt(c[2])];
						ChecksGame.board.pos[Integer.parseInt(c[1])][Integer.parseInt(c[2])] = ChecksGame.board.pos[Integer.parseInt(c[3])][Integer.parseInt(c[4])];
						if(qizi==5||qizi==21){
							File f = new File("./res/audio/Round_End_Lose_BL.wav");
							audio = Applet.newAudioClip(f.toURI().toURL());
							audio.play();
							JOptionPane.showMessageDialog(null, "你输了",
									"提示", JOptionPane.INFORMATION_MESSAGE);
						}
						if(qizi==2||qizi==8||qizi==18||qizi==24){
							
							File f = new File("./res/audio/ma_bei_eat.wav");
							audio = Applet.newAudioClip(f.toURI().toURL());
							audio.play();
						}
						ChecksGame.board.pos[Integer.parseInt(c[3])][Integer.parseInt(c[4])]=0;
						ChecksGame.board.x2 = 12 + Integer.parseInt(c[4]) * 48;//显示轨迹，走之前的地方
						ChecksGame.board.y2 = 10 + Integer.parseInt(c[3]) * 48;//显示轨迹
						ChecksGame.board.x1 = 12 + Integer.parseInt(c[2]) * 48;//显示轨迹,走了之后的地方
						ChecksGame.board.y1 = 10 + Integer.parseInt(c[1]) * 48;//显示轨迹
						ChecksGame.board.checkselect = true;
						ChecksGame.board.repaint();
						ChecksGame.board.chess_able=true;
						
					}
					else if(check.contains("icon")){
						
						String f[]= check.split(";");
						ImageIcon a = new ImageIcon("./res/face/" + f[1] + "-"
								+ "1.gif");
						try {
							Thread.sleep(100);//延时
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ChecksGame.jLabel1.setIcon(a);
						ChecksGame.jLabel1.setText(f[2]);
						
					}
					else if(check.equals("want_i")){
						
						Log_In_Window.output.writeUTF("give_i");
					}
					else if(check.contains("speak")){
						
						String f[]= check.split(";");
						ChecksGame.jTextArea2.append(f[1]);
						
					}
					else{
						
						msg_list.add(check);
					}
				} catch (IOException e) {

				}
			}
	}
}
