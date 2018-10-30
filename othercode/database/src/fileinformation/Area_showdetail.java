package fileinformation;

import javax.swing.*;

import com.modelCl.CasesCl;
import register.FirstJPanel;
import register.FiveJPanel;
import register.FourJPanel;
import register.RegLogin;
import register.SecondJPanel;
import register.ThreeJPanel;

import login.zhujiemian;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Area_showdetail implements ActionListener{

	JFrame jf = null;
	
	public static JPanel c = null;
	
	JPanel jpanel = null;
	JPanel jpanel2 = null;
	JPanel jpanel_button = null;
	JPanel jpanel_button_all = null;
	JPanel jpanel_up = null;
	JPanel jpanel_all = null;
	
	JButton jbutton2 = null;
	
	JLabel jlabel = null;
	public static JLabel jlabel2 = null;
	
	RegLogin rl = null;
	
	public void init(){
		rl = new RegLogin();
		
		jpanel = new JPanel(new GridLayout(2,2,0,0));
		jpanel2 = new JPanel(new GridLayout(2,2,0,0));
		jpanel_button = new JPanel(new GridLayout(1,2,5,0));
		jpanel_button_all = new JPanel(new BorderLayout());
		jpanel_up = new JPanel(new BorderLayout());
		jpanel_all = new JPanel(new BorderLayout());		

		jlabel2 = new JLabel("档案号：");
		jlabel2.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		

		jbutton2 = new JButton("返回");
		jbutton2.setBorder(BorderFactory.createBevelBorder(0));
		jbutton2.setFont(new Font( "微软雅黑",Font.PLAIN,12));		
		jbutton2.addActionListener(this);
		
		jpanel_button.setPreferredSize(new Dimension(80,20));
	}
	public Area_showdetail(){
		init();
		
		FirstJPanel_showdetail fjpanel = new FirstJPanel_showdetail();
		SecondJPanel_showdetail sjpanel = new SecondJPanel_showdetail();
		ThreeJPanel_showdetail tjpanel = new ThreeJPanel_showdetail();
		FourJPanel_showdetail fourjpanel = new FourJPanel_showdetail();
		FiveJPanel_showdetail fivejpanel = new FiveJPanel_showdetail();
		
		jf = new JFrame();
		
		c = (JPanel)jf.getContentPane();
		
		jpanel_button.add(jbutton2);
		
		jpanel_button_all.add(jpanel_button,BorderLayout.EAST);
		jpanel_button_all.add(jlabel2,BorderLayout.CENTER);
		
		jpanel_up.add(jpanel_button_all,BorderLayout.CENTER);
		//jpanel_up.add(jlabel,BorderLayout.NORTH);			
		
		jpanel.add(fjpanel.First_up_jpanel_1_left);
		jpanel.add(sjpanel.JPanel3);
		jpanel.add(tjpanel.JPanel1);
		jpanel.add(fourjpanel.JPanel1);
		
		jpanel2.add(jpanel);
		jpanel2.add(fivejpanel.jpanel4);
		
		jpanel_all.add(jpanel_up,BorderLayout.NORTH);
		jpanel_all.add(jpanel2,BorderLayout.CENTER);
		
		
		
		
		c.add(jpanel_all);
		
		
		//jf.setVisible(true);
		//jf.setSize(1052, 500);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		First_Area first = new First_Area();
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(jbutton2)){
			zhujiemian.xiaoGuo(Show_after_select.c,"->人力资源档案管理->档案查询");
		}
		
	}

}
