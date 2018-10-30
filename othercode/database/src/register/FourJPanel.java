package register;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
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

public class FourJPanel {

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
	
	public static JComboBox JComboBox1 = null;
	public static JComboBox JComboBox2 = null;
	public static JComboBox JComboBox3 = null;
	public static JComboBox JComboBox4 = null;

	
	//public static JTextField jtextfield1 = null;
	public static JCalendarChooser jtextfield1 = new JCalendarChooser();
	public static JTextField jtextfield2 = null;
	public static JTextField jtextfield3 = null;
	public static JTextField jtextfield4 = null;
	public static JTextField jtextfield5 = null;
	JTextField jtextfield6 = null;

	String string1[] = new String[]{"汉族","阿昌族","鄂温克族","水族","白族","高山族"};
	String string2[] = new String[]{"无","1年","2年","3年","4年","5年","6年","7年","8年","9年","10年","11年","12年","13年","14年","15年","16年"};
	Vector<String> string3 = new Vector<String>();	
	String string4[] = new String[]{"篮球","足球","排球","羽毛球","乒乓球"};
	
	OrganizationCl oc  = new OrganizationCl();
	
	public void init(){
		
		string3 = oc.getDegreeProfessinal();
		
		JPanel1 = new JPanel(new GridLayout(5,4,0,0));
		
		JLabel1 = new JLabel("生日");
		JLabel2 = new JLabel("民族*");
		JLabel3 = new JLabel("手机号*");
		JLabel4 = new JLabel("社会保障号码*");
		JLabel5 = new JLabel("教育年龄");		
		JLabel6 = new JLabel("学历专业*");		
		JLabel7 = new JLabel("账号*");		
		JLabel8 = new JLabel("登记人*");		
		JLabel9 = new JLabel("爱好");		
		JLabel10 = new JLabel("");				
		
		JLabel1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel6.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel7.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel8.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel9.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		
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
		
		JComboBox1 = new JComboBox(string1);
		JComboBox2 = new JComboBox(string2);
		JComboBox3 = new JComboBox(string3);
		JComboBox4 = new JComboBox(string4);

		JComboBox1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
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
		
		jtextfield1.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield2.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield3.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield4.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield5.setBorder(BorderFactory.createBevelBorder(1));

		
		jtextfield1.setBackground(new Color(212,248,212));
		jtextfield2.setBackground(new Color(212,248,212));
		jtextfield3.setBackground(new Color(212,248,212));		
		jtextfield4.setBackground(new Color(212,248,212));		
		jtextfield5.setBackground(new Color(212,248,212));		
		jtextfield6.setBackground(new Color(212,248,212));		
	}
	
	public FourJPanel(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		JPanel1.add(JLabel1);		
		JPanel1.add(jtextfield1);
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
//		
	}
//	public static void main(String args[]){
//		new FourJPanel();
//	}
}
