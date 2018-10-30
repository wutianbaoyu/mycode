/*
 * µÚ¶þ¸öJPanel
 */

package fileinformation;

import javax.swing.*;

import register.SecondJPanel;

import java.awt.*;

public class SecondJPanel_showdetail {		
	
	JFrame jf = null;
	
	Container c = null;
	
	JPanel JPanel1 = null;
	JPanel JPanel2 = null;
	static JPanel JPanel3 = null;
	
	JLabel JLabel1 = null;
	JLabel JLabel2 = null;
	JLabel JLabel3 = null;
	JLabel JLabel4 = null;
	JLabel JLabel5 = null;
	public static JLabel JLabelImage = null;
	public static JLabel msgJLabl = null;
	
	//JComboBox JComboBox1 = null;
	//public static JComboBox JComboBox2 = SecondJPanel.JComboBox2;
	
	public static JTextField jtextfield1 = null;
	public static JTextField jtextfield2 = null;
	public static JTextField jtextfield3 = null;
	public static JTextField jtextfield4 = null;
	
	//public static String string1[] = new String[]{"","2","3","4","5"};
	//String string2[] = new String[]{"","2","3","4","5"};
	
	public void init(){
		
		JPanel1 = new JPanel(new GridLayout(5,1,0,0));
		JPanel2 = new JPanel(new GridLayout(5,1,0,0));
		JPanel3 = new JPanel(new BorderLayout());
		
		JLabel1 = new JLabel("III¼¶»ú¹¹");
		JLabel2 = new JLabel("Ö°³Æ");
		JLabel3 = new JLabel("EMAIL");
		JLabel4 = new JLabel("ÓÊ±à");
		JLabel5 = new JLabel("Éí·ÝÖ¤ºÅ");		
		JLabelImage = new JLabel();
		msgJLabl = new JLabel("");
		
		JLabel1.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel2.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel3.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel4.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel5.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		msgJLabl.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		
		JLabel1.setBorder(BorderFactory.createEtchedBorder());
		JLabel2.setBorder(BorderFactory.createEtchedBorder());
		JLabel3.setBorder(BorderFactory.createEtchedBorder());
		JLabel4.setBorder(BorderFactory.createEtchedBorder());
		JLabel5.setBorder(BorderFactory.createEtchedBorder());
		JLabelImage.setBorder(BorderFactory.createEtchedBorder());
		msgJLabl.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel1.setPreferredSize(new Dimension(93, 20));
		JLabelImage.setPreferredSize(new Dimension(93,120));
		
		jtextfield1 = new JTextField();
		jtextfield2 = new JTextField();
		jtextfield3 = new JTextField();	
		jtextfield4 = new JTextField();	
		
		jtextfield1.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield2.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield3.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield4.setBorder(BorderFactory.createBevelBorder(1));
		
		jtextfield1.setBackground(new Color(212,248,212));
		jtextfield2.setBackground(new Color(212,248,212));
		jtextfield3.setBackground(new Color(212,248,212));
		jtextfield4.setBackground(new Color(212,248,212));
		
		jtextfield1.setEditable(false);
		jtextfield2.setEditable(false);
		jtextfield3.setEditable(false);
		jtextfield4.setEditable(false);
		
	}
	public SecondJPanel_showdetail(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		JPanel1.add(JLabel1);
		JPanel1.add(JLabel2);
		JPanel1.add(JLabel3);
		JPanel1.add(JLabel4);
		JPanel1.add(JLabel5);
		
		JPanel2.add(msgJLabl);
		JPanel2.add(jtextfield4);
		JPanel2.add(jtextfield1);
		JPanel2.add(jtextfield2);
		JPanel2.add(jtextfield3);
		
		JPanel3.add(JPanel1,BorderLayout.WEST);
		JPanel3.add(JPanel2,BorderLayout.CENTER);
		JPanel3.add(JLabelImage,BorderLayout.EAST);
		
		JPanel1.setBackground(new Color(233,224,217));
		
//		c.add(JPanel3);
//		
//		jf.setVisible(true);
//		jf.setSize(401,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
	}

}
