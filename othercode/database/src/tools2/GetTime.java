package tools2;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GetTime {
	
	public GetTime(){
		
		final JLabel l=new JLabel();		
		final SimpleDateFormat format=new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		
		Timer t=new Timer();
		t.scheduleAtFixedRate(new TimerTask() {			
			@Override
			public void run() {
				l.setText(format.format(new Date(System.currentTimeMillis())));				
			}
		}, 0, 1000);
	}

}