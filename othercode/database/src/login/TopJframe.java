package login;

import javax.swing.*;

import login.zhujiemian.bgroundPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TopJframe implements MouseListener{
	
//	public static void main(String args[]){
//		TopJframe tj = new TopJframe();	
//	}
	
	JFrame jf = null;
	
	public static Container c = null;
	
	JPanel bgpanel = null;
	
	JLabel jluser = null;
	JLabel jlxiantiao = null;
	JLabel jlxiantiao2 = null;
	JLabel jldaohan1 = null;
	JLabel jldaohan2 = null;
	JLabel jldaohan3 = null;
	JLabel jldaohan4 = null;
	
	
	public void init(){
		
		bgpanel = new bgroundPanel(930,76,"images/logo.png");
		bgpanel.setLayout(null);
		
		
		jluser = new JLabel("��ӭ����"+denglu.jtext_acount.getText());
		jluser.setFont(new Font( "΢���ź�",Font.BOLD,12));		
		jluser.setBounds(500, 50, 200, 25);
		
		jldaohan1 = new JLabel("��ҳ");
		jldaohan2 = new JLabel("ÿ����ʾ");
		jldaohan3 = new JLabel("��Ϣ����");
		jldaohan4 = new JLabel("ÿ�ձ���");
		
		jldaohan1.setFont(new Font( "΢���ź�",Font.PLAIN,12));
		jldaohan2.setFont(new Font( "΢���ź�",Font.PLAIN,12));
		jldaohan3.setFont(new Font( "΢���ź�",Font.PLAIN,12));
		
		jldaohan1.setBounds(710,50,40,25);
		jldaohan2.setBounds(760,50,80,25);
		jldaohan3.setBounds(830,50,80,25);
		
		//��Ӽ���
		jldaohan1.addMouseListener(this);
		
		jldaohan1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		jlxiantiao = new JLabel(new ImageIcon("images/nav0827_04.gif"));
		jlxiantiao.setBounds(740,50,15, 22);
		jlxiantiao2 = new JLabel(new ImageIcon("images/nav0827_04.gif"));
		jlxiantiao2.setBounds(810,50,15, 22);

		bgpanel.add(jldaohan1);
		bgpanel.add(jldaohan2);
		bgpanel.add(jldaohan3);
//		bgpanel.add(jldaohan4);
		bgpanel.add(jluser);
		bgpanel.add(jlxiantiao);
		bgpanel.add(jlxiantiao2);
	}
	
	public TopJframe(){
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		c.add(bgpanel);

					
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


	@Override
	public void mouseClicked(MouseEvent mouseevent) {
		
		if(mouseevent.getSource().equals(jldaohan1)){
			zhujiemian.xiaoGuo(zhujiemian.right,"");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent mouseevent) {}

	@Override
	public void mouseExited(MouseEvent mouseevent) {}

	@Override
	public void mousePressed(MouseEvent mouseevent) {}

	@Override
	public void mouseReleased(MouseEvent mouseevent) {}
}
