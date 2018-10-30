package paidmanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.modelCl.OrganizationCl;
import com.modelCl.PaidProjectCl;
import com.modelCl.PaidProjectRegCl;
import register.FirstJPanel;

public class PaidReg implements ActionListener{
	
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
	JLabel jl8 = null;
	
	JPanel jp1 = null;
	JPanel jp2 = null;
	
	JScrollPane jsp = null;
	
	JTable jTable = null;	
	
	JTextField jt1 = null;
	JTextField jt3 = null;
	JTextField jt4 = null;
	JTextField jt5 = null;
	JTextField jt6 = null;
	JTextArea ja = null;
	
	JButton jb = null;
	
	JScrollPane jsptable = null;
	
	PaidProjectCl ppc = new PaidProjectCl();
	PaidProjectRegCl pprc = new PaidProjectRegCl();
	
	JComboBox jcombobox = new JComboBox(pprc.selPaidProjectByReg());
	JComboBox jcombobox2 = new JComboBox(pprc.getPaidProjectNameByPosition(jcombobox.getSelectedItem().toString()));
		
	Vector<String> jtTitle = new Vector<String>();
	
	Vector<Vector<Object>> jtdata = ppc.getPaidProjectName(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString());
	
	public void init(){
		
		jtTitle.add("序号");
		jtTitle.add("酬薪项目名称");
		jtTitle.add("金额");
		
		jb = new JButton("复核");
		jb.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jb.setBorder(BorderFactory.createBevelBorder(0));
		
		jl1 = new JLabel("酬薪标准编号");
		jl2 = new JLabel("酬薪标准名称");
		jl3 = new JLabel("酬薪总额");
		jl4 = new JLabel("制定人");
		jl5 = new JLabel("登记人");
		jl6 = new JLabel("复核时间");
		jl7 = new JLabel("职位");
		jl8 = new JLabel("复核意见");
		
		jl1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl6.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl7.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl8.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		ja = new JTextArea();
		jsp = new JScrollPane(ja);
		jsp.setBorder(BorderFactory.createLineBorder(Color.black));
		
		jp1 = new JPanel();
		jp1.setLayout(null);

		jp2 = new JPanel(new BorderLayout());
		
		DefaultTableModel model = new DefaultTableModel(jtdata,jtTitle);
		
		jTable = new JTable(model){
			public boolean isCellEditable(int row, int column) {
				return false; 
		}};
		jTable.getTableHeader().setReorderingAllowed(false);//设置表头不能交换移动
		// 设置table内容居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, tcr);
		
		jsptable = new JScrollPane(jTable);
		
		jt1 = new JTextField();
		//jt2 = new JTextField();
		jt3 = new JTextField(pprc.getTotal(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString()));
		jt4 = new JTextField(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
		jt5 = new JTextField(pprc.getRegistrant(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString()));
		jt6 = new JTextField(new Date().toLocaleString());
		
		jl7.setBounds(0, 0, 80, 25);
		jcombobox.setBounds(80, 0, 169, 25);
		jl1.setBounds(0, 25,80, 25);
		jt1.setBounds(80, 25, 169, 25);
		jl2.setBounds(249, 25, 80, 25);
		jcombobox2.setBounds(329, 25, 169, 25);
		jl3.setBounds(498, 25, 80, 25);
		jt3.setBounds(578, 25, 169, 25);
		jsp.setBounds(80, 75, 669, 60);
		jl8.setBounds(0, 75, 80, 60);
		
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
		jl8.setBorder(BorderFactory.createEtchedBorder());
		
		jcombobox.setBorder(BorderFactory.createEtchedBorder());
		jcombobox.setBackground(Color.white);
		jcombobox2.setBorder(BorderFactory.createEtchedBorder());
		jcombobox2.setBackground(Color.white);
		
		jt1.setEditable(false);
		//jt2.setEditable(false);
		jt3.setEditable(false);
		jt4.setEditable(false);
		jt5.setEditable(false);
		jt6.setEditable(false);		
		
		jt1.setBackground(Color.white);
		//jt2.setBackground(Color.white);
		jt3.setBackground(Color.white);
		jt4.setBackground(Color.white);
		jt5.setBackground(Color.white);
		jt6.setBackground(Color.white);
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		jp1.add(jl7);
		jp1.add(jl8);
		jp1.add(jsp);
		
		jp1.add(jt1);
		jp1.add(jcombobox2);
		jp1.add(jt3);
		jp1.add(jt4);
		jp1.add(jt5);
		jp1.add(jt6);
		
		jp1.add(jcombobox);
		
		jp1.setPreferredSize(new Dimension(749,135));
		jp2.add(jp1,BorderLayout.NORTH);
		jp2.add(jsptable,BorderLayout.CENTER);
		
		jcombobox.addActionListener(this);
		jcombobox2.addActionListener(this);
		jb.addActionListener(this);

		setPaidNo();
	}

	public PaidReg(){
		init();
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
		
		//设置jScrollPanel透明（2句）
		jsptable.setOpaque(false);
		jsptable.getViewport().setOpaque(false); 

//		jf.setVisible(true);
//		jf.setSize(749, 449);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void setPaidNo(){
		int PaidNo = 1;
		if(jcombobox.getSelectedItem() != null){

			if(jcombobox2.getSelectedItem() != null){

				PaidNo = ppc.getPaidNo(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString());			
				jt4.setText(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
				jtdata = ppc.getPaidProjectName(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString());
				
				//重新改变table的model，使数据改变
				jTable.setModel(new DefaultTableModel(jtdata,jtTitle));
				jTable.repaint();
				
				jt3.setText(pprc.getTotal(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString()));				
				jt4.setText(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
				jt5.setText(pprc.getRegistrant(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString()));
			}
			
		
			
		}
		
		jt1.setText(PaidNo+"");
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(jTable.getCellEditor() != null){
			jTable.getCellEditor().stopCellEditing(); 
		}		
		
		if(e.getSource().equals(jcombobox2)){
				setPaidNo();

		}
		else if(e.getSource().equals(jcombobox)){

			if(jcombobox.getSelectedItem() != null){

				Vector<String> towVector = pprc.getPaidProjectNameByPosition(jcombobox.getSelectedItem().toString());
				
				jcombobox2.removeAllItems();
				
				for(int i=0;i<towVector.size();i++){
					jcombobox2.addItem(towVector.get(i));
				}
				
				jt4.setText(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
				jt5.setText(pprc.getRegistrant(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString()));
				jtdata = ppc.getPaidProjectName(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString());
			
				//重新改变table的model，使数据改变
				jTable.setModel(new DefaultTableModel(jtdata,jtTitle));
				jTable.repaint();
			}
		}else if(e.getSource().equals(jb)){
			
			//提交复核
			if(pprc.updatePaidMessge(jt6.getText(), 4, ja.getText(), jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(jf, "复核成功.");
				
				//更新一遍数据
				jcombobox.removeAllItems();
				jcombobox2.removeAllItems();
				
				for(int i=0,size=pprc.selPaidProjectByReg().size();i<size;i++){
					jcombobox.addItem(pprc.selPaidProjectByReg().get(i));
				}
				for(int i=0,size=pprc.getPaidProjectNameByPosition(jcombobox.getSelectedItem().toString()).size();i<size;i++){
					jcombobox2.addItem(pprc.getPaidProjectNameByPosition(jcombobox.getSelectedItem().toString()).get(i));
				}
				
				jtdata = ppc.getPaidProjectName(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString());
				jTable.setModel(new DefaultTableModel(jtdata,jtTitle));
				jTable.repaint();
				
				jt3.setText(pprc.getTotal(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString()));				
				jt4.setText(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
				jt5.setText(pprc.getRegistrant(jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString()));
				jt6.setText(new Date().toLocaleString());
				ja.setText("");
				jf.repaint();
				
			}else{
				JOptionPane.showMessageDialog(jf, "复核失败!");
			}
		}
		
	}


}
