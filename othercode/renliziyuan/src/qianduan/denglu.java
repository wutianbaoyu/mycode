package qianduan;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import houtai.*;



public class denglu implements MouseListener,EventListener{

	public static void main(String[] args) {
		denglu dl = new denglu();		

	}
	
	JFrame jf = null;
	JButton jb=null;
	JPanel c = null;
	
	//背景图片
	ImageIcon im = null;
	
	//登录图片
	ImageIcon img_login = null;
	
	JLabel background = null;
	JLabel passwd = null;
	JLabel jlb1,jlb2=null;
	public static JTextField jtext_acount = null;
	JTextField jtext_password2 = null;
	JPasswordField jtext_password = null;

	
	JButton login_button = null;
	
	JPanel jp_button = null;
	

	
	//权限
	public static int grade = 1;
	
	public void init(){
		
		im = new ImageIcon("images/1111.png");
		
		background = new JLabel(im);
		background.setBounds(0, 0, im.getIconWidth(), im.getIconHeight());
		
		passwd = new JLabel("请输入您的密码");
		
		img_login = new ImageIcon("images/button.png");
		
		jtext_acount = new JTextField("请输入您的帐号");
		jtext_password2 = new JTextField("请输入您的密码");
		jtext_password = new JPasswordField();
        jlb1=new JLabel("用户名");  
        jlb2=new JLabel("密    码");
		//设置JText字体颜色
		jtext_acount.setForeground(new Color(148,144,143));
		jtext_password.setForeground(new Color(148,144,143));
		jtext_password2.setForeground(new Color(148,144,143));

		//jtext_acount.setCaretColor(Color.YELLOW);//光标的颜色
		
		login_button = new JButton("登陆");
		jb=new JButton("重置");  
		
		//定位个个组件
		jtext_acount.setBounds(40, 245, 150, 24);
		jtext_password2.setBounds(40, 278, 150, 24);
		jtext_password.setBounds(40, 278, 150, 24);
		login_button.setBounds(40,320,img_login.getIconWidth(), img_login.getIconHeight());
		jlb1.setBounds(0,245,150,24);
		jlb2.setBounds(0,278,150,24);
		jb.setBounds(100, 320, 60, 24);

		
		//设置JTextField的边框线条
		jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
		jtext_password.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
		jtext_password2.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));

	}
	
	public denglu(){
		
		init();
		jf = new JFrame();
		jf.setTitle("人力资源管理平台");
		
		c = (JPanel) jf.getContentPane();
		c.setOpaque(false);	//使容器透明
		c.setLayout(null);//将布局设置为Null
				
		//注册监听
		jtext_acount.addMouseListener(this);
		//jtext_password.addMouseListener(this);
		jtext_password2.addMouseListener(this);
		login_button.addMouseListener(this);
		jf.addMouseListener(this);
		jf.setFocusable(true);
		c.add(jlb1);
		c.add(jlb2);
		jb.addMouseListener(this);
		c.add(login_button);
		c.add(jtext_acount);
		c.add(jtext_password2);
		c.add(jb);	
						
		jf.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		

		//初始焦点为jf
		jf.requestFocus();//获得焦点（即鼠标的显示）		

		jf.setVisible(true);
		jf.setSize(200, 400);
		//将JFrame显示在屏幕中间
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		int height = (dem.height-jf.getHeight())/2;
		int width = (dem.width-jf.getWidth())/2;
		jf.setLocation(width,height);
		
		jf.setResizable(false);//界面固定不能拉伸
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(jtext_acount.getText().equals("")){
			jtext_acount.setForeground(new Color(148,144,143));
			jtext_acount.setText("请输入您的帐号");
		}
		//先判断密码框是否有值，如果没有，则显示为JTextField
		if(jtext_password.getText().equals("")){
			c.remove(jtext_password);
			c.add(jtext_password2);
			c.validate();//重新验证组件
			//刷新一次
			jf.repaint();
		}
		if(e.getSource().equals(jb))  
	    {  
	              clear();  
	    }  
		//如果点击的是帐号框
		if(e.getSource().equals(jtext_acount)){

			if(jtext_acount.getText().equals("请输入您的帐号")){
				jtext_acount.setText("");
				//改变字体颜色为默认值
				jtext_acount.setForeground(null);
			}
			
			//获得焦点
			jtext_acount.requestFocus();//获得焦点（即鼠标的显示）
			//将帐号的边框的颜色改变
			jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(97,165,188)));
			//将密码的边框改变颜色
			jtext_password2.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
		}
		//如果点击的是密码框
		else if(e.getSource().equals(jtext_password2)){
			//将帐号的边框设置为原来的颜色
			jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
			//将密码的边框改变颜色
			jtext_password.setBorder(BorderFactory.createLineBorder(new Color(97,165,188)));
			//将JText移除，那么显示的即为JPasswordField
			c.remove(jtext_password2);
			c.add(jtext_password);
			c.validate();//重新验证组件
			//改变密码的字体颜色
			jtext_password.setForeground(null);
			
			jtext_password.requestFocus();//获得焦点（即鼠标的显示）
			
			//刷新一遍，使JText中的文字去除
			jf.repaint();
		}
		//如果点击的是登录按钮
		else if(e.getSource().equals(login_button)){
			
			yonhuyanzheng uc = new yonhuyanzheng();
			
			if(uc.checkUser(jtext_acount.getText(), jtext_password.getText())){
				
				//将权限放到一个变量中
				grade = uc.getGrade(jtext_acount.getText());
				
				
				jf.setVisible(false);
				
			}else{
				JOptionPane.showMessageDialog(jf, "用户名或密码错误.");
			}
			//JOptionPane.showMessageDialog(null, "您的帐号是"+jtext_acount.getText()+"\r\n您的密码是"+jtext_password.getText()+"", "用户登录", JOptionPane.INFORMATION_MESSAGE);

		}
		//如果点击的是面板
		else if(e.getSource().equals(jf)){			
			
			//将边框颜色设置为原来的颜色
			jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
			jtext_password2.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
			
			//获得焦点
			jf.requestFocus();//获得焦点（即鼠标的显示）

		}

	}
	private void clear() {
		// TODO Auto-generated method stub
		jtext_acount.setForeground(new Color(148,144,143));
		jtext_acount.setText("请输入您的帐号");
		jtext_password.setForeground(new Color(148,144,143));
		jtext_password.setText("");

	}
}
