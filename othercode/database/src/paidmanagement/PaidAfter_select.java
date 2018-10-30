package paidmanagement;

/*
 * ��ʾ��ѯ�����Ϣ
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
	
	//��һҳ
	int pageNow = 1;
	//�õ���ҳ��
	int pageCount = 0;
	//�õ��ܽ����
	int Counts = 0;
	
	Vector<Vector<String>> al = null;
	
	//�õ�������Ҫ��ʾ����Ϣ
	
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
		tableTitle.add("��н��׼���");
		tableTitle.add("ְλ");
		tableTitle.add("��н��׼����");
		tableTitle.add("�ƶ���");
		tableTitle.add("�Ǽ���");
		tableTitle.add("��н�ܶ�");
		tableTitle.add("�Ǽ�ʱ��");
		tableTitle.add("�鿴��ϸ");

//		if(FileSelect.jc1.getSelectedItem() != null){
//			//�õ�Ҫ��ѯ����Ϣ
//
//			
//		}
		
		if(!Paidselect.jt1.getText().equals("")){
			//System.out.println("Paidselect.jt1="+Paidselect.jt1);
			String iPaidProjectNo = Paidselect.jt1.getText();
			al = pprc.selPaidProjectMsgByNo(iPaidProjectNo);
			if(al.size() != 0){
				//��ҳ��ҳ��
				pageCount = 1;
				//�ܰ�����
				Counts = 1;
			}
		}else{
			System.out.println("2");
			keyword = "%"+Paidselect.jt2.getText()+"%";
			time1 = Paidselect.jt3.getText();
			time2 = Paidselect.jt4.getText();
			
			al = pprc.getSelectByPage(keyword, time1, time2, pageNow);
			//��ҳ��ҳ��
			pageCount = pprc.getSelectPageCount(keyword, time1, time2);
			//�ܰ�����
			Counts = pprc.getCountTatol(keyword, time1, time2);
		}

		
		final DefaultTableModel model = new DefaultTableModel(al,tableTitle);
		//ʵ����table�������������ܱ༭����дisCellEditable����
		table = new JTable(model){ public boolean isCellEditable(int row, int column) { return false; }};
		
		table.getTableHeader().setReorderingAllowed(false);//���ñ�ͷ���ܽ����ƶ�
		table.setRowHeight(20);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		//���ñ߿���ɫ
		table.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		//Ӱ��"�Ǽ�ʱ��"����
		TableColumn hidecolumn = table.getColumn("�Ǽ�ʱ��");
		//��ȡ�б�ģ��
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(hidecolumn);
		
		jsp = new JScrollPane(table);
		
		title = new JLabel("���������ĳ�н����������"+Counts+"��");
		showpage = new JLabel(""+pageNow+"/"+pageCount+"");
		
		title.setFont(new Font( "΢���ź�",Font.BOLD,14));
		showpage.setFont(new Font( "΢���ź�",Font.BOLD,12));
		showpage.setBounds(458, 325, 20, 20);
		if(Counts <= 1){
			next = new JButton(iicon1);
		}else{
			next = new JButton(iicon3);
		}
		pre = new JButton(iicon4);
		return_select = new JButton("����");
		return_select.setFont(new Font( "΢���ź�",Font.BOLD,12));
		return_select.setBorder(BorderFactory.createBevelBorder(0));
		return_select.setBounds(625, 0, 40, 20);
		
		next.setBounds(502+iicon4.getIconWidth()+20, 326, iicon1.getIconWidth(), iicon1.getIconHeight());
		pre.setBounds(502, 326, iicon4.getIconWidth(), iicon4.getIconHeight());
		
		jp = new JPanel(new BorderLayout());		
		jp.setBounds(0, 50, 749, 244);

		jsp.setBorder(BorderFactory.createLineBorder(new Color(247,240,238)));
		//ע��
		next.addActionListener(this);
		pre.addActionListener(this);
		return_select.addActionListener(this);

		
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			
			public void mouseClicked(java.awt.event.MouseEvent e){
				
				int column = table.getSelectedColumn();//���Ի����ѡ�е���
				int row = table.getSelectedRow();//���Ի����ѡ�����

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

					zhujiemian.xiaoGuo(psd.c,"->��н��׼����->��н��׼��ѯ->�鿴��ϸ");
					
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
		
		//����jScrollPanel͸����2�䣩
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
		
		//��һҳ
		if(e.getSource().equals(next)){
			
			//pageNow++;
			if(pageNow < pageCount){
				
				//����preͼ��
				pre.setIcon(iicon2);
				
				//�õ���һҳ����Ϣ
				//al = cc.getRegCasesByPage(++pageNow);
				al = pprc.getSelectByPage(keyword, time1, time2, ++pageNow);
				
				//���¸ı�table��model��ʹ���ݸı�
				table.setModel(new DefaultTableModel(al,tableTitle));
				
				//����label
				showpage.setText(""+pageNow+"/"+pageCount+"");
				
				if(pageNow == pageCount){
					//����nextͼ��
					next.setIcon(iicon3);
				}
				
				jf.repaint();
			}
		}
		//��һҳ
		else if(e.getSource().equals(pre)){
			
			//pageNow--;
			
			if(pageNow > 1){
				
				//����preͼ��
				next.setIcon(iicon1);
				
				//�õ���һҳ����Ϣ
				al = pprc.getSelectByPage(keyword, time1, time2, --pageNow);
				
				//���¸ı�table��model��ʹ���ݸı�
				table.setModel(new DefaultTableModel(al,tableTitle));
				
				//����label
				showpage.setText(""+pageNow+"/"+pageCount+"");
				
				if(pageNow <= 1){
					//����preͼ��
					pre.setIcon(iicon4);
				}
				
				jf.repaint();
			}
		}
		//����
		if(e.getSource().equals(return_select)){
			zhujiemian.xiaoGuo(Paidselect.c,"->��н��׼����->��н��׼��ѯ");
		}
	}


}


