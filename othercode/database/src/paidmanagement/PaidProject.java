package paidmanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import login.denglu;

import com.modelCl.OrganizationCl;
import com.modelCl.PaidProjectCl;
import register.FirstJPanel;
public class PaidProject implements ActionListener,MouseListener{


//	public static void main(String[] args) {
//		new PaidProject();
//
//	}
	JFrame jf = null;
	
	public static JPanel c = null;
	
	JLabel jl1 = null;
	JLabel jl2 = null;
	JLabel jl3 = null;
	JLabel jl4 = null;

	JPanel jp1 = null;
	JPanel jp2 = null;
	
	JScrollPane jsp = null;
	
	JTable jtable = null;
	
	Vector<String> jtTitle = new Vector<String>();
	Vector<Vector<String>> jtData = new Vector<Vector<String>>();
	
	JTextField jt1 = null;
	
	JButton jb = null;
	JButton jb_add = null;
	
	JScrollPane jsptable = null;
	
	DefaultTableModel model = null;
	
	OrganizationCl oc = new OrganizationCl(); 
	
	JComboBox jcombobox = new JComboBox(oc.getPositionClassification());
	JComboBox jcombobox2 = new JComboBox(oc.getPosition(1));

	
	PaidProjectCl ppc = new PaidProjectCl();
	
	Vector<String> vec = ppc.selPaidProject(jcombobox.getSelectedItem().toString());

	public void init(){
		
		reget(1,"证券");
				
		jtTitle.add("序号");
		jtTitle.add("酬薪项目名称");
		jtTitle.add("");
		
		jb = new JButton("登记");
		jb_add = new JButton("增加");
		jb.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jb_add.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jb.setBorder(BorderFactory.createBevelBorder(0));
		jb_add.setBorder(BorderFactory.createBevelBorder(0));
		
		jl1 = new JLabel("制定人",0);
		jl2 = new JLabel("职位",0);
		jl3 = new JLabel("酬薪标准名称",0);
		jl4 = new JLabel("(*号表示该酬薪标准已被制定)");
				
		jl1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jl4.setFont(new Font( "微软雅黑",Font.BOLD,10));
		jcombobox.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jcombobox2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		jp1 = new JPanel();
		jp1.setLayout(null);
		jp2 = new JPanel(new BorderLayout());
		
		
		model = new DefaultTableModel(jtData,jtTitle); 
		jtable = new JTable(model){ public boolean isCellEditable(int row, int column){			
			if(column == 1){
				return true; 
			}else{
				return false;
			}
		}};
		
		// 设置table内容居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		jtable.setDefaultRenderer(Object.class, tcr);
		
		jtable.getTableHeader().setReorderingAllowed(false);//设置表头不能交换移动	
		
		jtable.setRowHeight(20);
		
		jsptable = new JScrollPane(jtable);
		
		jt1 = new JTextField(denglu.jtext_acount.getText());

		
		jl1.setBounds(0, 0,100, 25);
		jt1.setBounds(100, 0, 100, 25);
		jl2.setBounds(200, 0, 80, 25);
		jcombobox.setBounds(280, 0, 120, 25);
		jl3.setBounds(400, 0, 80, 25);
		jcombobox2.setBounds(480, 0, 120, 25);
		jl4.setBounds(603, 50, 140, 25);
		
		jl1.setBorder(BorderFactory.createEtchedBorder());
		jl2.setBorder(BorderFactory.createEtchedBorder());
		jl3.setBorder(BorderFactory.createEtchedBorder());

		jcombobox.setBorder(BorderFactory.createEtchedBorder());
		jcombobox.setBackground(Color.white);
		jcombobox2.setBorder(BorderFactory.createEtchedBorder());
		jcombobox2.setBackground(Color.white);
		
		jt1.setEditable(false);
		jt1.setBackground(Color.white);
		jt1.setBorder(BorderFactory.createBevelBorder(1));

		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jt1);
		jp1.add(jcombobox);
		jp1.add(jcombobox2);

		
		jp1.setPreferredSize(new Dimension(749,40));
		jp2.add(jp1,BorderLayout.NORTH);
		jp2.add(jsptable,BorderLayout.CENTER);
		
		jb.setBounds(625, 0, 40, 20);
		jb_add.setBounds(625, 90, 40, 20);
		jp2.setBounds(0, 50, 600, 449);
		jp2.setBounds(0,50,600, 300);
		
		jb_add.addActionListener(this);
		jtable.addMouseListener(this);
		jcombobox.addActionListener(this);				
		jsptable.addMouseListener(this);				
		jb.addActionListener(this);				

	}

	public PaidProject(){
		init();
		jf = new JFrame();
		
		c = (JPanel) jf.getContentPane();
		c.setLayout(null);
		
		c.add(jb);
		c.add(jb_add);
		c.add(jp2);
		c.add(jl4);
		c.setBackground(new Color(247,240,238));
		jp2.setBackground(new Color(247,240,238));
		jp1.setBackground(new Color(247,240,238));
		
		//设置jScrollPanel透明（2句）
		jsptable.setOpaque(false);
		jsptable.getViewport().setOpaque(false); 
		
//		jf.setVisible(true);
//		jf.setSize(749, 449);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.addMouseListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(jtable.getCellEditor() != null){
			jtable.getCellEditor().stopCellEditing(); 
		}
		else if(e.getSource().equals(jb_add)){
			
			Vector<String> vector = new Vector<String>();
			vector.add(jtData.size()+1+"");
			vector.add("");
			vector.add("删除");
			jtData.add(vector);
			model = new DefaultTableModel(jtData,jtTitle);
			jtable.setModel(model);
			jtable.repaint();
			
		}
		else if(e.getSource().equals(jcombobox)){
			
			reget(jcombobox.getSelectedIndex()+1,jcombobox.getSelectedItem().toString());
		}
		else if(e.getSource().equals(jb)){
			
			
			int a = JOptionPane.showConfirmDialog(jf,"你确定要登记吗？","", JOptionPane.YES_NO_OPTION);
			
			if(a == 0){				
				
				if(jtData.size() == 0){
					JOptionPane.showMessageDialog(jf, "请完善内容再登记.");
					return;
				}
				for(int i=0,size=jtData.size();i<size;i++){
					if(jtData.get(i).get(1).toString().equals("")){
						JOptionPane.showMessageDialog(jf, "请完善内容再登记.");
						return;
					}
				}
				
				int index = jcombobox2.getSelectedItem().toString().lastIndexOf('(');
				
				//截取*
				String tig = jcombobox2.getSelectedItem().toString().substring(index+1,index+2);

				if(tig.equals("*")){
					JOptionPane.showMessageDialog(jf, "该酬薪项目已被登记过.");
					return;
				}
				
				//提交，加进数据库
				if(jcombobox.getSelectedItem() != null){
					String nvPaidProjectPositionName = jcombobox.getSelectedItem().toString();
					String nvPaidProjectName = jcombobox2.getSelectedItem().toString();
					String nvPaidProjectMaker =  jt1.getText();
					int iState = 1;
					
					
					if(ppc.addPaidProject(nvPaidProjectPositionName, nvPaidProjectName, nvPaidProjectMaker, iState, jtable)){
						
						//提交成功
						JOptionPane.showMessageDialog(jf, "登记成功.");
						int selectindex = jcombobox2.getSelectedIndex();

						reget(jcombobox.getSelectedIndex()+1,jcombobox.getSelectedItem().toString());
						
						//保持原来的酬薪选项
						jcombobox2.setSelectedIndex(selectindex);
						
					}else{
						JOptionPane.showMessageDialog(jf, "登记失败.");
					}
				}
				
			}
			
		}

		
	}

	public void reget(int index,String Paidname){
		if(jcombobox.getSelectedItem() != null){

			Vector<String> towVector = oc.getPosition(index);
			
			vec = ppc.selPaidProject(Paidname);
			
			if(vec.size() > 0){
				for(int i=0,size1=towVector.size();i<size1;i++){
					for(int j=0,size2=vec.size();j<size2;j++){
						if(towVector.get(i).equals(vec.get(j))){
							towVector.setElementAt(towVector.get(i)+"(*)", i);
						}
					}
				}
			}
			
			jcombobox2.removeAllItems();
			
			for(int i=0;i<towVector.size();i++){
				jcombobox2.addItem(towVector.get(i));
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {

			if(jtable.getCellEditor() != null){
				jtable.getCellEditor().stopCellEditing(); 
			}


		if(e.getSource().equals(jtable)){
			
			int column = jtable.getSelectedColumn();//可以获得你选中的列
			int row = jtable.getSelectedRow();//可以获得你选择的行
			
			if(column == 2){
				
				int a = JOptionPane.showConfirmDialog(jf,"你确定要删除吗？","", JOptionPane.YES_NO_OPTION);
				
				if(a == 0){
					jtData.remove(row);
					
					Vector<String> vector = new Vector<String>();
					
					for(int i=0;i<jtData.size();i++){
						jtData.get(i).setElementAt(i+1+"", 0);
					}				
					
					model = new DefaultTableModel(jtData,jtTitle);
					jtable.setModel(model);
					jtable.repaint();
				}
				
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
