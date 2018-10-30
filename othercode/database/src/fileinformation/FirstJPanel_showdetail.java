/*
 * µÚÒ»¸öJPanel
 */

package fileinformation;

import javax.swing.*;

import java.awt.*;


public class FirstJPanel_showdetail {

	JFrame jf = null;
	
	Container c = null;
	
	JPanel First_up_jpanel_1_left_up = null;
	JPanel First_up_jpanel_1_left_bottom = null;
	static JPanel First_up_jpanel_1_left = null;

	JLabel JLabel_1 = null;
	JLabel JLabel_2 = null;
	JLabel JLabel_3 = null;
	JLabel JLabel_4 = null;
	JLabel JLabel_5 = null;
	JLabel JLabel_6 = null;
	JLabel JLabel_7 = null;
	JLabel JLabel_8 = null;
	JLabel JLabel_9 = null;
	
	public static JLabel msgJLable1 = null;
	public static JLabel msgJLable2 = null;
	public static JLabel msgJLable3 = null;
	public static JLabel msgJLable4 = null;

	//public static JComboBox jcombobox5 = null;

	//String[] string5 = new String[]{"ÄÐ","Å®"};

	public static JTextField jtext1 = null;
	public static JTextField jtext2 = null;
	public static JTextField jtext3 = null;
	public static JTextField jtext4 = null;
	public static JTextField jtext5 = null;

	public void init(){

		//jcombobox5 = new JComboBox(string5);

		//jcombobox5.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));

		//jcombobox5.setBorder(BorderFactory.createEtchedBorder());
		//jcombobox5.setBackground(Color.white);
		
		//JLabel_1 = new JLabel("<html><body bgcolor=\"#ffffff\">II¼¶»ú¹¹</body> </html> ");
		JLabel_1 = new JLabel("I¼¶»ú¹¹");
		JLabel_2 = new JLabel("II¼¶»ú¹¹");
		JLabel_3 = new JLabel("Ö°Òµ·ÖÀà");
		JLabel_4 = new JLabel("Ö°Î»Ãû³Æ");
		JLabel_5 = new JLabel("ÐÕÃû");
		JLabel_6 = new JLabel("ÐÔ±ð");
		JLabel_7 = new JLabel("µç»°");
		JLabel_8 = new JLabel("QQ");
		JLabel_9 = new JLabel("×¡Ö·");
		msgJLable1 = new JLabel("");
		msgJLable2 = new JLabel("");
		msgJLable3 = new JLabel("");
		msgJLable4 = new JLabel("");
		
		JLabel_1.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_2.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_3.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_4.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_5.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_6.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_7.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_8.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		JLabel_9.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		msgJLable1.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		msgJLable2.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		msgJLable3.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		msgJLable4.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));

		JLabel_1.setBorder(BorderFactory.createEtchedBorder());
		JLabel_2.setBorder(BorderFactory.createEtchedBorder());
		JLabel_3.setBorder(BorderFactory.createEtchedBorder());
		JLabel_4.setBorder(BorderFactory.createEtchedBorder());
		JLabel_5.setBorder(BorderFactory.createEtchedBorder());
		JLabel_6.setBorder(BorderFactory.createEtchedBorder());
		JLabel_7.setBorder(BorderFactory.createEtchedBorder());
		JLabel_8.setBorder(BorderFactory.createEtchedBorder());
		msgJLable1.setBorder(BorderFactory.createEtchedBorder());
		msgJLable2.setBorder(BorderFactory.createEtchedBorder());
		msgJLable3.setBorder(BorderFactory.createEtchedBorder());
		msgJLable4.setBorder(BorderFactory.createEtchedBorder());
		
		msgJLable1.setOpaque(true);
		msgJLable2.setOpaque(true);
		msgJLable3.setOpaque(true);
		msgJLable4.setOpaque(true);
		
		JLabel_9.setPreferredSize(new Dimension(93, 20));
		
		jtext1 = new JTextField();
		jtext2 = new JTextField();
		jtext3 = new JTextField();
		jtext4 = new JTextField();
		jtext5 = new JTextField();
		
		jtext1.setBorder(BorderFactory.createBevelBorder(1));
		jtext2.setBorder(BorderFactory.createBevelBorder(1));
		jtext3.setBorder(BorderFactory.createBevelBorder(1));
		jtext4.setBorder(BorderFactory.createBevelBorder(1));
		jtext5.setBorder(BorderFactory.createBevelBorder(1));

		
		jtext1.setBackground(new Color(212,248,212));
		jtext2.setBackground(new Color(212,248,212));
		jtext3.setBackground(new Color(212,248,212));
		jtext4.setBackground(new Color(212,248,212));
		jtext5.setBackground(new Color(212,248,212));
		
		jtext1.setEditable(false);
		jtext2.setEditable(false);
		jtext3.setEditable(false);
		jtext4.setEditable(false);
		jtext5.setEditable(false);
		
	}
	public FirstJPanel_showdetail() {
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		First_up_jpanel_1_left_up = new JPanel(new GridLayout(4,4,0,0));
		First_up_jpanel_1_left_bottom = new JPanel(new BorderLayout());
		First_up_jpanel_1_left = new JPanel(new BorderLayout());
		
		//¼ÓÈë×é¼þ
		First_up_jpanel_1_left_up.add(JLabel_1);
		First_up_jpanel_1_left_up.add(msgJLable1);
		First_up_jpanel_1_left_up.add(JLabel_2);
		First_up_jpanel_1_left_up.add(msgJLable2);
		
		First_up_jpanel_1_left_up.add(JLabel_3);
		First_up_jpanel_1_left_up.add(msgJLable3);
		First_up_jpanel_1_left_up.add(JLabel_4);
		First_up_jpanel_1_left_up.add(msgJLable4);
		
		First_up_jpanel_1_left_up.add(JLabel_5);
		First_up_jpanel_1_left_up.add(jtext1);
		First_up_jpanel_1_left_up.add(JLabel_6);
		First_up_jpanel_1_left_up.add(jtext5);
		
		First_up_jpanel_1_left_up.add(JLabel_7);
		First_up_jpanel_1_left_up.add(jtext2);
		First_up_jpanel_1_left_up.add(JLabel_8);
		First_up_jpanel_1_left_up.add(jtext3);
		
		First_up_jpanel_1_left_bottom.add(JLabel_9,BorderLayout.WEST);
		First_up_jpanel_1_left_bottom.add(jtext4,BorderLayout.CENTER);
		
		First_up_jpanel_1_left.add(First_up_jpanel_1_left_up,BorderLayout.CENTER);
		First_up_jpanel_1_left.add(First_up_jpanel_1_left_bottom,BorderLayout.SOUTH);
		
		First_up_jpanel_1_left_up.setBackground(new Color(233,224,217));
		First_up_jpanel_1_left_bottom.setBackground(new Color(233,224,217));
		
		
//		c.add(First_up_jpanel_1_left);
		
//		jf.setVisible(true);
//		jf.setSize(402,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
