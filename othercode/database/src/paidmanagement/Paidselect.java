package paidmanagement;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import login.zhujiemian;

import calendar3.JCalendarChooser;

import register.FirstJPanel;
import register.SecondJPanel;

public class Paidselect implements ActionListener{

//	public static void main(String[] args) {
//		new Paidselect();
//
//	}
	JFrame jf = null;
	
	public static JPanel c = null;
	
	JLabel jl1 = null;
	JLabel jl2 = null;
	JLabel jl3 = null;
	JLabel jl7 = null;
	JLabel jl8 = null;
	
	public static JTextField jt1 = null;
	public static JTextField jt2 = null;
	public static JCalendarChooser jt3 = null;
	public static JCalendarChooser jt4 = null;
	
	JButton jb = null;
	
	public void init(){
		
		jl1 = new JLabel("请输入薪酬标准编号");
		jl2 = new JLabel("请输入关键字");
		jl3 = new JLabel("请输入登记时间");	
		jl7 = new JLabel("至",0);
		jl8 = new JLabel("（YYYY-MM-DD）");
		
		jl1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl7.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl8.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl1.setBorder(BorderFactory.createEtchedBorder());
		jl2.setBorder(BorderFactory.createEtchedBorder());
		jl3.setBorder(BorderFactory.createEtchedBorder());

		
		jb = new JButton("查询");
		jb.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jb.setBorder(BorderFactory.createBevelBorder(0));
		jb.addActionListener(this);
		
		jt1 = new JTextField();
		jt2 = new JTextField();
		jt3 = new JCalendarChooser();
		jt4 = new JCalendarChooser();
		
		jt1.setBorder(BorderFactory.createBevelBorder(1));
		jt2.setBorder(BorderFactory.createBevelBorder(1));
		jt3.setBorder(BorderFactory.createBevelBorder(1));
		jt4.setBorder(BorderFactory.createBevelBorder(1));
		
		jl1.setBounds(0, 50, 200, 30);
		jl2.setBounds(0, 80, 200, 30);
		jl3.setBounds(0, 110, 200, 30);
		
		jt1.setBounds(200, 50, 200, 30);
		jt2.setBounds(200, 80, 200, 30);
		
		jt3.setBounds(200, 110, 85, 30);		
		jl7.setBounds(285, 110, 30, 30);
		jt4.setBounds(315, 110, 85, 30);		
		
		jl8.setBounds(430, 110, 150, 30);
		
		
		jb.setBounds(625, 0, 40, 20);
		jb.setCursor(new Cursor(Cursor.HAND_CURSOR) );
	}
	
	public Paidselect(){
		
		init();
		jf = new JFrame();
		
		c = (JPanel) jf.getContentPane();
		c.setLayout(null);
		
		c.add(jl1);
		c.add(jl2);
		c.add(jl3);
		c.add(jl7);
		c.add(jl8);

		
		c.add(jt1);
		c.add(jt2);
		c.add(jt3);
		c.add(jt4);
		
		c.add(jb);
		
		c.setBackground(new Color(247,240,238));
		
		
//		jf.setVisible(true);
//		jf.setSize(756, 460);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}

	public boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("^\\d*$");
		Matcher isNum = pattern.matcher(str);
		
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(jb)){
			//判断查询条件是否满足
			if((!jt1.getText().equals("")) || (!jt2.getText().equals("") && !jt3.getText().equals("") && !jt4.getText().equals(""))){
				
				if(!jt1.getText().equals("")){
					if(!isNumeric(jt1.getText())){
						JOptionPane.showMessageDialog(jf, "请输入正确的酬薪编号信息.");
						return;
					}
				}
				zhujiemian.xiaoGuo(new PaidAfter_select().c,"->酬薪标准管理->酬薪标准查询");
				
			}else{
				JOptionPane.showMessageDialog(jf, "请完善查询条件.");
			}
		}
		
	}
	

}
