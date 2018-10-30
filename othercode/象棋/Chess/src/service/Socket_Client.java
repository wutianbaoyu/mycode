package service;

import gui.Log_In_Window;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import thread.ReadUTF_Thread;

public class Socket_Client {

	Socket socket;
	private ReadUTF_Thread read;
	private String ip;
	private String username;
	private int iconnum;
	public Socket_Client(String ip_1,String username_1, int iconnum) throws UnknownHostException, IOException {
		
		this.ip = ip_1;
		this.username=username_1;
		this.iconnum=iconnum;
		initSocket();
		
	}
	/**
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void initSocket() throws UnknownHostException, IOException {
		socket = new Socket(ip,8888);
		Log_In_Window.input = new DataInputStream(socket.getInputStream());
		Log_In_Window.output = new DataOutputStream(socket.getOutputStream());		
		read = new ReadUTF_Thread();
		Thread read_thread = new Thread(read);
		read_thread.start();
		Log_In_Window.output.writeUTF("us"+";"+username);
		Log_In_Window.output.writeUTF("icon"+";"+iconnum);
	} 
}
