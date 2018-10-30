package paidmanagement;

/*
 * 显示查询后的信息
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import audit.*;
import com.model.CasesBean;
import com.modelCl.CasesCl;
import com.modelCl.PaidProjectRegCl;

import login.zhujiemian;


public class PaidAfter_select implements ActionListener{

	//CasesCl cc = new CasesCl();
	PaidProjectRegCl pprc = new PaidProjectRegCl();
	
	//第一页
	int pageNow = 1;
	//得到总页数
	int pageCount = 0;
	//得到总结果数
	int Counts = 0;
	
	Vector<Vector<String>> al = null;
	
	//得到返回来要显示的信息
	
	JFrame jf = null;
	
	public static JPanel c = null;
	JPanel jp = null;
	
	JScrollPane jsp = null;
	
	JLabel title = null;
	JLabel showpage = null;
	
	JTable table = null;
	
	JButton return_select = null;
	JButton pre = null;
	JButton next = null;

	
	ImageIcon iicon1 = new ImageIcon("images/button1.gif");
	ImageIcon iicon2 = new ImageIcon("images/button2.gif");
	ImageIcon iicon3 = new ImageIcon("images/button3.gif");
	ImageIcon iicon4 = new ImageIcon("images/button4.gif");
	
	Vector<String> tableTitle = new Vector<String>();
	
	//public static int iCaseNo = 1;
	
	String keyword = null;
	String time1 = null;
	String time2 = null;
	
	@SuppressWarnings("serial")
	public void init(){
		tableTitle.add("酬薪标准编号");
		tableTitle.add("职位");
		tableTitle.add("酬薪标准名称");
		tableTitle.add("制定人");
		tableTitle.add("登记人");
		tableTitle.add("酬薪总额");
		tableTitle.add("登记时间");
		tableTitle.add("查看明细");

//		if(FileSelect.jc1.getSelectedItem() != null){
//			//得到要查询的信息
//
//			
//		}
		
		if(!Paidselect.jt1.getText().equals("")){
			//System.out.println("Paidselect.jt1="+Paidselect.jt1);
			String iPaidProjectNo = Paidselect.jt1.getText();
			al = pprc.selPaidProjectMsgByNo(iPaidProjectNo);
			if(al.size() != 0){
				//分页总页数
				pageCount = 1;
				//总案例数
				Counts = 1;
			}
		}else{
			System.out.println("2");
			keyword = "%"+Paidselect.jt2.getText()+"%";
			time1 = Paidselect.jt3.getText();
			time2 = Paidselect.jt4.getText();
			
			al = pprc.getSelectByPage(keyword, time1, time2, pageNow);
			//分页总页数
			pageCount = pprc.getSelectPageCount(keyword, time1, time2);
			//总案例数
			Counts = pprc.getCountTatol(keyword, time1, time2);
		}

		
		final DefaultTableModel model = new DefaultTableModel(al,tableTitle);
		//实例化table，并且让它不能编辑，重写isCellEditable方法
		table = new JTable(model){ public boolean isCellEditable(int row, int column) { return false; }};
		
		table.getTableHeader().setReorderingAllowed(false);//设置表头不能交换移动
		table.setRowHeight(20);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		//设置边框颜色
		table.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		//影藏"登记时间"这列
		TableColumn hidecolumn = table.getColumn("登记时间");
		//获取列表模型
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(hidecolumn);
		
		jsp = new JScrollPane(table);
		
		title = new JLabel("符合条件的酬薪案例总数："+Counts+"例");
		showpage = new JLabel(""+pageNow+"/"+pageCount+"");
		
		title.setFont(new Font( "微软雅黑",Font.BOLD,14));
		showpage.setFont(new Font( "微软雅黑",Font.BOLD,12));
		showpage.setBounds(458, 325, 20, 20);
		if(Counts <= 1){
			next = new JButton(iicon1);
		}else{
			next = new JButton(iicon3);
		}
		pre = new JButton(iicon4);
		return_select = new JButton("返回");
		return_select.setFont(new Font( "微软雅黑",Font.BOLD,12));
		return_select.setBorder(BorderFactory.createBevelBorder(0));
		return_select.setBounds(625, 0, 40, 20);
		
		next.setBounds(502+iicon4.getIconWidth()+20, 326, iicon1.getIconWidth(), iicon1.getIconHeight());
		pre.setBounds(502, 326, iicon4.getIconWidth(), iicon4.getIconHeight());
		
		jp = new JPanel(new BorderLayout());		
		jp.setBounds(0, 50, 749, 244);

		jsp.setBorder(BorderFactory.createLineBorder(new Color(247,240,238)));
		//注册
		next.addActionListener(this);
		pre.addActionListener(this);
		return_select.addActionListener(this);

		
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			
			public void mouseClicked(java.awt.event.MouseEvent e){
				
				int column = table.getSelectedColumn();//可以获得你选中的列
				int row = table.getSelectedRow();//可以获得你选择的行

				if(column == 6){				
					//iCaseNo = Integer.parseInt((String) table.getModel().getValueAt(row, 0));					
					
					String[] messges = new String[7];
					for(int i=0,rowCount=table.getColumnCount();i<rowCount;i++){
						
						messges[i] = (String) table.getModel().getValueAt(row, i);
						
					}
					
					PaidAfter_select_detail psd = new PaidAfter_select_detail(Integer.parseInt(messges[0]));
					
					PaidAfter_select_detail.jt1.setText(messges[0]);
					PaidAfter_select_detail.jt2.setText(messges[2]);
					PaidAfter_select_detail.jt3.setText(messges[5]);
					PaidAfter_select_detail.jt4.setText(messges[3]);
					PaidAfter_select_detail.jt5.setText(messges[4]);
					PaidAfter_select_detail.jt6.setText(messges[6]);
					PaidAfter_select_detail.jt7.setText(messges[1]);

					zhujiemian.xiaoGuo(psd.c,"->酬薪标准管理->酬薪标准查询->查看明细");
					
				}
			}
			
		});
	}
	
	public PaidAfter_select() {
		
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
		c.add(return_select);
		
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
				//al = cc.getRegCasesByPage(++pageNow);
				al = pprc.getSelectByPage(keyword, time1, time2, ++pageNow);
				
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
				al = pprc.getSelectByPage(keyword, time1, time2, --pageNow);
				
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
		//返回
		if(e.getSource().equals(return_select)){
			zhujiemian.xiaoGuo(Paidselect.c,"->酬薪标准管理->酬薪标准查询");
		}
	}


}


