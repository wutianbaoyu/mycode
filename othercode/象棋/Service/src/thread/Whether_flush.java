package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

public class Whether_flush implements Runnable{

	private DataInputStream input;
	private DataOutputStream output;
	@Override
	public void run() {
		
		Iterator it = Receive_name.v.iterator();		
	while(it.hasNext()){
		
		Socket a=(Socket) it.next();
		try {
			input = new DataInputStream(a.getInputStream());
			output = new DataOutputStream(a.getOutputStream());
			output.writeUTF("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		}
	}

}
