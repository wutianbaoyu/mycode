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
		centerPanel =  new JPanel();// �м����
		centerPanel.setLayout(new BorderLayout());
		
		jl1 = new JLabel("�ҵ�λ�ã�������Դ��ҳ");
		jl1.setFont(new Font( "΢���ź�",Font.PLAIN,12));
		jl1.setCursor(new Cursor(Cursor.HAND_CURSOR) );
		jlip = new JLabel("����������IP�ǣ�"+GetIP.getWebIp("http://city.ip138.com/city0.asp"));
		jlip.setFont(new Font( "΢���ź�",Font.PLAIN,12));
		
		right.setLayout(null);
		right.setBackground(new Color(247,240,238));

		right_up.setLayout(null);
		right_up.setBackground(new Color(247,240,238));
		
		jsp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//��ܷ�����
		jsp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//��ܷ�����
		jsp3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//��ܷ�����,������ұߵ����������·ָ�
		
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
		
		//���÷ָ��ߵ�λ��
		jsp1.setDividerLocation(75);
		jsp2.setDividerLocation(180);
		jsp3.setDividerLocation(20);
		
		jsp1.setDividerSize(0);//���÷ָ�����ϸ
		jsp2.setDividerSize(0);//���÷ָ�����ϸ
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
		
		//Сͼ��		
		jf.setIconImage(Toolkit.getDefaultToolkit().createImage("images/human.png"));
		
		jf.setVisible(true);
		jf.setSize(937, 596);
		
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		int height = (dem.height-jf.getHeight())/2;
		int width = (dem.width-jf.getWidth())/2;
		jf.setLocation(width,height);
		
		jf.setResizable(false);//����̶���������
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
			
			final JLabel l=new JLabel("����ʱ�䣺");

			 l.setFont(new Font( "΢���ź�",Font.PLAIN,12));
			
			l.setBounds(20,435,200,20);
			right.add(l);

			final SimpleDateFormat format=new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss",Locale.CHINA);
			
			Timer t=new Timer();
			t.scheduleAtFixedRate(new TimerTask() {			
				@Override
				public void run() {
					l.setText("����ʱ�䣺"+format.format(new Date(System.currentTimeMillis())));				
				}
			}, 0, 1000);
		}

	}
	
	// ����Ч������
	public static void xiaoGuo(final JPanel panel,final String title) {

		panel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());// ���û�����ʼλ��

		int count = centerPanel.getComponentCount();// ��ȡcenterPanel�пؼ���

		List list = new ArrayList();
		for (Component comp : centerPanel.getComponents()) {
			list.add(comp);// ��list��ֵ
		}

		if (count > 0) {// ���centerPanel�пؼ�������0��ִ��Ч��
			for (int i = 0; i < count; i++) {
				Component comp = centerPanel.getComponent(i);// ��ø�λ�õĿؼ�,�����õ���ǰһ����jpanel

				if (comp instanceof JPanel) {// �жϿؼ�����
					final JPanel currentPanel = (JPanel) comp;// ��õ�ǰpanel
					if (currentPanel != panel) { //�����õ�jpanel����������Ҫ��ʾ��jpanel,��Ҫ�����л�����
						
						new Thread() {

							public void run() {

								//Rectangle rec = currentPanel.getBounds();// ��õ�ǰ����λ����Ϣ
								int y = -centerPanel.getWidth();
								//System.out.println("y="+y);

								for (int i = 0; i <= centerPanel.getWidth(); i += 10) {
									// �������λ��
									//��ǰһ��JPanel���ұ��ƶ�
									currentPanel.setBounds(i, 0,
											centerPanel.getWidth(),
											centerPanel.getHeight());
									//��Ҫ��ʾ��JPanel����������ʾ
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

								centerPanel.remove(currentPanel);// �Ƴ���ǰ���,���Ƴ�ǰһ�����

								//�ƶ�������µ���panelλ�ã��Ա���ʾ����
								panel.setBounds(0, 0, centerPanel.getWidth(),centerPanel.getHeight());

								//������������
								jl1.setText("�ҵ�λ�ã�������Դ��ҳ"+title);
							}
						}.start();
						break;
					}
				}
			}
		}

		if (!list.contains(panel)) {
			//right.add(panel);// ���Ҫ�л������
			centerPanel.add(panel,BorderLayout.CENTER);
		}
			centerPanel.validate();// �ع��������
			centerPanel.repaint();// �ػ��������
	}


}
