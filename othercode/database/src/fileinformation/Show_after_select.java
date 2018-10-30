/*
 * ��ʾ��ѯ�����Ϣ
 */

package fileinformation;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import audit.*;
import com.model.CasesBean;
import com.modelCl.CasesCl;

import login.zhujiemian;


public class Show_after_select implements ActionListener{

	CasesCl cc = new CasesCl();
	
	//��һҳ
	int pageNow = 1;
	//�õ���ҳ��
	int pageCount = 1;
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
	
	public static String iCaseNo = "";
	
	
	@SuppressWarnings("serial")
	public void init(){
		tableTitle.add("�������");
		tableTitle.add("����");
		tableTitle.add("�Ա�");
		tableTitle.add("һ������");
		tableTitle.add("��������");
		tableTitle.add("��������");
		tableTitle.add("ְλ����");
		tableTitle.add("�鿴��ϸ");

		if(FileSelect.jc1.getSelectedItem() != null){
			//�õ�Ҫ��ѯ����Ϣ
			String nvOneOrganizationName = FileSelect.jc1.getSelectedItem().toString();
			String nvTwoOrganizationName = FileSelect.jc2.getSelectedItem().toString();
			String nvThreeOrganizationName = FileSelect.jc3.getSelectedItem().toString();
			String nvPositionClassificationName = FileSelect.jc4.getSelectedItem().toString();
			String nvPositionName = FileSelect.jc5.getSelectedItem().toString();
			String time1 = FileSelect.file_chooser.getText();
			String time2 = FileSelect.file_chooser2.getText();

			//Ĭ����ʾ��һҳ
			al = cc.getSelect(nvOneOrganizationName, nvTwoOrganizationName, nvThreeOrganizationName, nvPositionClassificationName, nvPositionName, time1, time2, pageNow);
			pageCount = cc.getSelectPageCount(nvOneOrganizationName, nvTwoOrganizationName, nvThreeOrganizationName, nvPositionClassificationName, nvPositionName, time1, time2);
			Counts = cc.getSelectCount(nvOneOrganizationName, nvTwoOrganizationName, nvThreeOrganizationName, nvPositionClassificationName, nvPositionName, time1, time2);
			
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
		
		jsp = new JScrollPane(table);
		
		title = new JLabel("����������������Դ����������"+Counts+"��");
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
	

				if(column == 7){
					Area_showdetail as = new Area_showdetail();
					iCaseNo = (String) model.getValueAt(row, 0);
					System.out.println(iCaseNo);
					CasesBean cb = cc.getRegMessage(iCaseNo);
					//����Ϣ��ǰ׼����
					Area_showdetail.jlabel2.setText("�����ţ�"+cb.getiNo());
					FirstJPanel_showdetail.msgJLable1.setText(cb.getNvOneOrganizationName());
					FirstJPanel_showdetail.msgJLable2.setText(cb.getNvTwoOrganizationName());				
					FirstJPanel_showdetail.msgJLable3.setText(cb.getNvPositionClassificationName());
					FirstJPanel_showdetail.msgJLable4.setText(cb.getNvPositionName());				
					FirstJPanel_showdetail.jtext1.setText(cb.getNvName());
					//FirstJPanel_showdetail.jcombobox5.setSelectedItem(cb.getNcSex());			
					FirstJPanel_showdetail.jtext2.setText(cb.getiPhone());
					FirstJPanel_showdetail.jtext3.setText(cb.getiQQ());
					FirstJPanel_showdetail.jtext4.setText(cb.getNvAddress());
					
					SecondJPanel_showdetail.msgJLabl.setText(cb.getNvThreeOrganizationName());
					SecondJPanel_showdetail.jtextfield4.setText(cb.getNvJobName());
					SecondJPanel_showdetail.jtextfield1.setText(cb.getvEmail());
					SecondJPanel_showdetail.jtextfield2.setText(cb.getiPost());
					SecondJPanel_showdetail.jtextfield3.setText(cb.getvIdCart());
					SecondJPanel_showdetail.msgJLabl.setIcon(new ImageIcon(cb.getvHead()));
					
					ThreeJPanel_showdetail.jtextfield5.setText(cb.getNvNationality());
					ThreeJPanel_showdetail.jtextfield1.setText(cb.getNvBirthPlace());
					ThreeJPanel_showdetail.jtextfield6.setText(cb.getNvReligious());
					ThreeJPanel_showdetail.jtextfield7.setText(cb.getNvPolitics());
					ThreeJPanel_showdetail.jtextfield2.setText(cb.getvAge());
					ThreeJPanel_showdetail.jtextfield8.setText(cb.getNvDegree());
					ThreeJPanel_showdetail.jtextfield1.setText(cb.getNvPaidStandardNo());
					ThreeJPanel_showdetail.jtextfield3.setText(cb.getNvBanks());
					ThreeJPanel_showdetail.jtextfield10.setText(cb.getNvSpecialty());
					
					FourJPanel_showdetail.jtextfield[0].setText(cb.getcBirthday());
					FourJPanel_showdetail.jtextfield[1].setText(cb.getNvNation());
					FourJPanel_showdetail.jtextfield[2].setText(cb.getcMobilePhone());
					FourJPanel_showdetail.jtextfield[3].setText(cb.getvSocialNo());
					FourJPanel_showdetail.jtextfield[4].setText(cb.getiEductionAge()+"");
					FourJPanel_showdetail.jtextfield[5].setText(cb.getNvdegreeProfessinal());
					FourJPanel_showdetail.jtextfield[6].setText(cb.getvAccount());
					FourJPanel_showdetail.jtextfield[7].setText("heyuewei");
					FourJPanel_showdetail.jtextfield[8].setText(cb.getNvHobby());
					
					FiveJPanel_showdetail.jtextarea1.setText(cb.getNvResume());
					FiveJPanel_showdetail.jtextarea2.setText(cb.getNvFamilyrelations());
					FiveJPanel_showdetail.jtextarea3.setText(cb.getNvNote());
					
					
					
					zhujiemian.xiaoGuo(as.c,"->������Դ��������->������Դ������ѯ->�鿴��ϸ");
					
				}
			}
			
		});
	}
	
	public Show_after_select() {
		
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
				al = cc.getRegCasesByPage(++pageNow);
				
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
				al = cc.getRegCasesByPage(--pageNow);
				
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
			zhujiemian.xiaoGuo(FileSelect.c,"->������Դ��������->������Դ������ѯ");
		}
	}


}

