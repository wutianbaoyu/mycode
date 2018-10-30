/*
 * 第二个JPanel
 */

package register;

import javax.swing.*;

import com.modelCl.OrganizationCl;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondJPanel implements ActionListener{		
	
	JFrame jf = null;
	
	Container c = null;
	
	JPanel JPanel1 = null;
	JPanel JPanel2 = null;
	public static JPanel JPanelImage = null;
	static JPanel JPanel3 = null;
	
	JLabel JLabel1 = null;
	JLabel JLabel2 = null;
	JLabel JLabel3 = null;
	JLabel JLabel4 = null;
	JLabel JLabel5 = null;
	public static JLabel JLabelImage = null;

	JButton upLoade = null;
	JButton upLoade_change = null;
	
	public static JComboBox JComboBox1 = null;
	public static JComboBox JComboBox2 = null;
	
	public static JTextField jtextfield1 = null;
	public static JTextField jtextfield2 = null;
	public static JTextField jtextfield3 = null;
	
	public static Vector<String> string1 = new Vector<String>();
	Vector<String> string2 = new Vector<String>();
	
	OrganizationCl oc = new OrganizationCl();
	
	public static String outputString = "";
	
	public void init(){
		
		upLoade = new JButton("上传图片");
		upLoade_change = new JButton("更改");
		upLoade.setBounds(20, 35, 50, 30);
		upLoade_change.setFont(new Font( "微软雅黑",Font.BOLD,12));
		upLoade.setFont(new Font( "微软雅黑",Font.BOLD,10));
		upLoade_change.setBorder(BorderFactory.createBevelBorder(0));
		upLoade.setBorder(BorderFactory.createBevelBorder(0));
		
		string1 = oc.getThreeOrganization(1,"公司");
		string2 = oc.getJob();
		
		JPanel1 = new JPanel(new GridLayout(5,1,0,0));
		JPanel2 = new JPanel(new GridLayout(5,1,0,0));
		JPanel3 = new JPanel(new BorderLayout());
		JPanelImage = new JPanel();
		JPanelImage.setLayout(null);
		
		JLabel1 = new JLabel("III级机构*");
		JLabel2 = new JLabel("职称*");
		JLabel3 = new JLabel("EMAIL*");
		JLabel4 = new JLabel("邮编");
		JLabel5 = new JLabel("身份证号*");		
		JLabelImage = new JLabel();
			
		JLabel1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel3.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel4.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JLabel5.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		
		JLabel1.setBorder(BorderFactory.createEtchedBorder());
		JLabel2.setBorder(BorderFactory.createEtchedBorder());
		JLabel3.setBorder(BorderFactory.createEtchedBorder());
		JLabel4.setBorder(BorderFactory.createEtchedBorder());
		JLabel5.setBorder(BorderFactory.createEtchedBorder());
		//JLabelImage.setBorder(BorderFactory.createEtchedBorder());
		
		
		JLabel1.setPreferredSize(new Dimension(93, 20));
		//JLabelImage.setPreferredSize(new Dimension(93,120));
		JPanelImage.setPreferredSize(new Dimension(93,120));
		JPanelImage.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		JComboBox1 = new JComboBox(string1);
		JComboBox2 = new JComboBox(string2);
		
		JComboBox1.setFont(new Font( "微软雅黑",Font.BOLD,12));
		JComboBox2.setFont(new Font( "微软雅黑",Font.BOLD,12));
		
		JComboBox1.setBorder(BorderFactory.createEtchedBorder());
		JComboBox1.setBackground(Color.white);
		JComboBox2.setBorder(BorderFactory.createEtchedBorder());
		JComboBox2.setBackground(Color.white);
		
		jtextfield1 = new JTextField();
		jtextfield2 = new JTextField();
		jtextfield3 = new JTextField();	
		
		jtextfield1.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield2.setBorder(BorderFactory.createBevelBorder(1));
		jtextfield3.setBorder(BorderFactory.createBevelBorder(1));
		
		jtextfield1.setBackground(new Color(212,248,212));
		jtextfield2.setBackground(new Color(212,248,212));
		jtextfield3.setBackground(new Color(212,248,212));
		
		JPanelImage.add(upLoade);
		
		upLoade.addActionListener(this);
		upLoade_change.addActionListener(this);
	}
	public SecondJPanel(){
		
		init();
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		JPanel1.add(JLabel1);
		JPanel1.add(JLabel2);
		JPanel1.add(JLabel3);
		JPanel1.add(JLabel4);
		JPanel1.add(JLabel5);
		
		JPanel2.add(JComboBox1);
		JPanel2.add(JComboBox2);
		JPanel2.add(jtextfield1);
		JPanel2.add(jtextfield2);
		JPanel2.add(jtextfield3);
		
		JPanel3.add(JPanel1,BorderLayout.WEST);
		JPanel3.add(JPanel2,BorderLayout.CENTER);
		JPanel3.add(JPanelImage,BorderLayout.EAST);
		
		JPanel1.setBackground(new Color(233,224,217));
		
//		c.add(JPanel3);
//		
//		jf.setVisible(true);
//		jf.setSize(401,150);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(upLoade) || e.getSource().equals(upLoade_change)){
			String path = null;
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("请选择要上传的文件...");
			fc.setApproveButtonText("确定");
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(jf)) {
			    path=fc.getSelectedFile().getPath();

			    //获取文件后缀
			    int index = path.lastIndexOf('.');		    
			    String extension = path.substring(index + 1).toLowerCase();

			    File oldfile = new File(path);

			    File createfile = new File("D:/人力资源/UsersImages/"+jtextfield3.getText()+"."+extension+"");//目标文件夹
			    
			    
			    
			    if(extension.equals("gif")|| extension.equals("jpg") || extension.equals("png")){
			    	
				    try {
				    	
					    Image src = javax.imageio.ImageIO.read(oldfile); //构造Image对象 
					    int wideth=src.getWidth(null); //得到源图宽 
					    int height=src.getHeight(null); //得到源图长 
					    BufferedImage tag = new BufferedImage(JPanelImage.getWidth(),JPanelImage.getHeight(),BufferedImage.TYPE_INT_RGB); 
					    tag.getGraphics().drawImage(src,0,0,JPanelImage.getWidth(),JPanelImage.getHeight(),null); //绘制缩小后的图 ，没有这句，出来的图片是是黑色的.
					    FileOutputStream out=new FileOutputStream(createfile); //输出到文件流 

					    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

					    encoder.encode(tag); //近JPEG编码 
					    out.close(); 
					    
//					    FileInputStream fis = new FileInputStream(oldfile);
//					    FileOutputStream fos = new FileOutputStream(createfile);
//				      
//					    byte[] date = new byte[1024];
//					    int rs = -1;
//					      
//					    while ((rs = fis.read(date)) > 0) {
//					    	fos.write(date, 0, rs);
//					    }
//					      
//					    fos.close();
//					    fis.close();
					      
					    JOptionPane.showMessageDialog(null, "上传成功");
					    
					    outputString = "D:/人力资源/UsersImages/"+jtextfield3.getText()+"."+extension+"";
					    
					    JPanelImage.removeAll();
					    
					    upLoade_change.setBounds(62, 92, 30, 20);
					    JLabelImage.setIcon(new ImageIcon("D:/人力资源/UsersImages/"+jtextfield3.getText()+"."+extension+""));
					    JLabelImage.setBounds(0, 0, JPanelImage.getWidth(), JPanelImage.getHeight());
					    				    
					    JPanelImage.add(upLoade_change);
					    JPanelImage.add(JLabelImage);
					    
					    JPanelImage.repaint();
					    jf.repaint();
					      
				    } catch (IOException e1) {
				    	e1.printStackTrace();
				    }
			    
			    }else{
			    	JOptionPane.showMessageDialog(null, "请选择正确的图片格式");
			    }
			}
		}
		
	}


}
