/*
 * ������JPanel
 */

package audit;

import javax.swing.*;

import java.awt.*;
import java.util.Date;
import java.util.Vector;
import register.*;

public class ThreeJPanel_au {
	
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
	
	static String string1[] = new String[]{"�й�","�¹�","����˹","����","Ӣ��"};
	static String string2[] = new String[]{"��","������","��˹����","���"};
	static String string3[] = new String[]{"Ⱥ��","������Ա","�й���Ա"};
	static String string4[] = new String[]{"Сѧ","��ѧ","����","ר��","����","˶ʿ","��ʿ"};
	static String string5[] = new String[]{"δ����/0","2","3","4","5"};
	static String string6[] = new String[]{"���ݿ�","����ϵͳ","Java","C++","C#"};
	
	public static JComboBox JComboBox1 = new JComboBox(string1);
	public static JComboBox JComboBox2 = new JComboBox(string2);
	public static JComboBox JComboBox3 = new JComboBox(string3);
	public static JComboBox JComboBox4 = new JComboBox(string4);
	public static JComboBox JComboBox5 = new JComboBox(string5);
	public static JComboBox JComboBox6 = new JComboBox(string6);
	
	public static JTextField jtextfield1 = null;
	public static JTextField jtextfield2 = null;
	public static JTextField jtextfield3 = null;
	public static JTextField jtextfield4 = null;
	
//	Vector<String> string1 = ;
//	String string2[] = null;	
//	String string3[] = null;	
//	String string4[] = null;	
//	String string5[] = null;	
//	String string6[] = null;	
	
	public void init(){
		
		JPanel1 = new JPanel(new GridLayout(5,4,0,0));

		
		JLabel1 = new JLabel("����");
		JLabel2 = new JLabel("������");
		JLabel3 = new JLabel("�ڽ�����");
		JLabel4 = new JLabel("������ò");
		JLabel5 = new JLabel("����");		
		JLabel6 = new JLabel("ѧ��");		
		JLabel7 = new JLabel("��н��׼");		
		JLabel8 = new JLabel("������");		
		JLabel9 = new JLabel("����ʱ��");		
		JLabel10 = new JLabel("�س�");		
		
		JLabel1.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel2.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel3.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel4.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel5.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel6.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel7.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel8.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel9.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel10.setFont(new Font( "΢���ź�",Font.BOLD,12));
		
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

		
		
//		string1 = new String[]{"�й�","2","3","4","5"};
//		string2 = new String[]{"��","2","3","4","5"};
//		string3 = new String[]{"��","2","3","4","5"};
//		string4 = new String[]{"����","2","3","4","5"};
//		string5 = new String[]{"δ����/0","2","3","4","5"};
//		string6 = new String[]{"���ݿ�","2","3","4","5"};
		
//		JComboBox1 = new JComboBox(string1);
//		JComboBox2 = new JComboBox(string2);
//		JComboBox3 = new JComboBox(string3);
//		JComboBox4 = new JComboBox(string4);
//		JComboBox5 = new JComboBox(string5);
//		JComboBox6 = new JComboBox(string6);
		
		JComboBox1.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox2.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox3.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox4.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox5.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox6.setFont(new Font( "΢���ź�",Font.BOLD,12));
		
		JComboBox1.setBorder(BorderFactory.createEtchedBorder());
		JComboBox1.setBackground(Color.white);
		JComboBox2.setBorder(BorderFactory.createEtchedBorder());
		JComboBox2.setBackground(Color.white);
		JComboBox3.setBorder(BorderFactory.createEtchedBorder());
		JComboBox3.setBackground(Color.white);
		JComboBox4.setBorder(BorderFactory.createEtchedBorder());
		JComboBox4.setBackground(Color.white);
		JComboBox5.setBorder(BorderFactory.createEtchedBorder());
		JComboBox5.setBackground(Color.white);
		JComboBox6.setBorder(BorderFactory.createEtchedBorder());
		JComboBox6.setBackground(Color.white);		
		
		jtextfield1 = new JTextField();
		jtextfield2 = new JTextField();
		jtextfield3 = new JTextField();	
		jtextfield4 = new JTextField(new Date().toLocaleString());	
		
		jtextfield1.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield2.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield3.setBorder(BorderFactory.createBevelBorder(1));
		
		jtextfield1.setBackground(new Color(212,248,212));
		jtextfield2.setBackground(new Color(212,248,212));
		jtextfield3.setBackground(new Color(212,248,212));		
		//jtextfield4.setBackground(new Color(212,248,212));	
		jtextfield4.setEditable(false);
	}
	
	public ThreeJPanel_au(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		JPanel1.add(JLabel1);		
		JPanel1.add(JComboBox1);
		JPanel1.add(JLabel2);
		JPanel1.add(jtextfield1);
		
		JPanel1.add(JLabel3);
		JPanel1.add(JComboBox2);
		JPanel1.add(JLabel4);
		JPanel1.add(JComboBox3);
		
		JPanel1.add(JLabel5);
		JPanel1.add(jtextfield2);
		JPanel1.add(JLabel6);
		JPanel1.add(JComboBox4);
		
		JPanel1.add(JLabel7);
		JPanel1.add(JComboBox5);
		JPanel1.add(JLabel8);
		JPanel1.add(jtextfield3);
		
		JPanel1.add(JLabel9);
		JPanel1.add(jtextfield4);
		JPanel1.add(JLabel10);
		JPanel1.add(JComboBox6);
				
		JPanel1.setBackground(new Color(233,224,217));
		
//		c.add(JPanel1);
//		
//		jf.setVisible(true);
//		jf.setSize(401,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
