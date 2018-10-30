package pkg;

import org.eclipse.swt.widgets.Display;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.forms.widgets.FormToolkit;
import java.awt.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class begin {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			begin window = new begin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		JPanel j=new JPanel(new BorderLayout());
		randomButton(j);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 346);
		shell.setText("\u8BF7\u767B\u9646");//
		
		Label label = new Label(shell, SWT.NONE);//请输入你的个人信息标签
		label.setText("\u8BF7\u8F93\u5165\u60A8\u7684\u4E2A\u4EBA\u4FE1\u606F");
		label.setBounds(0, 0, 209, 17);
		
		Label label_1 = new Label(shell, SWT.NONE);//用户名标签
		label_1.setBounds(0, 20, 48, 17);
		label_1.setText("\u7528\u6237\u540D\uFF1A");
		
		text = new Text(shell, SWT.BORDER);//文本框
		text.setBounds(52, 20, 372, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);//服务器标签
		label_2.setBounds(0, 49, 48, 17);
		label_2.setText("\u670D\u52A1\u5668\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER);//文本框
		text_1.setBounds(52, 49, 372, 23);
		
		Label label_3 = new Label(shell, SWT.NONE);//头像标签
		label_3.setBounds(0, 72, 36, 17);
		label_3.setText("\u5934\u50CF\uFF1A");
		
		Button button = new Button(shell, SWT.NONE);//连接按钮
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		button.setBounds(118, 271, 80, 27);
		button.setText("\u8FDE\u63A5");
		
		Button btnNewButton = new Button(shell, SWT.NONE);//重置按钮
		btnNewButton.setBounds(203, 271, 80, 27);
		btnNewButton.setText("\u91CD\u7F6E");
		
		Button button_1 = new Button(shell, SWT.NONE);//退出按钮
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		button_1.setBounds(289, 271, 80, 27);
		button_1.setText("\u9000\u51FA");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(52, 78, 372, 187);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);

	}


public void randomButton(JPanel panel){
	
	File file = new File("pic1/a.ini");
	int x=0,y=0,j=0;
	String s;
	
	try {
		BufferedReader br = new BufferedReader(new FileReader(file));
		s = br.readLine();
		while(s!=null){
			icon[j] = s;
			ImageIcon icon = new ImageIcon("image/"+s);
			JButton jb = new JButton(icon);
			jb.setName(s);
			jb.setBounds(x*40, 15+y*40, 40, 40);
			jb.addActionListener(this);
			jb.setActionCommand(s);
			
			panel.add(jb);
			
			if(x<12){
				x++;
			}else if(y<5){
				x=0;
				y++;
			}
			j++;
			s = br.readLine();
		}
		br.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
