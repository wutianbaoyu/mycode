package login;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.SplitPaneUI;



import tools2.GetIP;

import calendar2.Simple_Calendar;
import register.*;

import java.awt.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class zhujiemian{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		zhujiemian mf = new zhujiemian();
	}
	
	public JFrame jf = null;
	
	Container c = null; 
	
	JSplitPane jsp1 = null;
	JSplitPane jsp2 = null;
	static JSplitPane jsp3 = null;
	
	JPanel top = null;
	JPanel left = null;
	static JPanel right = null;
	JPanel right_up = null;	
	static JPanel centerPanel = null;
	
	public static JLabel jl1 = null;
	JLabel jlip = null;
	
	public void init(){
		Reg_Area f_a = new Reg_Area();
		Navegation na = new Navegation();
		TopJframe tj = new TopJframe();
		Simple_Calendar s_c = new Simple_Calendar();
		
		//top = new bgroundPanel(930,76,"images/logo.png");
		left = new JPanel();
		right = new JPanel();
		right_up = new JPanel();
		centerPanel =  new JPanel();// 中间面板
		centerPanel.setLayout(new BorderLayout());
		
		jl1 = new JLabel("我的位置：人力资源首页");
		jl1.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		jl1.setCursor(new Cursor(Cursor.HAND_CURSOR) );
		jlip = new JLabel("本机的外网IP是："+GetIP.getWebIp("http://city.ip138.com/city0.asp"));
		jlip.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		
		right.setLayout(null);
		right.setBackground(new Color(247,240,238));

		right_up.setLayout(null);
		right_up.setBackground(new Color(247,240,238));
		
		jsp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//框架分上下
		jsp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//框架分左右
		jsp3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//框架分上下,这个在右边的区域在上下分隔
		
		jsp1.setTopComponent(tj.c);
		jsp1.setBottomComponent(jsp2);

		jl1.setBounds(10,0,749,20);
		s_c.c.setBounds(50,20,620,400);
		
		jlip.setBounds(500,435,200,20);
		
		right_up.add(jl1);
		right_up.setBorder(null);
		
		right.add(s_c.c);		
		right.add(jlip);
		
		centerPanel.add(right,BorderLayout.CENTER);

		jsp3.setTopComponent(right_up);
		jsp3.setBottomComponent(centerPanel);
		
		jsp2.setLeftComponent(na.c);		
		jsp2.setRightComponent(jsp3);
		
		//设置分割线的位置
		jsp1.setDividerLocation(75);
		jsp2.setDividerLocation(180);
		jsp3.setDividerLocation(20);
		
		jsp1.setDividerSize(0);//设置分隔条粗细
		jsp2.setDividerSize(0);//设置分隔条粗细
		jsp3.setDividerSize(0);
		
		jsp1.setEnabled(false);
		jsp2.setEnabled(false);
		jsp3.setEnabled(false);
		
		GetTime gt = new GetTime();
		
//		SplitPaneUI ui = leftSplitPane.getUI();  
//		if (ui instanceof BasicSplitPaneUI) {  
//		    ((BasicSplitPaneUI) ui).getDivider().setBorder(null);  
//		}  
				
	}
	public zhujiemian() {
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		
		c.add(jsp1);
		
		//小图标		
		jf.setIconImage(Toolkit.getDefaultToolkit().createImage("images/human.png"));
		
		jf.setVisible(true);
		jf.setSize(937, 596);
		
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		int height = (dem.height-jf.getHeight())/2;
		int width = (dem.width-jf.getWidth())/2;
		jf.setLocation(width,height);
		
		jf.setResizable(false);//界面固定不能拉伸
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class bgroundPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		
		int width=0,hight=0;
		String imgpath = "";
		
		public bgroundPanel(int width,int hight,String file){
		  this.width = width;
		  this.hight = hight;
		  imgpath = file;
		}
		
		protected void paintComponent(Graphics g) {  
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, null,null);
		}  
	}
	
	public class GetTime {
		
		public GetTime(){
			
			final JLabel l=new JLabel("北京时间：");

			 l.setFont(new Font( "微软雅黑",Font.PLAIN,12));
			
			l.setBounds(20,435,200,20);
			right.add(l);

			final SimpleDateFormat format=new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss",Locale.CHINA);
			
			Timer t=new Timer();
			t.scheduleAtFixedRate(new TimerTask() {			
				@Override
				public void run() {
					l.setText("北京时间："+format.format(new Date(System.currentTimeMillis())));				
				}
			}, 0, 1000);
		}

	}
	
	// 滑动效果方法
	public static void xiaoGuo(final JPanel panel,final String title) {

		panel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());// 设置滑动初始位置

		int count = centerPanel.getComponentCount();// 获取centerPanel中控件数

		List list = new ArrayList();
		for (Component comp : centerPanel.getComponents()) {
			list.add(comp);// 给list赋值
		}

		if (count > 0) {// 如果centerPanel中控件数大于0就执行效果
			for (int i = 0; i < count; i++) {
				Component comp = centerPanel.getComponent(i);// 获得该位置的控件,这里获得的是前一个的jpanel

				if (comp instanceof JPanel) {// 判断控件类型
					final JPanel currentPanel = (JPanel) comp;// 获得当前panel
					if (currentPanel != panel) { //如果获得的jpanel不等于现在要显示的jpanel,就要进行切换滑动
						
						new Thread() {

							public void run() {

								//Rectangle rec = currentPanel.getBounds();// 获得当前面板的位置信息
								int y = -centerPanel.getWidth();
								//System.out.println("y="+y);

								for (int i = 0; i <= centerPanel.getWidth(); i += 10) {
									// 设置面板位置
									//将前一个JPanel向右边移动
									currentPanel.setBounds(i, 0,
											centerPanel.getWidth(),
											centerPanel.getHeight());
									//将要显示的JPanel从左往右显示
									panel.setBounds(y, 0,
											centerPanel.getWidth(),
											centerPanel.getHeight());
									try {
										Thread.sleep(5);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									y += 10;
								}

								centerPanel.remove(currentPanel);// 移除當前面板,即移除前一个面板

								//移动完后，重新调整panel位置，以便显示正常
								panel.setBounds(0, 0, centerPanel.getWidth(),centerPanel.getHeight());

								//标题重新设置
								jl1.setText("我的位置：人力资源首页"+title);
							}
						}.start();
						break;
					}
				}
			}
		}

		if (!list.contains(panel)) {
			//right.add(panel);// 添加要切换的面板
			centerPanel.add(panel,BorderLayout.CENTER);
		}
			centerPanel.validate();// 重构内容面板
			centerPanel.repaint();// 重绘内容面板
	}


}
