package paidmanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.bind.DataBindingException;

public class PaidSendDetail implements MouseListener{

//	public static void main(String[] args) {
//		new PaidSendDetail();
//	}
	
	JFrame jf = null;
	
	JPanel c= null;
	
	JTable jt = null;
	
	JScrollPane jsp = null;
	
	String[] jtTitle = new String[]{"�������","����","��������","��ͨ����","��Ͳ���","ͨ�Ų���","���ϱ���",
			"ʧҵ����","ҽ�Ʊ���","ס��������","��������","Ӧ�۽���"};
	String[][] jtData = new String[][]{
			{"201011701306","����","5000.00","200.00","200.00","200.00","400.00","25.00","103.00","400.00","0.00","0.00"}	
	};
	
	JLabel jl1 = null;
	JLabel jl2 = null;
	JLabel jl3 = null;
	JLabel jl4 = null;
	JLabel jl5 = null;
	
	JTextField jtf = null;
	
	JButton jb = null;
	
	public void init(){
		
		jt = new JTable(jtData,jtTitle){ public boolean isCellEditable(int row, int column) { return false; }};
		jt.getTableHeader().setReorderingAllowed(false);//���ñ�ͷ���ܽ����ƶ�
		
		jsp = new JScrollPane(jt);
		
		jl1 = new JLabel("н�귢�ŵ���ţ�SG1000001");
		jl2 = new JLabel("����������/�����˾/������");
		jl3 = new JLabel("��������3�ˣ�����н���ܶ17064.00��ʵ���ܶ");
		jl4 = new JLabel("�Ǽ���");
		jl5 = new JLabel("�Ǽ����ڣ�"+new Date().toLocaleString());
		
		jl1.setFont(new Font( "΢���ź�",Font.BOLD,12));
		jl2.setFont(new Font( "΢���ź�",Font.BOLD,12));
		jl3.setFont(new Font( "΢���ź�",Font.BOLD,12));
		jl4.setFont(new Font( "΢���ź�",Font.BOLD,12));
		jl5.setFont(new Font( "΢���ź�",Font.BOLD,12));

		jtf = new JTextField();
		
		jb = new JButton("�ύ");
		jb.setFont(new Font( "΢���ź�",Font.BOLD,12));
		jb.setBorder(BorderFactory.createBevelBorder(0));
		jb.addMouseListener(this);
		jb.setBounds(625, 0, 40, 20);
		
		
		jl1.setBounds(0, 50, 749, 20);
		jl2.setBounds(0, 80, 749, 20);
		jl3.setBounds(0, 110, 749, 20);
		jl4.setBounds(0, 140, 50, 20);
		jl5.setBounds(549, 110, 200, 20);
		
		jtf.setBounds(50, 140, 150, 20);
		
		jsp.setBounds(0, 160, 749, 446);
	}
	
	public PaidSendDetail() {
		
		init();
		jf = new JFrame();
		
		c = (JPanel) jf.getContentPane();
		c.setLayout(null);
		
		c.add(jsp);
		c.add(jl1);
		c.add(jl2);
		c.add(jl3);
		c.add(jl4);
		c.add(jl5);
		c.add(jtf);
		c.add(jb);
		
		
		c.setBackground(new Color(247,240,238));
		//����jScrollPanel͸����2�䣩
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		
//		jf.setVisible(true);
//		jf.setSize(749,446);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
