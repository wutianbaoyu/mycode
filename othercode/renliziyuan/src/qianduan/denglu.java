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
	
	//����ͼƬ
	ImageIcon im = null;
	
	//��¼ͼƬ
	ImageIcon img_login = null;
	
	JLabel background = null;
	JLabel passwd = null;
	JLabel jlb1,jlb2=null;
	public static JTextField jtext_acount = null;
	JTextField jtext_password2 = null;
	JPasswordField jtext_password = null;

	
	JButton login_button = null;
	
	JPanel jp_button = null;
	

	
	//Ȩ��
	public static int grade = 1;
	
	public void init(){
		
		im = new ImageIcon("images/1111.png");
		
		background = new JLabel(im);
		background.setBounds(0, 0, im.getIconWidth(), im.getIconHeight());
		
		passwd = new JLabel("��������������");
		
		img_login = new ImageIcon("images/button.png");
		
		jtext_acount = new JTextField("�����������ʺ�");
		jtext_password2 = new JTextField("��������������");
		jtext_password = new JPasswordField();
        jlb1=new JLabel("�û���");  
        jlb2=new JLabel("��    ��");
		//����JText������ɫ
		jtext_acount.setForeground(new Color(148,144,143));
		jtext_password.setForeground(new Color(148,144,143));
		jtext_password2.setForeground(new Color(148,144,143));

		//jtext_acount.setCaretColor(Color.YELLOW);//������ɫ
		
		login_button = new JButton("��½");
		jb=new JButton("����");  
		
		//��λ�������
		jtext_acount.setBounds(40, 245, 150, 24);
		jtext_password2.setBounds(40, 278, 150, 24);
		jtext_password.setBounds(40, 278, 150, 24);
		login_button.setBounds(40,320,img_login.getIconWidth(), img_login.getIconHeight());
		jlb1.setBounds(0,245,150,24);
		jlb2.setBounds(0,278,150,24);
		jb.setBounds(100, 320, 60, 24);

		
		//����JTextField�ı߿�����
		jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
		jtext_password.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
		jtext_password2.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));

	}
	
	public denglu(){
		
		init();
		jf = new JFrame();
		jf.setTitle("������Դ����ƽ̨");
		
		c = (JPanel) jf.getContentPane();
		c.setOpaque(false);	//ʹ����͸��
		c.setLayout(null);//����������ΪNull
				
		//ע�����
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
		

		//��ʼ����Ϊjf
		jf.requestFocus();//��ý��㣨��������ʾ��		

		jf.setVisible(true);
		jf.setSize(200, 400);
		//��JFrame��ʾ����Ļ�м�
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		int height = (dem.height-jf.getHeight())/2;
		int width = (dem.width-jf.getWidth())/2;
		jf.setLocation(width,height);
		
		jf.setResizable(false);//����̶���������
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
			jtext_acount.setText("�����������ʺ�");
		}
		//���ж�������Ƿ���ֵ�����û�У�����ʾΪJTextField
		if(jtext_password.getText().equals("")){
			c.remove(jtext_password);
			c.add(jtext_password2);
			c.validate();//������֤���
			//ˢ��һ��
			jf.repaint();
		}
		if(e.getSource().equals(jb))  
	    {  
	              clear();  
	    }  
		//�����������ʺſ�
		if(e.getSource().equals(jtext_acount)){

			if(jtext_acount.getText().equals("�����������ʺ�")){
				jtext_acount.setText("");
				//�ı�������ɫΪĬ��ֵ
				jtext_acount.setForeground(null);
			}
			
			//��ý���
			jtext_acount.requestFocus();//��ý��㣨��������ʾ��
			//���ʺŵı߿����ɫ�ı�
			jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(97,165,188)));
			//������ı߿�ı���ɫ
			jtext_password2.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
		}
		//���������������
		else if(e.getSource().equals(jtext_password2)){
			//���ʺŵı߿�����Ϊԭ������ɫ
			jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
			//������ı߿�ı���ɫ
			jtext_password.setBorder(BorderFactory.createLineBorder(new Color(97,165,188)));
			//��JText�Ƴ�����ô��ʾ�ļ�ΪJPasswordField
			c.remove(jtext_password2);
			c.add(jtext_password);
			c.validate();//������֤���
			//�ı������������ɫ
			jtext_password.setForeground(null);
			
			jtext_password.requestFocus();//��ý��㣨��������ʾ��
			
			//ˢ��һ�飬ʹJText�е�����ȥ��
			jf.repaint();
		}
		//���������ǵ�¼��ť
		else if(e.getSource().equals(login_button)){
			
			yonhuyanzheng uc = new yonhuyanzheng();
			
			if(uc.checkUser(jtext_acount.getText(), jtext_password.getText())){
				
				//��Ȩ�޷ŵ�һ��������
				grade = uc.getGrade(jtext_acount.getText());
				
				
				jf.setVisible(false);
				
			}else{
				JOptionPane.showMessageDialog(jf, "�û������������.");
			}
			//JOptionPane.showMessageDialog(null, "�����ʺ���"+jtext_acount.getText()+"\r\n����������"+jtext_password.getText()+"", "�û���¼", JOptionPane.INFORMATION_MESSAGE);

		}
		//�������������
		else if(e.getSource().equals(jf)){			
			
			//���߿���ɫ����Ϊԭ������ɫ
			jtext_acount.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
			jtext_password2.setBorder(BorderFactory.createLineBorder(new Color(215,215,215)));
			
			//��ý���
			jf.requestFocus();//��ý��㣨��������ʾ��

		}

	}
	private void clear() {
		// TODO Auto-generated method stub
		jtext_acount.setForeground(new Color(148,144,143));
		jtext_acount.setText("�����������ʺ�");
		jtext_password.setForeground(new Color(148,144,143));
		jtext_password.setText("");

	}
}
