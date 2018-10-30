package service;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import thread.Receive_name;
import check.Table_Check;
public class Socket_service {

	public static Table_Check[] table=new Table_Check[15];
	public static Vector<Socket> socket_list=new Vector<Socket>();
	public Socket_service(){
		
		try {
			ServerSocket server = new ServerSocket(8888);
			Receive_name receive=new Receive_name(server);
			Thread namethread = new Thread(receive);
			namethread.start();
			for (int i = 0; i < 15; i++) {
				
				table[i]=new Table_Check();
				
			}
//			Table_Thread settable=new Table_Thread();
//			Thread tablethread = new Thread(settable);
//			tablethread.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		new Socket_service();
//	}

}
