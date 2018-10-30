/*
 * 薪酬发放
 */
package paidmanagement;
import javax.swing.*;

import login.zhujiemian;

import audit.First_Area_au;

import java.awt.*;

public class PaidSend{
//	public static void main(String[] args) {
//		new PaidSend();
//	}
	
	JFrame jf = null;
	
	public static JPanel c= null;
	
	JTable jt = null;
	
	JScrollPane jsp = null;
	
	String[] jtTitle = new String[]{"薪酬发放单编号","I级机构","II级机构","III级机构","人数","基本薪酬总额","登记"};
	String[][] jtData = new String[][]{
			{"SG1000001","集团","软件公司","外包组","5","48236.62","登记"}	
	};
	
	public void init(){
		
		jt = new JTable(jtData,jtTitle){ public boolean isCellEditable(int row, int column) { return false; }};
		jt.getTableHeader().setReorderingAllowed(false);//设置表头不能交换移动
		
		jt.addMouseListener(new java.awt.event.MouseAdapter(){
			
			public void mouseClicked(java.awt.event.MouseEvent e){
				
				int column = jt.getSelectedColumn();//可以获得你选中的列
				int row = jt.getSelectedRow();//可以获得你选择的行
				//System.out.println(row);
				if(column == 6){
					
					if(row == 0){
						zhujiemian.xiaoGuo(new PaidSendDetail().c,"->酬薪标准管理->酬薪发放登记明细");
					}
				}
			}
			
		});
		jsp = new JScrollPane(jt);
		
		jsp.setBounds(0, 50, 749, 446);
	}
	
	public PaidSend() {
		
		init();
		jf = new JFrame();
		
		c = (JPanel) jf.getContentPane();
		c.setLayout(null);
		
		c.add(jsp);
		c.setBackground(new Color(247,240,238));
		//设置jScrollPanel透明（2句）
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		
//		jf.setVisible(true);
//		jf.setSize(749,446);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
