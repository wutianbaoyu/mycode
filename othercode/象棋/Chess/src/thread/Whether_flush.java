package thread;

import gui.GameHall;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import check.Table_Check;

public class Whether_flush implements Runnable{

	public  Boolean flush=false;
	public Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	public Whether_flush() {
		try {
			
			socket=new Socket("127.0.0.1",9999);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			
		} catch (IOException e) {
			
		}
	}

	@Override
	public void run() {
		
		while(true){
			
			try {
				flush=Boolean.parseBoolean(input.readUTF());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(flush){
				flush=false;
				try {
					GameHall.access_Table();
					Table_Check.ergodic_Table();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
