package mousedemo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class KeyDemo extends KeyAdapter implements ActionListener
{
	JFrame f=null;
	JLabel label=null;
	JTextField tField =null;
	String keyString="";

	public KeyDemo()
	{
		f=new JFrame("KeyEventDemo");
		Container contentPane=f.getContentPane();
		contentPane.setLayout(new GridLayout(3,1));
		label=new JLabel();
		tField=new JTextField();
		tField.requestFocus();
		//光标一开始就放在tField上，方便输入
		tField.addKeyListener(this);
		//增加KeyListener到tField中
		JButton b=new JButton("清除");
		b.addActionListener(this);
		contentPane.add(label);
		contentPane.add(tField);
		contentPane.add(b);
		f.pack();
		f.show();
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		})
		;
		
	}
	public void actionPerformed(ActionEvent e)
	{
		keyString="";
		label.setText("");
		tField.setText("");
		tField.requestFocus();
	}
	//当按下按钮时运行此清除tField与label上文字的工作
	public void KeyTyped(KeyEvent e)
	{
		char c=e.getKeyChar();
		if(c=='o')
		{
			JFrame newF=new JFrame("新窗口");
			newF.setSize(200, 200);
			newF.show();
		}
		keyString=keyString+c;
		label.setText(keyString);
	}
	//判断用户输入的字符，若为“o”，则开新窗口，且将用户输入的字符在label中输出
	public static void main(String args[])
	{
		new KeyDemo();
	}
}
