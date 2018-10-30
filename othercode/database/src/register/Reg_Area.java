package register;

import javax.swing.*;

import tools2.Reset;

import com.modelCl.CasesCl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg_Area implements ActionListener{

	JFrame jf = null;
	
	public static JPanel c = null;
	
	JPanel jpanel = null;
	JPanel jpanel2 = null;
	JPanel jpanel_button = null;
	JPanel jpanel_button_all = null;
	JPanel jpanel_up = null;
	JPanel jpanel_all = null;
	
	JButton jbutton1 = null;
	JButton jbutton2 = null;
	
	JLabel jlabel = null;
	//JLabel jlabel2 = null;
	
	public void init(){
		
		jpanel = new JPanel(new GridLayout(2,2,0,0));
		jpanel2 = new JPanel(new GridLayout(2,2,0,0));
		jpanel_button = new JPanel(new GridLayout(1,2,5,0));
		jpanel_button_all = new JPanel(new BorderLayout());
		jpanel_up = new JPanel(new BorderLayout());
		jpanel_all = new JPanel(new BorderLayout());		

		jbutton1 = new JButton("提交");
		jbutton2 = new JButton("清除");
		jbutton1.setBorder(BorderFactory.createBevelBorder(0));
		jbutton2.setBorder(BorderFactory.createBevelBorder(0));

		jbutton1.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		jbutton2.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		
		jpanel_button.setPreferredSize(new Dimension(80,20));
		
		//监听
		jbutton1.addActionListener(this);
		jbutton2.addActionListener(this);
	}
	public Reg_Area(){
		
		init();
		
		FirstJPanel fjpanel = new FirstJPanel();
		SecondJPanel sjpanel = new SecondJPanel();
		ThreeJPanel tjpanel = new ThreeJPanel();
		FourJPanel fourjpanel = new FourJPanel();
		FiveJPanel fivejpanel = new FiveJPanel();
		
		jf = new JFrame();
		
		c = (JPanel)jf.getContentPane();
		
		
		jpanel_button.add(jbutton1);
		jpanel_button.add(jbutton2);
		
		jpanel_button_all.add(jpanel_button,BorderLayout.EAST);
		//jpanel_button_all.add(jlabel2,BorderLayout.CENTER);
		
		jpanel_up.add(jpanel_button_all,BorderLayout.CENTER);
		//jpanel_up.add(jlabel,BorderLayout.NORTH);			
		
		jpanel.add(fjpanel.First_up_jpanel_1_left);
		jpanel.add(sjpanel.JPanel3);
		jpanel.add(tjpanel.JPanel1);
		jpanel.add(fourjpanel.JPanel1);
		
		jpanel2.add(jpanel);
		jpanel2.add(fivejpanel.jpanel4);
		
		jpanel_all.add(jpanel_up,BorderLayout.NORTH);
		jpanel_all.add(jpanel2,BorderLayout.CENTER);
	
		c.add(jpanel_all);
		
		c.setBackground(new Color(247,240,238));
		jpanel_button_all.setBackground(new Color(247,240,238));
		
//		jf.setVisible(true);
//		jf.setSize(1052, 500);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Reg_Area first = new Reg_Area();
//	}
	public boolean isNumeric(String str){
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";  
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(str);
		
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	} 
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(jbutton1)){
			int a = JOptionPane.showConfirmDialog(jf,"你确定要提交吗？","", JOptionPane.YES_NO_OPTION);
			
			if(!isNumeric(SecondJPanel.jtextfield1.getText())){
				JOptionPane.showMessageDialog(jf, "请输入正确的email格式");
				return;
			}
			if(a == 0){
				//确认
				CasesCl cc = new CasesCl();
				String nvOneOrganizationName = FirstJPanel.jcombobox1.getSelectedItem().toString();
				String nvTwoOrganizationName = FirstJPanel.jcombobox2.getSelectedItem().toString();
				String nvThreeOrganizationName = SecondJPanel.JComboBox1.getSelectedItem().toString();
				String nvPositionClassificationName = FirstJPanel.jcombobox3.getSelectedItem().toString();
				String nvPositionName = FirstJPanel.jcombobox4.getSelectedItem().toString();
				String nvJobName = SecondJPanel.JComboBox2.getSelectedItem().toString();
				String nvName = FirstJPanel.jtext1.getText();
				String ncSex = FirstJPanel.jcombobox5.getSelectedItem().toString();
				String vEmail = SecondJPanel.jtextfield1.getText();
				String iPhone = FirstJPanel.jtext2.getText();
				String iQQ = FirstJPanel.jtext3.getText();
				String vIdCart = SecondJPanel.jtextfield3.getText();
				String nvAddress = FirstJPanel.jtext4.getText();
				String iPost = SecondJPanel.jtextfield2.getText();
				String nvNationality = ThreeJPanel.JComboBox1.getSelectedItem().toString();
				String nvBirthPlace = ThreeJPanel.jtextfield1.getText();
				String cBirthday = FourJPanel.jtextfield1.getText();
				String nvNation = FourJPanel.JComboBox1.getSelectedItem().toString();
				String nvReligious = ThreeJPanel.JComboBox2.getSelectedItem().toString();
				String nvPolitics = ThreeJPanel.JComboBox3.getSelectedItem().toString();
				String cMobilePhone = FourJPanel.jtextfield2.getText();
				String vSocialNo = FourJPanel.jtextfield3.getText();
				String vAge = ThreeJPanel.jtextfield2.getText();
				String nvDegree = ThreeJPanel.JComboBox4.getSelectedItem().toString();
				int iEductionAge = FourJPanel.JComboBox2.getSelectedIndex();
				String nvdegreeProfessinal = FourJPanel.JComboBox3.getSelectedItem().toString();
				String nvPaidStandardNo = ThreeJPanel.JComboBox5.getSelectedItem().toString();
				String nvBanks = ThreeJPanel.jtextfield3.getText();
				String vAccount = FourJPanel.jtextfield4.getText();
				String nvRegistration = FourJPanel.jtextfield5.getText();
				String vFilebuildingTime = ThreeJPanel.jtextfield4.getText();
				String nvSpecialty = ThreeJPanel.JComboBox6.getSelectedItem().toString();
				String nvHobby = FourJPanel.JComboBox4.getSelectedItem().toString();
				String nvResume = FiveJPanel.jtextarea1.getText();
				String nvFamilyrelations = FiveJPanel.jtextarea2.getText();
				String nvNote = FiveJPanel.jtextarea3.getText();
				String vHead = SecondJPanel.outputString;
				int iState = 1;
				
				String first = "";
				String second = "";
				String third = "";
				if(FirstJPanel.jcombobox1.getSelectedIndex() < 10){
					first = "0"+(FirstJPanel.jcombobox1.getSelectedIndex()+1)+"";
				}else{
					first = (FirstJPanel.jcombobox1.getSelectedIndex()+1)+"";
				}
				if(FirstJPanel.jcombobox2.getSelectedIndex() < 10){
					second = "0"+(FirstJPanel.jcombobox2.getSelectedIndex()+1)+"";
				}else{
					second = (FirstJPanel.jcombobox2.getSelectedIndex()+1)+"";
				}
				if(SecondJPanel.JComboBox1.getSelectedIndex() < 10){
					third = "0"+(SecondJPanel.JComboBox1.getSelectedIndex()+1)+"";
				}else{
					third = (SecondJPanel.JComboBox1.getSelectedIndex()+1)+"";
				}
				String iNo = new GregorianCalendar().get(Calendar.YEAR)+first+second+third;
				
				if(cc.addCases(nvOneOrganizationName,nvTwoOrganizationName,nvThreeOrganizationName,nvPositionClassificationName,nvPositionName,nvJobName,nvName, ncSex, vEmail, iPhone, iQQ, vIdCart, nvAddress, iPost, nvNationality, nvBirthPlace, cBirthday, nvNation, nvReligious, nvPolitics, cMobilePhone, vSocialNo, vAge, nvDegree, iEductionAge, nvdegreeProfessinal, nvPaidStandardNo, nvBanks, vAccount, nvRegistration,vFilebuildingTime,nvSpecialty, nvHobby,nvResume, nvFamilyrelations, nvNote, vHead, iState,iNo)){
					
					JOptionPane.showMessageDialog(jf, "提交成功");
					//清空内容
					new Reset();
					
				}else{			
					//提高失败
					JOptionPane.showMessageDialog(jf, "提交失败");
				}

				
			}else{
				//取消
				
			}

		}else if(arg0.getSource().equals(jbutton2)){
			new Reset();
		}
	}

}
