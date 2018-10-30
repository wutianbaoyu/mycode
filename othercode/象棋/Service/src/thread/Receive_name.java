package thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Receive_name implements Runnable{

	
//	TreeMap <String, Socket> socket_name=new TreeMap <String, Socket>();
	ServerSocket server ;
	public static Vector<Socket> v=new Vector<Socket>();
	public Receive_name(ServerSocket server) {
		
		this.server = server;
	}
	public void run() {
		
		while(true){
		try {

			
			ReadUTF_Thread read = new ReadUTF_Thread(server.accept());
			Thread read_thread = new Thread(read);
			read_thread.start();
//			Client_thread client=new Client_thread(server.accept());
//			Thread t_client=new Thread(client);
//			t_client.start();
		} catch (Exception e) {
			
		}
	}
	}

}
