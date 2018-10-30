/*
 * 第五个JPaneL.既最下面的履历，家庭关系
 * 备注
 */

package fileinformation;
import javax.swing.*;

import java.awt.*;
public class FiveJPanel_showdetail {

//	public static void main(String[] args) {
//		FiveJPanel_showdetail fj = new FiveJPanel_showdetail();
//
//	}
	
	JFrame jf = null;
	
	Container c = null;
	
	JLabel jlabel1 = null;
	JLabel jlabel2 = null;
	JLabel jlabel3 = null;
	
	
	JPanel jpanel1 = null;
	JPanel jpanel2 = null;
	JPanel jpanel3 = null;
	static JPanel jpanel4 = null;
	
	public static JTextArea jtextarea1 = null;
	public static JTextArea jtextarea2 = null;
	public static JTextArea jtextarea3 = null;
	
	JScrollPane jscrollpane1 = null;
	JScrollPane jscrollpane2 = null;
	JScrollPane jscrollpane3 = null;
	
	public void init(){
		
		jpanel1 = new JPanel(new BorderLayout());
		jpanel2 = new JPanel(new BorderLayout());
		jpanel3 = new JPanel(new BorderLayout());
		jpanel4 = new JPanel(new GridLayout(3,1,0,0));
		
		jlabel1 = new JLabel("个人履历");
		jlabel2 = new JLabel("家庭关系信息");
		jlabel3 = new JLabel("备注");
		
		jlabel1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jlabel2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		jlabel3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		jlabel1.setBorder(BorderFactory.createEtchedBorder());
		jlabel2.setBorder(BorderFactory.createEtchedBorder());
		jlabel3.setBorder(BorderFactory.createEtchedBorder());
		
		jlabel1.setPreferredSize(new Dimension(93, 20));
		jlabel2.setPreferredSize(new Dimension(93, 20));
		jlabel3.setPreferredSize(new Dimension(93, 20));
		
		jtextarea1 = new JTextArea();
		jtextarea2 = new JTextArea();
		jtextarea3 = new JTextArea();
		
		//jtextarea3.setBorder(BorderFactory.createEtchedBorder());
		jtextarea1.setBorder(BorderFactory.createLineBorder(Color.black));
		jtextarea2.setBorder(BorderFactory.createLineBorder(Color.black));
		jtextarea3.setBorder(BorderFactory.createLineBorder(Color.black));
		
		jscrollpane1 = new JScrollPane(jtextarea1);
		jscrollpane2 = new JScrollPane(jtextarea2);
		jscrollpane3 = new JScrollPane(jtextarea3);
	}

	public FiveJPanel_showdetail(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		jpanel1.add(jlabel1,BorderLayout.WEST);
		jpanel1.add(jscrollpane1,BorderLayout.CENTER);
		
		jpanel2.add(jlabel2,BorderLayout.WEST);
		jpanel2.add(jscrollpane2,BorderLayout.CENTER);
		
		jpanel3.add(jlabel3,BorderLayout.WEST);
		jpanel3.add(jscrollpane3,BorderLayout.CENTER);
		
		jpanel4.add(jpanel1);
		jpanel4.add(jpanel2);
		jpanel4.add(jpanel3);
		
		jpanel1.setBackground(new Color(233,224,217));
		jpanel2.setBackground(new Color(233,224,217));
		jpanel3.setBackground(new Color(233,224,217));
		
//		c.add(jpanel4);
//		
//		jf.setVisible(true);
//		jf.setSize(802,200);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
