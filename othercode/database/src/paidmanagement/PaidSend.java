/*
 * н�귢��
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
	
	String[] jtTitle = new String[]{"н�귢�ŵ����","I������","II������","III������","����","����н���ܶ�","�Ǽ�"};
	String[][] jtData = new String[][]{
			{"SG1000001","����","�����˾","�����","5","48236.62","�Ǽ�"}	
	};
	
	public void init(){
		
		jt = new JTable(jtData,jtTitle){ public boolean isCellEditable(int row, int column) { return false; }};
		jt.getTableHeader().setReorderingAllowed(false);//���ñ�ͷ���ܽ����ƶ�
		
		jt.addMouseListener(new java.awt.event.MouseAdapter(){
			
			public void mouseClicked(java.awt.event.MouseEvent e){
				
				int column = jt.getSelectedColumn();//���Ի����ѡ�е���
				int row = jt.getSelectedRow();//���Ի����ѡ�����
				//System.out.println(row);
				if(column == 6){
					
					if(row == 0){
						zhujiemian.xiaoGuo(new PaidSendDetail().c,"->��н��׼����->��н���ŵǼ���ϸ");
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
		//����jScrollPanel͸����2�䣩
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false); 
		
//		jf.setVisible(true);
//		jf.setSize(749,446);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
