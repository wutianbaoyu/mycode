package tools2;

import register.FirstJPanel;
import register.FourJPanel;
import register.SecondJPanel;
import register.ThreeJPanel;

public class Reset {

	public Reset(){
		//Çå¿Õ
		FirstJPanel.jtext1.setText("");		
		FirstJPanel.jtext2.setText("");
		FirstJPanel.jtext3.setText("");
		FirstJPanel.jtext4.setText("");
		
		SecondJPanel.jtextfield1.setText("");
		SecondJPanel.jtextfield2.setText("");
		SecondJPanel.jtextfield3.setText("");
		SecondJPanel.JLabelImage.setIcon(null);
		SecondJPanel.JPanelImage.removeAll();
		SecondJPanel.outputString = "";
		
		ThreeJPanel.jtextfield1.setText("");
		ThreeJPanel.jtextfield2.setText("");
		ThreeJPanel.jtextfield3.setText("");
		
		FourJPanel.jtextfield1.setText("");
		FourJPanel.jtextfield2.setText("");
		FourJPanel.jtextfield3.setText("");
		FourJPanel.jtextfield4.setText("");
		FourJPanel.jtextfield5.setText("");
		
	}
}
