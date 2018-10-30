import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TestAudio extends JPanel implements ActionListener{
	private JButton buttonPlay = new JButton("≤•∑≈");
	private JButton buttonStop = new JButton("Õ£÷π");
	private AudioClip audio;
	
	public TestAudio(){
		this.add(buttonPlay);
		this.add(buttonStop);
		buttonPlay.addActionListener(this);
		buttonStop.addActionListener(this);
		try{
			File f = new File("./res/audio/begin.wav");
			System.out.println(f.toURI().toURL());		
			audio = Applet.newAudioClip(f.toURI().toURL());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("≤•∑≈"))
			audio.play();
//			audio.loop();
		else if(e.getActionCommand().equals("Õ£÷π") )
			audio.stop();
	}	 
	
	public static void main(String args[]){
		JFrame frame = new JFrame("…˘“Ù≤‚ ‘");
		frame.add(new TestAudio());
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
