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

import login.denglu;

import com.modelCl.OrganizationCl;
import com.modelCl.PaidProjectCl;
import register.FirstJPanel;

public class PaidLogin implements ActionListener,MouseListener{
	
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
	
	JScrollPane jsp = null;
	
	JTable jTable = null;	
	
	JTextField jt1 = null;
	//JTextField jt2 = null;
	JTextField jt3 = null;
	JTextField jt4 = null;
	JTextField jt5 = null;
	JTextField jt6 = null;
	
	JButton jb = null;
	
	JScrollPane jsptable = null;
	
	PaidProjectCl ppc = new PaidProjectCl();
	
	JComboBox jcombobox = new JComboBox(ppc.getPositionByPaid());
	JComboBox jcombobox2 = new JComboBox(ppc.getPaidProjectByPosition(jcombobox.getSelectedItem().toString()));
		
	Vector<String> jtTitle = new Vector<String>();
	
	Vector<Vector<Object>> jtdata = ppc.getPaidProjectName(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString());
	
	public void init(){
		
		jtTitle.add("序号");
		jtTitle.add("酬薪项目名称");
		jtTitle.add("金额");
		
		jb = new JButton("提交");
		jb.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jb.setBorder(BorderFactory.createBevelBorder(0));
		
		jl1 = new JLabel("酬薪标准编号");
		jl2 = new JLabel("酬薪标准名称");
		jl3 = new JLabel("酬薪总额");
		jl4 = new JLabel("制定人");
		jl5 = new JLabel("登记人");
		jl6 = new JLabel("登记时间");
		jl7 = new JLabel("职位");
				
		jl1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl6.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl7.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		jp1 = new JPanel();
		jp1.setLayout(null);

		jp2 = new JPanel(new BorderLayout());
		
		DefaultTableModel model = new DefaultTableModel(jtdata,jtTitle);
		
		jTable = new JTable(model){
			public boolean isCellEditable(int row, int column) {
				if(column == 2){
					return true;
				}else{
					return false; 
				}
		}};
		jTable.getTableHeader().setReorderingAllowed(false);//设置表头不能交换移动
		// 设置table内容居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, tcr);
		
		jsptable = new JScrollPane(jTable);
		
		jt1 = new JTextField();
		//jt2 = new JTextField();
		jt3 = new JTextField("0.00");
		jt4 = new JTextField(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
		jt5 = new JTextField(denglu.jtext_acount.getText());
		jt6 = new JTextField(new Date().toLocaleString());
		
		jl7.setBounds(0, 0, 80, 25);
		jcombobox.setBounds(80, 0, 169, 25);
		jl1.setBounds(0, 25,80, 25);
		jt1.setBounds(80, 25, 169, 25);
		jl2.setBounds(249, 25, 80, 25);
		jcombobox2.setBounds(329, 25, 169, 25);
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
		
		jp1.add(jt1);
		jp1.add(jcombobox2);
		jp1.add(jt3);
		jp1.add(jt4);
		jp1.add(jt5);
		jp1.add(jt6);
		
		jp1.add(jcombobox);
		
		jp1.setPreferredSize(new Dimension(749,75));
		jp2.add(jp1,BorderLayout.NORTH);
		jp2.add(jsptable,BorderLayout.CENTER);
		
		jcombobox.addActionListener(this);
		jcombobox2.addActionListener(this);
		jsptable.addMouseListener(this);
		jTable.addMouseListener(this);
		jp2.addMouseListener(this);
		jb.addMouseListener(this);
		setPaidNo();
	}

	public PaidLogin(){
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

				//Vector<String> towVector = ppc.selPaidProject(jcombobox.getSelectedIndex()+1);
				Vector<String> towVector = ppc.getPaidProjectByPosition(jcombobox.getSelectedItem().toString());
				
				jcombobox2.removeAllItems();
				
				for(int i=0;i<towVector.size();i++){
					jcombobox2.addItem(towVector.get(i));
				}
				
				jt4.setText(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
				jtdata = ppc.getPaidProjectName(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString());
			
				//重新改变table的model，使数据改变
				jTable.setModel(new DefaultTableModel(jtdata,jtTitle));
				jTable.repaint();
			}
		}
		
	}

	public boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
		Matcher isNum = pattern.matcher(str);
		
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	} 
	@Override
	public void mouseClicked(MouseEvent e) {
		float Salary = 0.00f;
		float total = 0.00f;
		
		if(!jTable.isEditing()){
			for(int i=0,size=jTable.getRowCount();i<size;i++){					
				
				String newSalary = jTable.getValueAt(i, 2).toString();	
				
				if(newSalary.equals("")){

					jTable.setValueAt("0.00", i, 2);
				}				
				else if(isNumeric(newSalary)){
					jTable.setValueAt(newSalary, i, 2);
				}else{
					JOptionPane.showMessageDialog(jf, "请在金额上输入有效的数字(保留2位小数).");
					jTable.editCellAt(i,2);
					jTable.requestFocusInWindow();
					jTable.requestFocus();
					return;
				}	
			}
		}
		
		if(jTable.getCellEditor() != null){
			
			jTable.getCellEditor().stopCellEditing(); 
			
			//得到表格中的金额，截取小数点后2位,如果不输入,就默认为0.00
			for(int i=0,size=jTable.getRowCount();i<size;i++){					
				
				String newSalary = jTable.getValueAt(i, 2).toString();	
				
				if(newSalary.equals("")){

					jTable.setValueAt("0.00", i, 2);
				}				
				else if(isNumeric(newSalary)){
					jTable.setValueAt(newSalary, i, 2);
				}else{
					JOptionPane.showMessageDialog(jf, "请在金额上输入有效的数字(保留2位小数).");
					jTable.editCellAt(i,2);
					jTable.requestFocusInWindow();
					jTable.requestFocus();
					return;
				}				
				
				//得到3险一金
				String ThreeAndOne = jTable.getValueAt(i, 1).toString();
				
				
				
				if(ThreeAndOne.equals("基本工资")){
					Salary = Float.parseFloat(jTable.getValueAt(i,2).toString());
					jTable.setValueAt(new BigDecimal(Salary).setScale(2, BigDecimal.ROUND_DOWN), i, 2);
				}
				else if(ThreeAndOne.equals("养老保险")){
					//System.out.println(new BigDecimal(Salary*0.08).setScale(2, BigDecimal.ROUND_DOWN));
					jTable.setValueAt(new BigDecimal(Salary*0.08).setScale(2, BigDecimal.ROUND_DOWN), i, 2);
					
				}else if(ThreeAndOne.equals("失业保险")){

					jTable.setValueAt(new BigDecimal(Salary*0.02+3).setScale(2, BigDecimal.ROUND_DOWN), i, 2);

				}else if(ThreeAndOne.equals("医疗保险")){
					
					jTable.setValueAt(new BigDecimal(Salary*0.005).setScale(2, BigDecimal.ROUND_DOWN), i, 2);
				}else if(ThreeAndOne.equals("住房公积金")){
					
					jTable.setValueAt(new BigDecimal(Salary*0.08).setScale(2, BigDecimal.ROUND_DOWN), i, 2);
				}else{
					jTable.setValueAt(new BigDecimal(Float.parseFloat(jTable.getValueAt(i,2).toString())).setScale(2, BigDecimal.ROUND_DOWN), i, 2);
				}

				total = Float.parseFloat(jTable.getValueAt(i, 2).toString())+total;				
			}
			//更新总金额
			
			jt3.setText(new BigDecimal(total).setScale(2,BigDecimal.ROUND_DOWN)+"");
		}
		else if(e.getSource().equals(jb)){
			
			//提交，加入到数据库
			if(ppc.updatePaidProjectTotal(jt3.getText(), jcombobox.getSelectedItem().toString(), jcombobox2.getSelectedItem().toString(),jTable,jt5.getText(),jt6.getText())){
				JOptionPane.showMessageDialog(jf, "提交成功.");
				
				//更新一遍数据
				//jcombobox = new JComboBox(ppc.getPositionByPaid());
				
				jcombobox.removeAllItems();
				jcombobox2.removeAllItems();
				
				for(int i=0,size=ppc.getPositionByPaid().size();i<size;i++){
					jcombobox.addItem(ppc.getPositionByPaid().get(i));
				}
				for(int i=0,size=ppc.getPaidProjectByPosition(jcombobox.getSelectedItem().toString()).size();i<size;i++){
					jcombobox2.addItem(ppc.getPaidProjectByPosition(jcombobox.getSelectedItem().toString()).get(i));
				}

				jtdata = ppc.getPaidProjectName(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString());
				jTable.setModel(new DefaultTableModel(jtdata,jtTitle));
				jTable.repaint();
				jt3.setText("0.00");
				jt4.setText(ppc.getMaker(jcombobox.getSelectedItem().toString(),jcombobox2.getSelectedItem().toString()));
				jt5.setText("heyuewei");
				jt6.setText(new Date().toLocaleString());
				jf.repaint();
			}else{
				JOptionPane.showMessageDialog(jf, "提交失败!");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
