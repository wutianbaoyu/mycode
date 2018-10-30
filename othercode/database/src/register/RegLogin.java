/*
 * 复核登记
 */
package register;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import audit.*;
import com.model.CasesBean;
import com.modelCl.CasesCl;

import login.zhujiemian;


public class RegLogin implements ActionListener{

	CasesCl cc = new CasesCl();
	//得到审核数
	int pageRegCount = cc.getRegCount();
	
	//第一页
	int pageNow = 1;
	//得到总页数
	int pageCount = cc.getPageCount();
	
	@SuppressWarnings("rawtypes")
	//默认显示第一页
	Vector al = cc.getRegCasesByPage(pageNow);
	//得到返回来要显示的信息
	
	JFrame jf = null;
	
	public JPanel c = null;
	JPanel jp = null;
	
	JScrollPane jsp = null;
	
	JLabel title = null;
	JLabel showpage = null;
	
	JTable table = null;
	
	JButton pre = null;
	JButton next = null;

	
	ImageIcon iicon1 = new ImageIcon("images/button1.gif");
	ImageIcon iicon2 = new ImageIcon("images/button2.gif");
	ImageIcon iicon3 = new ImageIcon("images/button3.gif");
	ImageIcon iicon4 = new ImageIcon("images/button4.gif");
	
	Vector<String> tableTitle = new Vector<String>();
	
	public static String iCaseNo = "";
	
	@SuppressWarnings("serial")
	public void init(){
		tableTitle.add("档案编号");
		tableTitle.add("一级机构");
		tableTitle.add("二级机构");
		tableTitle.add("三级机构");
		tableTitle.add("职位名称");
		tableTitle.add("姓名");
		tableTitle.add("性别");
		tableTitle.add("复核");
		DefaultTableModel model = new DefaultTableModel(al,tableTitle);
		
		//实例化table，并且让它不能编辑，重写isCellEditable方法
		table = new JTable(model){ public boolean isCellEditable(int row, int column) { return false; }};
		
		table.getTableHeader().setReorderingAllowed(false);//设置表头不能交换移动
		
		table.setRowHeight(20);//设置行距
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);

		table.setDefaultRenderer(Object.class, tcr);
		//设置边框颜色
		table.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		//设置“复核”字体颜色
		TableColumn tableColumn = table.getColumn("复核");
		DefaultTableCellRenderer tcrcolor = new DefaultTableCellRenderer();
		tcrcolor.setForeground(Color.blue);
		tcrcolor.setHorizontalAlignment(JLabel.CENTER);
		tableColumn.setCellRenderer(tcrcolor);
		
		jsp = new JScrollPane(table);
		
		title = new JLabel("当前等待复核的人力资源档案总数："+pageRegCount+"例");
		showpage = new JLabel(""+pageNow+"/"+pageCount+"");
		
		title.setFont(new Font( "微软雅黑",Font.BOLD,14));
		showpage.setFont(new Font( "微软雅黑",Font.BOLD,12));
		showpage.setBounds(458, 325, 20, 20);
		
		next = new JButton(iicon1);
		pre = new JButton(iicon4);

		next.setBounds(502+iicon4.getIconWidth()+20, 326, iicon1.getIconWidth(), iicon1.getIconHeight());
		pre.setBounds(502, 326, iicon4.getIconWidth(), iicon4.getIconHeight());
		
		jp = new JPanel(new BorderLayout());		
		jp.setBounds(0, 50, 749, 244);

		jsp.setBorder(BorderFactory.createLineBorder(new Color(247,240,238)));
		//注册
		next.addActionListener(this);
		pre.addActionListener(this);

		
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			
			public void mouseClicked(java.awt.event.MouseEvent e){
				
				int column = table.getSelectedColumn();//可以获得你选中的列
				int row = table.getSelectedRow();//可以获得你选择的行


				if(column == 7){
					First_Area_au faa = new First_Area_au();
					
					iCaseNo = (String) table.getModel().getValueAt(row, 0);
					
					CasesBean cb = cc.getRegMessage(iCaseNo);
					//将信息提前准备好
					First_Area_au.jlabel2.setText("档案号："+cb.getiNo());
					FirstJPanel_au.msgJLable1.setText(cb.getNvOneOrganizationName());
					FirstJPanel_au.msgJLable2.setText(cb.getNvTwoOrganizationName());				
					FirstJPanel_au.msgJLable3.setText(cb.getNvPositionClassificationName());
					FirstJPanel_au.msgJLable4.setText(cb.getNvPositionName());				
					FirstJPanel_au.jtext1.setText(cb.getNvName());
					FirstJPanel_au.jcombobox5.setSelectedItem(cb.getNcSex());			
					FirstJPanel_au.jtext2.setText(cb.getiPhone());
					FirstJPanel_au.jtext3.setText(cb.getiQQ());
					FirstJPanel_au.jtext4.setText(cb.getNvAddress());
					
					SecondJPanel_au.msgJLabl.setText(cb.getNvThreeOrganizationName());
					SecondJPanel_au.JComboBox2.setSelectedItem(cb.getNvJobName());
					SecondJPanel_au.jtextfield1.setText(cb.getvEmail());
					SecondJPanel_au.jtextfield2.setText(cb.getiPost());
					SecondJPanel_au.jtextfield3.setText(cb.getvIdCart());
					SecondJPanel_au.JLabelImage.setIcon(new ImageIcon(cb.getvHead()));
					
					ThreeJPanel_au.JComboBox1.setSelectedItem(cb.getNvNationality());
					ThreeJPanel_au.jtextfield1.setText(cb.getNvBirthPlace());
					ThreeJPanel_au.JComboBox2.setSelectedItem(cb.getNvReligious());
					ThreeJPanel_au.JComboBox3.setSelectedItem(cb.getNvPolitics());
					ThreeJPanel_au.jtextfield2.setText(cb.getvAge());
					ThreeJPanel_au.JComboBox4.setSelectedItem(cb.getNvDegree());
					ThreeJPanel_au.JComboBox5.setSelectedItem(cb.getNvPaidStandardNo());
					ThreeJPanel_au.jtextfield3.setText(cb.getNvBanks());
					ThreeJPanel_au.JComboBox6.setSelectedItem(cb.getNvSpecialty());
					
					FourJPanel_au.chose_au.setText(cb.getcBirthday());
					FourJPanel_au.JComboBox1.setSelectedItem(cb.getNvNation());
					FourJPanel_au.jtextfield2.setText(cb.getcMobilePhone());
					FourJPanel_au.jtextfield3.setText(cb.getvSocialNo());
					FourJPanel_au.JComboBox2.setSelectedItem(cb.getiEductionAge());
					FourJPanel_au.JComboBox3.setSelectedItem(cb.getNvdegreeProfessinal());
					FourJPanel_au.jtextfield4.setText(cb.getvAccount());
					FourJPanel_au.jtextfield5.setText("heyuewei");
					FourJPanel_au.JComboBox4.setSelectedItem(cb.getNvHobby());
					
					FiveJPanel_au.jtextarea1.setText(cb.getNvResume());
					FiveJPanel_au.jtextarea2.setText(cb.getNvFamilyrelations());
					FiveJPanel_au.jtextarea3.setText(cb.getNvNote());
					
					
					
					zhujiemian.xiaoGuo(faa.c,"->人力资源档案管理->人力资源档案复核");
					
				}
			}
			
		});
	}
	
	public RegLogin() {
		
		init();
		jf = new JFrame();
		
		c = (JPanel) jf.getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(247,240,238));
		
		//设置jScrollPanel透明（2句）
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 

		
		jp.setBackground(new Color(247,240,238));
		jp.add(title,BorderLayout.NORTH);
		jp.add(jsp,BorderLayout.CENTER);
		
		c.add(jp);
		c.add(showpage);
		c.add(next);
		c.add(pre);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//下一页
		if(e.getSource().equals(next)){
			
			//pageNow++;
			if(pageNow < pageCount){
				
				//更新pre图标
				pre.setIcon(iicon2);
				
				//得到下一页的信息
				al = cc.getRegCasesByPage(++pageNow);
				
				//重新改变table的model，使数据改变
				table.setModel(new DefaultTableModel(al,tableTitle));
				
				//更新label
				showpage.setText(""+pageNow+"/"+pageCount+"");
				
				if(pageNow == pageCount){
					//更新next图标
					next.setIcon(iicon3);
				}
				
				jf.repaint();
			}
		}
		//上一页
		else if(e.getSource().equals(pre)){
			
			//pageNow--;
			
			if(pageNow > 1){
				
				//更新pre图标
				next.setIcon(iicon1);
				
				//得到下一页的信息
				al = cc.getRegCasesByPage(--pageNow);
				
				//重新改变table的model，使数据改变
				table.setModel(new DefaultTableModel(al,tableTitle));
				
				//更新label
				showpage.setText(""+pageNow+"/"+pageCount+"");
				
				if(pageNow <= 1){
					//更新pre图标
					pre.setIcon(iicon4);
				}
				
				jf.repaint();
			}
		}
		table.validate();
	}


}
