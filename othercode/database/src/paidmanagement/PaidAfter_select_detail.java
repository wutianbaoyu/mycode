package paidmanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import login.zhujiemian;

import com.modelCl.PaidProjectCl;
import com.modelCl.PaidProjectRegCl;


public class PaidAfter_select_detail implements ActionListener{
	
//	public static void main(String[] args) {
//		new PaidLogin();
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

	
	JPanel jp1 = null;
	JPanel jp2 = null;
	
	//JScrollPane jsp = null;
	
	public static JTable jTable = null;	
	
	public static JTextField jt1 = null;
	public static JTextField jt2 = null;
	public static JTextField jt3 = null;
	public static JTextField jt4 = null;
	public static JTextField jt5 = null;
	public static JTextField jt6 = null;
	public static JTextField jt7 = null;
	//JTextArea ja = null;
	
	JButton jb = null;
	
	JScrollPane jsptable = null;
		
	public static Vector<String> jtTitle = null;
	
	PaidProjectRegCl pprc = new PaidProjectRegCl();
	
	Vector<Vector<Object>> jtdata = null;
	
	public void init(int iPaidProjectNameNo){
		
		jtdata = pprc.getTableMsg(iPaidProjectNameNo);
		
		jtTitle = new Vector<String>();
		
		jtTitle.add("ÐòºÅ");
		jtTitle.add("³êÐ½ÏîÄ¿Ãû³Æ");
		jtTitle.add("½ð¶î");
		
		jb = new JButton("·µ»Ø");
		jb.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		jb.setBorder(BorderFactory.createBevelBorder(0));
		
		jl1 = new JLabel("³êÐ½±ê×¼±àºÅ");
		jl2 = new JLabel("³êÐ½±ê×¼Ãû³Æ");
		jl3 = new JLabel("³êÐ½×Ü¶î");
		jl4 = new JLabel("ÖÆ¶¨ÈË");
		jl5 = new JLabel("µÇ¼ÇÈË");
		jl6 = new JLabel("µÇ¼ÇÊ±¼ä");
		jl7 = new JLabel("Ö°Î»");
		//jl8 = new JLabel("¸´ºËÒâ¼û");
		
		jl1.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		jl2.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		jl3.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		jl4.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		jl5.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		jl6.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		jl7.setFont(new Font( "Î¢ÈíÑÅºÚ",Font.BOLD,12));
		
		jp1 = new JPanel();
		jp1.setLayout(null);

		jp2 = new JPanel(new BorderLayout());
		System.out.println(jtTitle.size());
		DefaultTableModel model = new DefaultTableModel(jtdata,jtTitle);
		
		jTable = new JTable(model){
			public boolean isCellEditable(int row, int column) {
				return false; 
		}};
		jTable.getTableHeader().setReorderingAllowed(false);//ÉèÖÃ±íÍ·²»ÄÜ½»»»ÒÆ¶¯
		// ÉèÖÃtableÄÚÈÝ¾ÓÖÐ
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, tcr);
		
		jsptable = new JScrollPane(jTable);
		
		jt1 = new JTextField();
		jt2 = new JTextField();
		jt3 = new JTextField();
		jt4 = new JTextField();
		jt5 = new JTextField();
		jt6 = new JTextField();
		jt7 = new JTextField();
		
		jl7.setBounds(0, 0, 80, 25);
		jt7.setBounds(80, 0, 169, 25);
		jl1.setBounds(0, 25,80, 25);
		jt1.setBounds(80, 25, 169, 25);
		jl2.setBounds(249, 25, 80, 25);
		jt2.setBounds(329, 25, 169, 25);
		jl3.setBounds(498, 25, 80, 25);
		jt3.setBounds(578, 25, 169, 25);

		
		jl4.setBounds(0, 50,80, 25);
		jt4.setBounds(80, 50, 169, 25);
		jl5.setBounds(249, 50, 80, 25);
		jt5.setBounds(329, 50, 169, 25);
		jl6.setBounds(498, 50, 80, 25);
		jt6.setBounds(578, 50, 169, 25);
		
		jl1.setBorder(BorderFactory.createEtchedBorder());
		jl2.setBorder(BorderFactory.createEtchedBorder());
		jl3.setBorder(BorderFactory.createEtchedBorder());
		jl4.setBorder(BorderFactory.createEtchedBorder());
		jl5.setBorder(BorderFactory.createEtchedBorder());
		jl6.setBorder(BorderFactory.createEtchedBorder());
		jl7.setBorder(BorderFactory.createEtchedBorder());

		
		jt1.setEditable(false);
		jt2.setEditable(false);
		jt3.setEditable(false);
		jt4.setEditable(false);
		jt5.setEditable(false);
		jt6.setEditable(false);		
		jt7.setEditable(false);		
		
		jt1.setBackground(Color.white);
		jt2.setBackground(Color.white);
		jt3.setBackground(Color.white);
		jt4.setBackground(Color.white);
		jt5.setBackground(Color.white);
		jt6.setBackground(Color.white);
		jt7.setBackground(Color.white);
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		jp1.add(jl7);
		
		jp1.add(jt1);
		jp1.add(jt2);
		jp1.add(jt3);
		jp1.add(jt4);
		jp1.add(jt5);
		jp1.add(jt6);
		jp1.add(jt7);

		
		jp1.setPreferredSize(new Dimension(749,75));
		jp2.add(jp1,BorderLayout.NORTH);
		jp2.add(jsptable,BorderLayout.CENTER);

		jb.addActionListener(this);

	}

	public PaidAfter_select_detail(int iPaidProjectNameNo){
		init(iPaidProjectNameNo);
		jf = new JFrame();
		
		c = (JPanel) jf.getContentPane();
		c.setLayout(null);

		jb.setBounds(625, 0, 40, 20);
		jp2.setBounds(0, 50, 748, 449);
		
		c.add(jb);
		c.add(jp2);
		c.setBackground(new Color(247,240,238));
		jp2.setBackground(new Color(247,240,238));
		jp1.setBackground(new Color(247,240,238));
		
		//ÉèÖÃjScrollPanelÍ¸Ã÷£¨2¾ä£©
		jsptable.setOpaque(false);
		jsptable.getViewport().setOpaque(false); 

//		jf.setVisible(true);
//		jf.setSize(749, 449);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(jb)){
	
			zhujiemian.xiaoGuo(PaidAfter_select.c,"->³êÐ½±ê×¼¹ÜÀí->³êÐ½±ê×¼²éÑ¯");
		}
		
	}


}

