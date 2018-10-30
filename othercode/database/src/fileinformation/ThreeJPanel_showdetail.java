/*
 * µÚÈý¸öJPanel
 */

package fileinformation;

import javax.swing.*;

import java.awt.*;
import java.util.Date;
import java.util.Vector;
import register.*;

public class ThreeJPanel_showdetail {
	
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
	
	public static JTextField jtextfield1 = null;
	public static JTextField jtextfield2 = null;
	public static JTextField jtextfield3 = null;
	public static JTextField jtextfield4 = null;
	
	public static JTextField jtextfield5 = null;
	public static JTextField jtextfield6 = null;
	public static JTextField jtextfield7 = null;
	public static JTextField jtextfield8 = null;
	public static JTextField jtextfield9 = null;
	public static JTextField jtextfield10 = null;

	
	public void init(){
		
		JPanel1 = new JPanel(new GridLayout(5,4,0,0));

		
		JLabel1 = new JLabel("¹ú¼®");
		JLabel2 = new JLabel("³öÉúµØ");
		JLabel3 = new JLabel("×Ú½ÌÐÅÑö");
		JLabel4 = new JLabel("ÕþÖÎÃæÃ²");
		JLabel5 = new JLabel("ÄêÁä");		
		JLabel6 = new JLabel("Ñ§Àú");		
		JLabel7 = new JLabel("³êÐ½±ê×¼");		
		JLabel8 = new JLabel("¿ª»§ÐÐ");		
		JLabel9 = new JLabel("¸´ºËÊ±¼ä");		
		JLabel10 = new JLabel("ÌØ³¤");		
		
		JLabel1.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel2.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel3.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel4.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel5.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel6.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel7.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel8.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel9.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel10.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		
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

		
		jtextfield1 = new JTextField();
		jtextfield2 = new JTextField();
		jtextfield3 = new JTextField();	
		jtextfield4 = new JTextField(new Date().toLocaleString());	
		jtextfield5 = new JTextField();	
		jtextfield6 = new JTextField();	
		jtextfield7 = new JTextField();	
		jtextfield8 = new JTextField();	
		jtextfield9 = new JTextField();	
		jtextfield10 = new JTextField();	
		
		jtextfield1.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield2.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield3.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield4.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield5.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield6.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield7.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield8.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield9.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield10.setBorder(BorderFactory.createBevelBorder(1));
		
		jtextfield1.setBackground(new Color(212,248,212));
		jtextfield2.setBackground(new Color(212,248,212));
		jtextfield3.setBackground(new Color(212,248,212));		
		jtextfield4.setBackground(new Color(212,248,212));		
		jtextfield5.setBackground(new Color(212,248,212));		
		jtextfield6.setBackground(new Color(212,248,212));		
		jtextfield7.setBackground(new Color(212,248,212));		
		jtextfield8.setBackground(new Color(212,248,212));		
		jtextfield9.setBackground(new Color(212,248,212));		
		jtextfield10.setBackground(new Color(212,248,212));		

		jtextfield1.setEditable(false);
		jtextfield2.setEditable(false);
		jtextfield3.setEditable(false);
		jtextfield4.setEditable(false);
		jtextfield5.setEditable(false);
		jtextfield6.setEditable(false);
		jtextfield7.setEditable(false);
		jtextfield8.setEditable(false);
		jtextfield9.setEditable(false);
		jtextfield10.setEditable(false);
	}
	
	public ThreeJPanel_showdetail(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		JPanel1.add(JLabel1);		
		JPanel1.add(jtextfield5);
		JPanel1.add(JLabel2);
		JPanel1.add(jtextfield1);
		
		JPanel1.add(JLabel3);
		JPanel1.add(jtextfield6);
		JPanel1.add(JLabel4);
		JPanel1.add(jtextfield7);
		
		JPanel1.add(JLabel5);
		JPanel1.add(jtextfield2);
		JPanel1.add(JLabel6);
		JPanel1.add(jtextfield8);
		
		JPanel1.add(JLabel7);
		JPanel1.add(jtextfield9);
		JPanel1.add(JLabel8);
		JPanel1.add(jtextfield3);
		
		JPanel1.add(JLabel9);
		JPanel1.add(jtextfield4);
		JPanel1.add(JLabel10);
		JPanel1.add(jtextfield10);
				
		JPanel1.setBackground(new Color(233,224,217));
		
//		c.add(JPanel1);
//		
//		jf.setVisible(true);
//		jf.setSize(401,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
