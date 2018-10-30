package audit;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import login.denglu;

import calendar3.JCalendarChooser;

import com.modelCl.OrganizationCl;
import register.*;

public class FourJPanel_au {

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
	
	static String string1[] = new String[]{"����","������","���¿���","ˮ��","����","��ɽ��"};
	static String string2[] = new String[]{"��","1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��","13��","14��","15��","16��"};
	static Vector<String> string3 = new OrganizationCl().getDegreeProfessinal();
	static String string4[] = new String[]{"����","����","����","��ë��","ƹ����"};
	
	public static JComboBox JComboBox1 = new JComboBox(string1);
	public static JComboBox JComboBox2 = new JComboBox(string2);
	public static JComboBox JComboBox3 = new JComboBox(string3);
	public static JComboBox JComboBox4 = new JComboBox(string4);

	
	//public static JTextField jtextfield1 = null;
	public static JTextField jtextfield2 = null;
	public static JTextField jtextfield3 = null;
	public static JTextField jtextfield4 = null;
	public static JTextField jtextfield5 = null;
	JTextField jtextfield6 = null;

	public static JCalendarChooser chose_au = new JCalendarChooser();
	
	public void init(){
		
		JPanel1 = new JPanel(new GridLayout(5,4,0,0));

		
		JLabel1 = new JLabel("����");
		JLabel2 = new JLabel("����");
		JLabel3 = new JLabel("�ֻ���");
		JLabel4 = new JLabel("��ᱣ�Ϻ���");
		JLabel5 = new JLabel("��������");		
		JLabel6 = new JLabel("ѧ��רҵ");		
		JLabel7 = new JLabel("�˺�");		
		JLabel8 = new JLabel("������");		
		JLabel9 = new JLabel("����");		
		JLabel10 = new JLabel("");				
		
		JLabel1.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel2.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel3.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel4.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel5.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel6.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel7.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel8.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JLabel9.setFont(new Font( "΢���ź�",Font.BOLD,12));
		
		
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
//		
//		JComboBox1 = new JComboBox(string1);
//		JComboBox2 = new JComboBox(string2);
//		JComboBox3 = new JComboBox(string3);
//		JComboBox4 = new JComboBox(string4);

		JComboBox1.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox2.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox3.setFont(new Font( "΢���ź�",Font.BOLD,12));
		JComboBox4.setFont(new Font( "΢���ź�",Font.BOLD,12));
		
		JComboBox1.setBorder(BorderFactory.createEtchedBorder());
		JComboBox1.setBackground(Color.white);
		JComboBox2.setBorder(BorderFactory.createEtchedBorder());
		JComboBox2.setBackground(Color.white);
		JComboBox3.setBorder(BorderFactory.createEtchedBorder());
		JComboBox3.setBackground(Color.white);
		JComboBox4.setBorder(BorderFactory.createEtchedBorder());
		JComboBox4.setBackground(Color.white);	
		
		//jtextfield1 = new JTextField();
		jtextfield2 = new JTextField();
		jtextfield3 = new JTextField();	
		jtextfield4 = new JTextField();	
		jtextfield5 = new JTextField(denglu.jtext_acount.getText());	
		jtextfield6 = new JTextField();	
		jtextfield5.setEditable(false);
		jtextfield6.setEditable(false);
		
		jtextfield2.setBorder(BorderFactory.createBevelBorder(1));
		chose_au.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield3.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield4.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield5.setBorder(BorderFactory.createBevelBorder(1));

		
		jtextfield2.setBackground(new Color(212,248,212));
		chose_au.setBackground(new Color(212,248,212));
		jtextfield3.setBackground(new Color(212,248,212));		
		jtextfield4.setBackground(new Color(212,248,212));		
		jtextfield5.setBackground(new Color(212,248,212));		
		jtextfield6.setBackground(new Color(212,248,212));		
	}
	
	public FourJPanel_au(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		JPanel1.add(JLabel1);		
		JPanel1.add(chose_au);
		JPanel1.add(JLabel2);
		JPanel1.add(JComboBox1);
		
		JPanel1.add(JLabel3);
		JPanel1.add(jtextfield2);
		JPanel1.add(JLabel4);
		JPanel1.add(jtextfield3);
		
		JPanel1.add(JLabel5);
		JPanel1.add(JComboBox2);
		JPanel1.add(JLabel6);
		JPanel1.add(JComboBox3);
		
		JPanel1.add(JLabel7);
		JPanel1.add(jtextfield4);
		JPanel1.add(JLabel8);
		JPanel1.add(jtextfield5);
		
		JPanel1.add(JLabel9);
		JPanel1.add(JComboBox4);
		JPanel1.add(JLabel10);
		JPanel1.add(jtextfield6);
				
		JPanel1.setBackground(new Color(233,224,217));
		
//		c.add(JPanel1);
//		
//		jf.setVisible(true);
//		jf.setSize(401,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
