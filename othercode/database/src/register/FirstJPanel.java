/*
 * 第一个JPanel
 */

package register;

import javax.swing.*;

import com.modelCl.OrganizationCl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;



public class FirstJPanel implements ActionListener{

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

	static OrganizationCl oc = new OrganizationCl(); 
	
	Vector<String> string1 = oc.getOneOrganization();
	Vector<String> string2 = oc.getTwoOrganization(1);
	Vector<String> string3 = oc.getPositionClassification();
	Vector<String> string4 = oc.getPosition(1);
	public static String[] string5 = new String[]{"男","女"};
	
	public static JComboBox jcombobox1 = null;
	public static JComboBox jcombobox2 = null;
	public static JComboBox jcombobox3 = null;
	public static JComboBox jcombobox4 = null;
	public static JComboBox jcombobox5 = null;
	
	public static JTextField jtext1 = null;
	public static JTextField jtext2 = null;
	public static JTextField jtext3 = null;
	public static JTextField jtext4 = null;
	
	public void init(){		           		

		string1 = oc.getOneOrganization();
		string2 = oc.getTwoOrganization(1);
		string3 = oc.getPositionClassification();
		string4 = oc.getPosition(1);
		//SecondJPanel.string1 = ol.getThreeOrganization(1, "公司");
		
		jcombobox1 = new JComboBox(string1);
		jcombobox2 = new JComboBox(string2);
		jcombobox3 = new JComboBox(string3);
		jcombobox4 = new JComboBox(string4);
		jcombobox5 = new JComboBox(string5);
		
		jcombobox1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		jcombobox1.setBorder(BorderFactory.createEtchedBorder());
		jcombobox1.setBackground(Color.white);
		jcombobox2.setBorder(BorderFactory.createEtchedBorder());
		jcombobox2.setBackground(Color.white);
		jcombobox3.setBorder(BorderFactory.createEtchedBorder());
		jcombobox3.setBackground(Color.white);
		jcombobox4.setBorder(BorderFactory.createEtchedBorder());
		jcombobox4.setBackground(Color.white);
		jcombobox5.setBorder(BorderFactory.createEtchedBorder());
		jcombobox5.setBackground(Color.white);
		
		//注册监听
		jcombobox1.addActionListener(this);
		jcombobox2.addActionListener(this);
		jcombobox3.addActionListener(this);
		jcombobox4.addActionListener(this);
		
		//JLabel_1 = new JLabel("<html><body bgcolor=\"#ffffff\">II级机构</body> </html> ");
		JLabel_1 = new JLabel("I级机构*");
		JLabel_2 = new JLabel("II级机构*");
		JLabel_3 = new JLabel("职业分类*");
		JLabel_4 = new JLabel("职位名称*");
		JLabel_5 = new JLabel("姓名*");
		JLabel_6 = new JLabel("性别*");
		JLabel_7 = new JLabel("电话*");
		JLabel_8 = new JLabel("QQ");
		JLabel_9 = new JLabel("住址*");
		
		JLabel_1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_6.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_7.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_8.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel_9.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		//JLabel_1.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		JLabel_1.setBorder(BorderFactory.createEtchedBorder());
		JLabel_2.setBorder(BorderFactory.createEtchedBorder());
		JLabel_3.setBorder(BorderFactory.createEtchedBorder());
		JLabel_4.setBorder(BorderFactory.createEtchedBorder());
		JLabel_5.setBorder(BorderFactory.createEtchedBorder());
		JLabel_6.setBorder(BorderFactory.createEtchedBorder());
		JLabel_7.setBorder(BorderFactory.createEtchedBorder());
		JLabel_8.setBorder(BorderFactory.createEtchedBorder());
		JLabel_9.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel_9.setPreferredSize(new Dimension(93, 20));
		
		
		jtext1 = new JTextField();
		jtext2 = new JTextField();
		jtext3 = new JTextField();
		jtext4 = new JTextField();
		
		jtext1.setBorder(BorderFactory.createBevelBorder(1));
		jtext2.setBorder(BorderFactory.createBevelBorder(1));
		jtext3.setBorder(BorderFactory.createBevelBorder(1));
		jtext4.setBorder(BorderFactory.createBevelBorder(1));
		
		jtext1.setBackground(new Color(212,248,212));
		jtext2.setBackground(new Color(212,248,212));
		jtext3.setBackground(new Color(212,248,212));
		jtext4.setBackground(new Color(212,248,212));
		
		//jtext1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51)));
	}
	public FirstJPanel() {
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		First_up_jpanel_1_left_up = new JPanel(new GridLayout(4,4,0,0));
		First_up_jpanel_1_left_bottom = new JPanel(new BorderLayout());
		First_up_jpanel_1_left = new JPanel(new BorderLayout());
		
		//加入组件
		First_up_jpanel_1_left_up.add(JLabel_1);
		First_up_jpanel_1_left_up.add(jcombobox1);
		First_up_jpanel_1_left_up.add(JLabel_2);
		First_up_jpanel_1_left_up.add(jcombobox2);
		
		First_up_jpanel_1_left_up.add(JLabel_3);
		First_up_jpanel_1_left_up.add(jcombobox3);
		First_up_jpanel_1_left_up.add(JLabel_4);
		First_up_jpanel_1_left_up.add(jcombobox4);
		
		First_up_jpanel_1_left_up.add(JLabel_5);
		First_up_jpanel_1_left_up.add(jtext1);
		First_up_jpanel_1_left_up.add(JLabel_6);
		First_up_jpanel_1_left_up.add(jcombobox5);
		
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
//		
//		jf.setVisible(true);
//		jf.setSize(402,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//	public static void main(String args[]){
//		new FirstJPanel();
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(jcombobox1)){
			//得到选中的值的索引
			//int index = jcombobox1.getSelectedIndex();
			
			Vector<String> towVector = oc.getTwoOrganization(jcombobox1.getSelectedIndex()+1);

			jcombobox2.removeAllItems();
			
			for(int i=0;i<towVector.size();i++){
				jcombobox2.addItem(towVector.get(i));
			}
						
		}
		else if(e.getSource().equals(jcombobox2)){

			if(jcombobox2.getSelectedItem() != null){
				
				Vector<String> towVector = oc.getThreeOrganization(jcombobox1.getSelectedIndex()+1,jcombobox2.getSelectedItem().toString());
				
				SecondJPanel.JComboBox1.removeAllItems();
				
				for(int i=0;i<towVector.size();i++){
					SecondJPanel.JComboBox1.addItem(towVector.get(i));
				}
			}
		}
		else if(e.getSource().equals(jcombobox3)){

			if(jcombobox3.getSelectedItem() != null){

				Vector<String> towVector = oc.getPosition(jcombobox3.getSelectedIndex()+1);
				
				jcombobox4.removeAllItems();
				
				for(int i=0;i<towVector.size();i++){
					jcombobox4.addItem(towVector.get(i));
				}
			}
		}
		
	}
	

}
