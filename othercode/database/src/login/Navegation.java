package login;

import javax.swing.*;

import treeNavegation.AboutJTree;
import treeNavegation.FirstJTree;
import treeNavegation.PaidTree;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Navegation implements ActionListener,MouseListener{

//	public static void main(String[] args) {
//		Navegation na = new Navegation();
//
//	}

	
	JFrame jf = null;
	
	public static Container c = null;
		
	//����ͼƬ
	ImageIcon im = null;
	
	ImageIcon buttonim1 = null;
	ImageIcon buttonim2 = null;
	ImageIcon buttonim3 = null;
	ImageIcon buttonim4 = null;
	ImageIcon buttonim5 = null;
	
	ImageIcon newbuttonim1 = null;
	ImageIcon newbuttonim2 = null;
	ImageIcon newbuttonim3 = null;
	ImageIcon newbuttonim4 = null;
	ImageIcon newbuttonim5 = null;
	
	JLabel background_daohang = null;
	
	//�м����
	JPanel jcenter = null;
	
	JPanel jp1 = null;
	JPanel jp2 = null;
	JPanel jp3 = null;
	JPanel jp4 = null;
	JPanel jp5 = null;

	JButton jb1 = null;
	JButton jb2 = null;
	JButton jb3 = null;
	JButton jb4 = null;
	JButton jb5 = null;
	
	//����һ�����飬��Ű�ť���������滹������.��ʼ����Ϊ1����ʾ�������棬������-1
	int dire[] = new int[]{1,1,1,1,1};
	
	//�������б��ų�ʼ��JPanel
	ArrayList<Component> al =  new ArrayList<Component>();
	
	//�������б�����Ҫ�ƶ���JPanel
	ArrayList<Component> newal =  new ArrayList<Component>();
	
	//�������б�����Ҫ��ԭ��button
	ArrayList<JButton> bottonreset =  new ArrayList<JButton>();
	
	//�������б�����Ҫ��ԭ��button��ͼƬ
	ArrayList<ImageIcon> bottonresetImage =  new ArrayList<ImageIcon>();
	
	//��ʼ����ť�߶�
	int Button_Height = 40;
	
	//��ʼ��ÿ��jpanel�ĸ߶ȺͿ��
	int Jpanel_Height = 260;
	int Jpanel_Width = 179;
	
	int Flag = 5;
	
	public void init(){
		
		FirstJTree ftree = new FirstJTree();
		AboutJTree abouttree = new AboutJTree();
		PaidTree paidtree = new PaidTree();
		
		jcenter = new bgroundPanel(179,490,"images/daohang2.png");
		//jcenter = new JPanel();
		//Ĭ����ʾ��buttonͼƬ
		buttonim1 = new ImageIcon("images/title1.png");
		buttonim2 = new ImageIcon("images/title2.png");
		buttonim3 = new ImageIcon("images/title3.png");
		buttonim4 = new ImageIcon("images/title4.png");
		buttonim5 = new ImageIcon("images/title5.png");
		
		//����ƶ�����ʾ��buttonͼƬ
		newbuttonim1 = new ImageIcon("images/newtitle1.png");
		newbuttonim2 = new ImageIcon("images/newtitle2.png");
		newbuttonim3 = new ImageIcon("images/newtitle3.png");
		newbuttonim4 = new ImageIcon("images/newtitle4.png");
		newbuttonim5 = new ImageIcon("images/newtitle5.png");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();

		jcenter.setLayout(null);
		jcenter.setSize(179, 490);
		jp1.setLayout(null);
		jp2.setLayout(null);
		jp3.setLayout(null);
		jp4.setLayout(null);
		jp5.setLayout(null);
		
		jp1.setBounds(0, 70, Jpanel_Width, Button_Height);
		jp2.setBounds(0, 70+Button_Height*1, Jpanel_Width, Button_Height);
		jp3.setBounds(0, 70+Button_Height*2, Jpanel_Width, Button_Height);
		jp4.setBounds(0, 70+Button_Height*3, Jpanel_Width, Button_Height);
		jp5.setBounds(0, 70+Button_Height*4, Jpanel_Width, Jpanel_Height);
				
		jb1 = new JButton(buttonim1);
		jb2 = new JButton(buttonim2);
		jb3 = new JButton(buttonim3);		
		jb4 = new JButton(buttonim4);		
		jb5 = new JButton(newbuttonim5);
		
		jp1.add(jb1);		
		jp2.add(jb2);
		jp2.add(ftree.jsp);
		
		jp3.add(jb3);
		jp3.add(paidtree.jsp);
		
		jp4.add(jb4);
		jp5.add(jb5);
		jp5.add(abouttree.jsp);
		
		jb1.setBounds(0, 0, jp1.getWidth(),Button_Height);
		jb2.setBounds(0, 0, jp2.getWidth(),Button_Height);
		jb3.setBounds(0, 0, jp3.getWidth(),Button_Height);
		jb4.setBounds(0, 0, jp4.getWidth(),Button_Height);
		jb5.setBounds(0, 0, jp5.getWidth(),Button_Height);
		
		//ftree.c.setBounds(0, Button_Height+50, jp2.getWidth(),160);
		
		jb1.setBorder(null);
		jb2.setBorder(null);
		jb3.setBorder(null);
		jb4.setBorder(null);
		jb5.setBorder(null);	
		
		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jcenter.setOpaque(false);
		//ftree.jsp.setOpaque(false);
		
		ftree.jsp.setBounds(15, Button_Height+10,150, 200);
		abouttree.jsp.setBounds(15, Button_Height+10,150, 200);
		paidtree.jsp.setBounds(15, Button_Height+10,150, 200);

		
		jcenter.add(jp5);
		jcenter.add(jp4);
		jcenter.add(jp3);
		jcenter.add(jp2);
		jcenter.add(jp1);
		
		bottonreset.add(jb1);
		bottonreset.add(jb2);
		bottonreset.add(jb3);
		bottonreset.add(jb4);
		bottonreset.add(jb5);
		
		bottonresetImage.add(buttonim1);
		bottonresetImage.add(buttonim2);
		bottonresetImage.add(buttonim3);
		bottonresetImage.add(buttonim4);
		bottonresetImage.add(buttonim5);
		
	}
	public Navegation(){
		
		init();
		jf = new JFrame();
		
		jf.setContentPane(jcenter);
		
		c = jf.getContentPane();

		al.add(jp1);
		al.add(jp2);
		al.add(jp3);
		al.add(jp4);
		al.add(jp5);

		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		
		jb1.setActionCommand("jb1");
		jb2.setActionCommand("jb2");
		jb3.setActionCommand("jb3");
		jb4.setActionCommand("jb4");
		jb5.setActionCommand("jb5");
		
		jb1.addMouseListener(this);
		jb2.addMouseListener(this);
		jb3.addMouseListener(this);
		jb4.addMouseListener(this);
		//jb5.addMouseListener(this);
		
//		c.add(jcenter);
//		jf.setVisible(true);
//		jf.setSize(500, 1000);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void moveUp(int flag){
		
		//�ҳ���ǰ�ж��ٸ���ť��������ģ�����Ҫ�ڸð�ť�����棩����ôҪһ�������ƶ�
		//��һ������������
		for(int i=0;i<flag-1;i++){
			if(dire[i] == -1){
				//Nums_up++;//�õ�һ���ƶ��İ�ť�ĸ���
				newal.add(al.get(i));
			}
		}
	}
	
	//�����ƶ�
	public void moveDown(int flag){

		//�����ť������ť�����������ť���ƶ������棬���û�а�ť������Ҫ�ƶ�
		for(int i=flag;i<dire.length;i++){
			if(dire[i] == 1){
				//Nums_down++;//�õ��ƶ��İ�ť�ĸ���,����������
				newal.add(al.get(i));
			}
		}

	}

	public void xiaoGuo(final JPanel panel,final int flag) {
		
		final int starty = panel.getBounds().y;//��ó�ʼ��Y����
		final int startx = panel.getBounds().x;//��ó�ʼ��Y����

		new Thread() {

			public void run() {

				//�ҳ���ǰ���ĸ�Panel�ĸ߶���370�ġ�
				Component ct = null;
				int y_next = 0;
				for(int z=0;z<al.size();z++){
					//System.out.println(al.get(z).getBounds().y);
					if(al.get(z).getBounds().getHeight() == 260){
						ct = al.get(z);
						y_next = al.get(z).getBounds().y;
						break;
					}
				}
				
				//����
				if(dire[flag-1] == 1){

					moveDown(flag);
					
					int y = 1;

					for (int i = 0; i <= 220; i+=5,y+=5) {
	
						//�����ƶ����ǣ�����İ�ť�����������������ť,�����ǵ�panel����������ֻ��ʾbutton,Ϊ���߳��ƶ�������
						for(int j=0;j<newal.size();j++){
							newal.get(j).setBounds(0,i+starty+Button_Height*(j+1),Jpanel_Width,Jpanel_Height-y+1);
							//System.out.println(jp5.getBounds().y);
							//�����෴����
							dire[flag+j] = -1;
						}					

						if(newal.size() >= 1){
							panel.setBounds(0,starty,Jpanel_Width,Button_Height+y-1);
							//System.out.println(panel.getHeight());
						}
						
						try {
							Thread.sleep(1);
							
						} catch (InterruptedException e) {							
							e.printStackTrace();
						}
						
					}
	
					newal.clear();

				}
				//����
				else if(dire[flag-1] == -1){


					moveUp(flag);

					int y = 1;				
					
					for (int i = starty; (flag-1)*Button_Height+70 <= i; i-=5,y+=5) {
						
						//��Ҫ��ʾ��PanelŪ�س���
						for(int z=0;z<newal.size();z++){
							newal.get(z).setBounds(0,i-Button_Height*(newal.size()-z),Jpanel_Width,40);
							//�����෴����
							dire[flag-z-2] = 1;
						}
						
						if(ct != null){
							ct.setBounds(0, y_next, Jpanel_Width, Jpanel_Height-y+1);
						}
						//��Ҫ��ʾ��PanelŪ�س���
						panel.setBounds(0,i,Jpanel_Width,Button_Height+y-1);
												
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						
					}
					dire[flag-1] = 1;
					
					//panel.setBounds(0,30*(sign-1),c.getWidth(),200);
					jf.repaint();
					newal.clear();
				}
				
//				System.out.println("jp1="+jp1.getBounds().y);
//				System.out.println("jp2="+jp2.getHeight());
//				System.out.println("jp3="+jp3.getBounds().y);
//				System.out.println("jp4="+jp4.getBounds().y);
//				System.out.println("jp5="+jp5.getBounds().y);
//				System.out.println("---------");

			}
			
		}.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("jb5")){
			xiaoGuo(jp5,5);
		}		
		if(arg0.getActionCommand().equals("jb4")){
			xiaoGuo(jp4,4);
		}
		if(arg0.getActionCommand().equals("jb3")){
			xiaoGuo(jp3,3);
		}
		if(arg0.getActionCommand().equals("jb2")){
			xiaoGuo(jp2,2);
		}
		if(arg0.getActionCommand().equals("jb1")){
			xiaoGuo(jp1,1);
		}
		newal.clear();
		
		
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
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {

		if(e.getSource().equals(jb1)){
			jb1.setIcon(newbuttonim1);
		}
		if(e.getSource().equals(jb2)){

			jb2.setIcon(newbuttonim2);
		}
		if(e.getSource().equals(jb3)){

			jb3.setIcon(newbuttonim3);
		}
		if(e.getSource().equals(jb4)){
			jb4.setIcon(newbuttonim4);
		}
		if(e.getSource().equals(jb5)){
			jb5.setIcon(newbuttonim5);
		}				
		
	}
	@Override
	public void mouseExited(MouseEvent e) {

		if(e.getSource().equals(jb1)){
			jb1.setIcon(buttonim1);
		}
		if(e.getSource().equals(jb2)){
			jb2.setIcon(buttonim2);
		}
		if(e.getSource().equals(jb3)){
			jb3.setIcon(buttonim3);
		}
		if(e.getSource().equals(jb4)){
			jb4.setIcon(buttonim4);
		}
		if(e.getSource().equals(jb5)){
			jb5.setIcon(buttonim5);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {

		if(e.getSource().equals(jb1)){
			jb1.setIcon(newbuttonim1);
			reset(1,jb1);
		}
		if(e.getSource().equals(jb2)){

			jb2.setIcon(newbuttonim2);
			reset(2,jb2);
		}
		if(e.getSource().equals(jb3)){

			jb3.setIcon(newbuttonim3);
			reset(3,jb3);
		}
		if(e.getSource().equals(jb4)){

			jb4.setIcon(newbuttonim4);
			reset(4,jb4);
		}
		if(e.getSource().equals(jb5)){
			jb5.setIcon(newbuttonim5);
			reset(5,jb5);
		}
		
		jf.repaint();

	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	//�÷�������ʹ����button��ԭ�����¼���
	public void reset(int flag,JButton jb){
		
		
		jb.removeMouseListener(this);

		if(Flag != 0){
				bottonreset.get(Flag-1).addMouseListener(this);	
				bottonreset.get(Flag-1).setIcon(bottonresetImage.get(Flag-1));
		}
		
		Flag = flag;
		
	}
}
