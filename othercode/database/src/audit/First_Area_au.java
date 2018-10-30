package audit;

import javax.swing.*;

import com.modelCl.CasesCl;
import register.FirstJPanel;
import register.FiveJPanel;
import register.FourJPanel;
import register.RegLogin;
import register.SecondJPanel;
import register.ThreeJPanel;

import login.zhujiemian;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class First_Area_au implements ActionListener{

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
	public static JLabel jlabel2 = null;
	
	RegLogin rl = null;
	
	public void init(){
		rl = new RegLogin();
		
		jpanel = new JPanel(new GridLayout(2,2,0,0));
		jpanel2 = new JPanel(new GridLayout(2,2,0,0));
		jpanel_button = new JPanel(new GridLayout(1,2,5,0));
		jpanel_button_all = new JPanel(new BorderLayout());
		jpanel_up = new JPanel(new BorderLayout());
		jpanel_all = new JPanel(new BorderLayout());		

		jlabel2 = new JLabel("档案号：");
		jlabel2.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		
		jbutton1 = new JButton("复核");
		jbutton2 = new JButton("返回");
		jbutton1.setBorder(BorderFactory.createBevelBorder(0));
		jbutton2.setBorder(BorderFactory.createBevelBorder(0));

		jbutton1.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		jbutton2.setFont(new Font( "微软雅黑",Font.PLAIN,12));
		
		jbutton1.addActionListener(this);
		jbutton2.addActionListener(this);
		
		jpanel_button.setPreferredSize(new Dimension(80,20));
	}
	public First_Area_au(){
		init();
		
		FirstJPanel_au fjpanel = new FirstJPanel_au();
		SecondJPanel_au sjpanel = new SecondJPanel_au();
		ThreeJPanel_au tjpanel = new ThreeJPanel_au();
		FourJPanel_au fourjpanel = new FourJPanel_au();
		FiveJPanel_au fivejpanel = new FiveJPanel_au();
		
		jf = new JFrame();
		
		c = (JPanel)jf.getContentPane();
		
		
		jpanel_button.add(jbutton1);
		jpanel_button.add(jbutton2);
		
		jpanel_button_all.add(jpanel_button,BorderLayout.EAST);
		jpanel_button_all.add(jlabel2,BorderLayout.CENTER);
		
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
		
		
		//jf.setVisible(true);
		//jf.setSize(1052, 500);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		First_Area first = new First_Area();
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
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(jbutton1)){
			
			if(!isNumeric(SecondJPanel_au.jtextfield1.getText())){
				JOptionPane.showMessageDialog(jf, "请输入正确的email格式");
				return;
			}
			
			String nvJobName = SecondJPanel_au.JComboBox2.getSelectedItem().toString();
			String nvName = FirstJPanel_au.jtext1.getText();
			String ncSex = FirstJPanel_au.jcombobox5.getSelectedItem().toString();
			String vEmail = SecondJPanel_au.jtextfield1.getText();
			String iPhone = FirstJPanel_au.jtext2.getText();
			String iQQ = FirstJPanel_au.jtext3.getText();
			String vIdCart = SecondJPanel_au.jtextfield3.getText();
			String nvAddress = FirstJPanel_au.jtext4.getText();
			String iPost = SecondJPanel_au.jtextfield2.getText();
			String nvNationality = ThreeJPanel_au.JComboBox1.getSelectedItem().toString();
			String nvBirthPlace = ThreeJPanel_au.jtextfield1.getText();
			String cBirthday = FourJPanel_au.chose_au.getText();
			String nvNation = FourJPanel_au.JComboBox1.getSelectedItem().toString();
			String nvReligious = ThreeJPanel_au.JComboBox2.getSelectedItem().toString();
			String nvPolitics = ThreeJPanel_au.JComboBox3.getSelectedItem().toString();
			String cMobilePhone = FourJPanel_au.jtextfield2.getText();
			String vSocialNo = FourJPanel_au.jtextfield3.getText();
			String vAge = ThreeJPanel_au.jtextfield2.getText();
			String nvDegree = ThreeJPanel_au.JComboBox4.getSelectedItem().toString();
			int iEductionAge = FourJPanel_au.JComboBox2.getSelectedIndex();
			String nvdegreeProfessinal = FourJPanel_au.JComboBox3.getSelectedItem().toString();
			String nvPaidStandardNo = ThreeJPanel_au.JComboBox5.getSelectedItem().toString();
			String nvBanks = ThreeJPanel_au.jtextfield3.getText();
			String vAccount = FourJPanel_au.jtextfield4.getText();
			//String nvRegistration = FourJPanel_au.jtextfield5.getText();
			String vFilechangeTime = ThreeJPanel_au.jtextfield4.getText();
			String nvSpecialty = ThreeJPanel_au.JComboBox6.getSelectedItem().toString();
			String nvHobby = FourJPanel_au.JComboBox4.getSelectedItem().toString();
			String nvResume = FiveJPanel_au.jtextarea1.getText();
			String nvFamilyrelations = FiveJPanel_au.jtextarea2.getText();
			String nvNote = FiveJPanel_au.jtextarea3.getText();
			String vHead = "";
			if(SecondJPanel_au.JLabelImage.getIcon() != null){
				vHead = SecondJPanel_au.JLabelImage.getIcon().toString();
			}

			int iState = 2;
			String iNo = RegLogin.iCaseNo;
			String nvChecker = FourJPanel_au.jtextfield5.getText();
				
			CasesCl cc = new CasesCl();
			
			if(cc.updateReg(nvJobName, nvName, ncSex, vEmail, iPhone, iQQ, iPost, nvAddress, vIdCart, nvNationality, nvBirthPlace, cBirthday, nvNation, nvReligious, nvPolitics, cMobilePhone, vSocialNo, vAge, nvDegree, iEductionAge, nvdegreeProfessinal, nvPaidStandardNo, nvBanks, vAccount, vFilechangeTime, nvSpecialty, nvHobby, nvResume, nvFamilyrelations,vHead, nvNote, iState, iNo, nvChecker)){
				
				//复核成功
				JOptionPane.showMessageDialog(jf, "复核成功");
				RegLogin rl = new RegLogin();
				zhujiemian.xiaoGuo(rl.c,"->人力资源档案管理->档案复核");
				
			}else{
				JOptionPane.showMessageDialog(jf, "复核失败");
			}
			
			
		}else if(e.getSource().equals(jbutton2)){
			zhujiemian.xiaoGuo(rl.c,"->人力资源档案管理->档案复核");
		}
		
	}

}
