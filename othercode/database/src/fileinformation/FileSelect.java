package fileinformation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;

import login.zhujiemian;

import calendar3.JCalendarChooser;

import com.modelCl.OrganizationCl;
import register.*;
public class FileSelect implements ActionListener{

//	public static void main(String[] args) {
//		new FileSelect();
//
//	}
	
	JFrame jf = null;
	
	public static JPanel c = null;
	
	JLabel jl1 = null;
	JLabel jl2 = null;
	JLabel jl3 = null;
	JLabel jl4 = null;
	JLabel jl5 = null;
	JLabel jl6 = null;
	JLabel jl7 = null;
	JLabel jl8 = null;
	static OrganizationCl oc = new OrganizationCl();
	public static JComboBox jc1 = new JComboBox(oc.getOneOrganization());
	public static JComboBox jc2 = new JComboBox(oc.getTwoOrganization(1));
	public static JComboBox jc3 = new JComboBox(oc.getThreeOrganization(1,"公司"));
	public static JComboBox jc4 = new JComboBox(oc.getPositionClassification());
	public static JComboBox jc5 = new JComboBox(oc.getPosition(1));
	
	JButton jb = null;
	
	
	public static JCalendarChooser file_chooser = new JCalendarChooser();
	public static JCalendarChooser file_chooser2 = new JCalendarChooser();
	
	
	public void init(){
		
		jl1 = new JLabel("I级机构",0);
		jl2 = new JLabel("II级机构",0);
		jl3 = new JLabel("III级机构",0);
		jl4 = new JLabel("职位分类",0);
		jl5 = new JLabel("职位名称",0);
		jl6 = new JLabel("建档时间",0);		
		jl7 = new JLabel("至");
		jl8 = new JLabel("（YYYY-MM-DD）");
		
		jl1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl6.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl7.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl8.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		jb = new JButton("查询");
		jb.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jb.setBorder(BorderFactory.createBevelBorder(0));

		
		jl1.setBounds(0, 40, 100, 30);
		jl2.setBounds(0, 80, 100, 30);
		jl3.setBounds(0, 120, 100, 30);
		jl4.setBounds(0, 160, 100, 30);
		jl5.setBounds(0, 200, 100, 30);
		jl6.setBounds(0, 240, 100, 30);
		
		jc1.setBounds(100, 40, 250, 30);
		jc2.setBounds(100, 80, 250, 30);
		jc3.setBounds(100, 120, 250, 30);
		jc4.setBounds(100, 160, 250, 30);
		jc5.setBounds(100, 200, 250, 30);
		
		jc1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc5.setFont(new Font( "微软雅黑",Font.BOLD,12));

		jc1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jc5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		jc1.setBorder(BorderFactory.createEtchedBorder());
		jc1.setBackground(Color.white);
		jc2.setBorder(BorderFactory.createEtchedBorder());
		jc2.setBackground(Color.white);
		jc3.setBorder(BorderFactory.createEtchedBorder());
		jc3.setBackground(Color.white);
		jc4.setBorder(BorderFactory.createEtchedBorder());
		jc4.setBackground(Color.white);
		jc5.setBorder(BorderFactory.createEtchedBorder());
		jc5.setBackground(Color.white);
		
		jc1.addActionListener(this);
		jc2.addActionListener(this);
		jc3.addActionListener(this);
		jc4.addActionListener(this);
		jc5.addActionListener(this);
		jb.addActionListener(this);
		
		file_chooser.setBounds(100, 240, 100, 30);
		jl7.setBounds(205, 240, 30, 30);
		file_chooser2.setBounds(225, 240, 100, 30);
		jl8.setBounds(325, 240, 150, 30);
		
		jb.setBounds(625, 0, 40, 20);
		jb.setCursor(new Cursor(Cursor.HAND_CURSOR) );
	}
	
	public FileSelect(){
		
		init();
		jf = new JFrame();
		
		c = (JPanel) jf.getContentPane();
		c.setLayout(null);
		
		c.add(jl1);
		c.add(jl2);
		c.add(jl3);
		c.add(jl4);
		c.add(jl5);
		c.add(jl6);
		c.add(jl7);
		c.add(jl8);
		
		c.add(jc1);
		c.add(jc2);
		c.add(jc3);
		c.add(jc4);
		c.add(jc5);
		
		c.add(file_chooser);
		c.add(file_chooser2);
		
		c.add(jb);
		
		c.setBackground(new Color(247,240,238));
		
//		
//		jf.setVisible(true);
//		jf.setSize(756, 460);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jc1)){
			//得到选中的值的索引
			//int index = jcombobox1.getSelectedIndex();
			
			Vector<String> towVector = oc.getTwoOrganization(jc1.getSelectedIndex()+1);

			jc2.removeAllItems();
			
			for(int i=0;i<towVector.size();i++){
				jc2.addItem(towVector.get(i));
			}
						
		}
		else if(e.getSource().equals(jc2)){

			if(jc2.getSelectedItem() != null){
				
				Vector<String> towVector = oc.getThreeOrganization(jc1.getSelectedIndex()+1,jc2.getSelectedItem().toString());
				
				jc3.removeAllItems();
				
				for(int i=0;i<towVector.size();i++){
					jc3.addItem(towVector.get(i));
				}
			}
		}
		else if(e.getSource().equals(jc4)){

			if(jc4.getSelectedItem() != null){

				Vector<String> towVector = oc.getPosition(jc4.getSelectedIndex()+1);
				
				jc5.removeAllItems();
				
				for(int i=0;i<towVector.size();i++){
					jc5.addItem(towVector.get(i));
				}
			}
		}
		else if(e.getSource().equals(jb)){
			if(file_chooser.getText().equals("") || file_chooser2.getText().equals("")){
				JOptionPane.showMessageDialog(jf, "请选择查询的日期");
			}else{
				//查询
				Show_after_select sas = new Show_after_select();
				zhujiemian.xiaoGuo(sas.c,"->人力资源档案管理->人力资源档案查询");
			}
		}
	}

}
