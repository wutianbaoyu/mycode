/*
 * 第三个JPanel
 */

package register;

import javax.swing.*;

import java.awt.*;
import java.util.Date;

public class ThreeJPanel {
	
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
	public static JComboBox JComboBox5 = null;
	public static JComboBox JComboBox6 = null;
	
	public static JTextField jtextfield1 = null;
	public static JTextField jtextfield2 = null;
	public static JTextField jtextfield3 = null;
	public static JTextField jtextfield4 = null;
	
	String string1[] = new String[]{"中国","德国","俄罗斯","美国","英国"};
	String string2[] = new String[]{"无","基督教","伊斯兰教","佛教"};
	String string3[] = new String[]{"群众","共青团员","中共党员"};
	String string4[] = new String[]{"小学","中学","高中","专科","本科","硕士","博士"};
	String string5[] = new String[]{"未定义/0","2","3","4","5"};
	String string6[] = new String[]{"数据库","操作系统","Java","C++","C#"};
	
	public void init(){
		
		JPanel1 = new JPanel(new GridLayout(5,4,0,0));

		
		JLabel1 = new JLabel("国籍*");
		JLabel2 = new JLabel("出生地*");
		JLabel3 = new JLabel("宗教信仰");
		JLabel4 = new JLabel("政治面貌*");
		JLabel5 = new JLabel("年龄");		
		JLabel6 = new JLabel("学历*");		
		JLabel7 = new JLabel("酬薪标准*");		
		JLabel8 = new JLabel("开户行*");		
		JLabel9 = new JLabel("建档时间*");		
		JLabel10 = new JLabel("特长");		
		
		JLabel1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel6.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel7.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel8.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel9.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel10.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
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
		JComboBox5 = new JComboBox(string5);
		JComboBox6 = new JComboBox(string6);
		
		JComboBox1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox6.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
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
	
	public ThreeJPanel(){
		
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
