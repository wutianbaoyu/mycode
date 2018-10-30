package fileinformation;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calendar3.JCalendarChooser;

import register.*;

public class FourJPanel_showdetail {

	JFrame jf = null;
	
	Container c = null;
	
	static JPanel JPanel1 = null;
	
	JLabel JLabel1 = null;
	JLabel JLabel2 = null;
	JLabel JLabel3 = null;
	JLabel JLabel4 = null;
	JLabel JLabel5 = null;
	JLabel JLabel6 = null;
	JLabel JLabel7 = null;
	JLabel JLabel8 = null;
	JLabel JLabel9 = null;
	JLabel JLabel10 = null;
	

	public static JTextField jtextfield[] = new JTextField[10];


	
	public void init(){
		
		JPanel1 = new JPanel(new GridLayout(5,4,0,0));

		
		JLabel1 = new JLabel("…˙»’");
		JLabel2 = new JLabel("√Ò◊Â");
		JLabel3 = new JLabel(" ÷ª˙∫≈");
		JLabel4 = new JLabel("…Áª·±£’œ∫≈¬Î");
		JLabel5 = new JLabel("ΩÃ”˝ƒÍ¡‰");		
		JLabel6 = new JLabel("—ß¿˙◊®“µ");		
		JLabel7 = new JLabel("’À∫≈");		
		JLabel8 = new JLabel("∏¥∫À»À");		
		JLabel9 = new JLabel("∞Æ∫√");		
		JLabel10 = new JLabel("");				
		
		JLabel1.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel2.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel3.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel4.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel5.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel6.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel7.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel8.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		JLabel9.setFont(new Font( "Œ¢»Ì—≈∫⁄",Font.BOLD,12));
		
		
		JLabel1.setBorder(BorderFactory.createEtchedBorder());
		JLabel2.setBorder(BorderFactory.createEtchedBorder());
		JLabel3.setBorder(BorderFactory.createEtchedBorder());
		JLabel4.setBorder(BorderFactory.createEtchedBorder());
		JLabel5.setBorder(BorderFactory.createEtchedBorder());
		JLabel6.setBorder(BorderFactory.createEtchedBorder());
		JLabel7.setBorder(BorderFactory.createEtchedBorder());
		JLabel8.setBorder(BorderFactory.createEtchedBorder());
		JLabel9.setBorder(BorderFactory.createEtchedBorder());
		JLabel10.setBorder(BorderFactory.createEtchedBorder());

		for(int i=0;i<jtextfield.length;i++){
			jtextfield[i] = new JTextField();
			jtextfield[i].setBorder(BorderFactory.createBevelBorder(1));
			jtextfield[i].setBackground(new Color(212,248,212));
		}
	
	}
	
	public FourJPanel_showdetail(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		JPanel1.add(JLabel1);		
		JPanel1.add(jtextfield[0]);
		JPanel1.add(JLabel2);
		JPanel1.add(jtextfield[1]);
		
		JPanel1.add(JLabel3);
		JPanel1.add(jtextfield[2]);
		JPanel1.add(JLabel4);
		JPanel1.add(jtextfield[3]);
		
		JPanel1.add(JLabel5);
		JPanel1.add(jtextfield[4]);
		JPanel1.add(JLabel6);
		JPanel1.add(jtextfield[5]);
		
		JPanel1.add(JLabel7);
		JPanel1.add(jtextfield[6]);
		JPanel1.add(JLabel8);
		JPanel1.add(jtextfield[7]);
		
		JPanel1.add(JLabel9);
		JPanel1.add(jtextfield[8]);
		JPanel1.add(JLabel10);
		JPanel1.add(jtextfield[9]);
				
		JPanel1.setBackground(new Color(233,224,217));
		
//		c.add(JPanel1);
//		
//		jf.setVisible(true);
//		jf.setSize(401,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
