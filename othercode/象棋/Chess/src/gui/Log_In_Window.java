package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import check.Table_Check;
import service.Socket_Client;
import thread.Whether_flush;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
public class Log_In_Window extends javax.swing.JFrame {
//	private JPanel jPanel2;
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton exitButton;
	private JButton jButton2;
	private JButton jButton1;
	private JPanel jPanel4;
	private JButton jButton0;
	private JPanel jPanel3;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JLabel jLabel2;
	public static Socket_Client socket;
	public static DataInputStream input;
	public static DataOutputStream output;
	public int iconnum=1;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		
//		new Table_Check();
//		Whether_flush flush=new Whether_flush();
//		Thread w_flush=new Thread(flush);
//		w_flush.start();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Log_In_Window inst = new Log_In_Window();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
		
	}

	public Log_In_Window() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			this.setResizable(false);
			this.setTitle("\u767b\u5f55\u7a97\u53e3");
//			{
//				jPanel2 = new JPanel();
//				getContentPane().add(jPanel2, BorderLayout.NORTH);
//				jPanel2.setBounds(0, 0, 384, 10);
//			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, BorderLayout.NORTH);
				jLabel1.setText("\u8bf7\u8f93\u5165\u60a8\u7684\u4e2a\u4eba\u4fe1\u606f");
				jLabel1.setPreferredSize(new java.awt.Dimension(384, 30));
			}
			{
				jPanel1 = new JPanel();
				jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(421, 436));
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u7528\u6237\u540d\uff1a");
				}
				{
					jTextField1 = new JTextField();
					jPanel1.add(jTextField1);
					jTextField1
							.setPreferredSize(new java.awt.Dimension(357, 30));
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("\u670d\u52a1\u5668\uff1a");
				}
				{
					jTextField2 = new JTextField();
					jTextField2.setText("127.0.0.1");
					jPanel1.add(jTextField2);
					jTextField2
							.setPreferredSize(new java.awt.Dimension(357, 30));
				}
				{
					jLabel4 = new JLabel();
					jPanel1.add(jLabel4);
					jLabel4.setText("\u5934    \u50cf\uff1a");
				}
				{
					ImageIcon portrait = new ImageIcon("./res/face/1-1.gif");
					jLabel5 = new JLabel(portrait);
					jPanel1.add(jLabel5);
					jLabel5.setPreferredSize(new java.awt.Dimension(portrait
							.getIconWidth(), portrait.getIconWidth()));
				}
				{
					jPanel3 = new JPanel();
					FlowLayout f=new FlowLayout(FlowLayout.LEFT);
//					f.setAlignOnBaseline(true);
					jPanel3.setLayout(f);
					jPanel1.add(jPanel3);
					jPanel3.setPreferredSize(new java.awt.Dimension(424, 309));
					{
						for (int i = 1; i <= 85; i++) {

							final ImageIcon a = new ImageIcon("./res/face/" + i + "-"
									+ "1.gif");
							final int num=i;
							jButton0 = new JButton(a);
							jButton0.setPreferredSize(new java.awt.Dimension(a
									.getIconWidth(), a.getIconHeight()));
							jButton0.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									jLabel5.setIcon(a);
									iconnum=num;
								}
							});
							jPanel3.add(jButton0);

						}

					}

				}
			}
			{
				jPanel4 = new JPanel();
				getContentPane().add(jPanel4, BorderLayout.SOUTH);
				jPanel4.setPreferredSize(new java.awt.Dimension(471, 41));
				{
					jButton1 = new JButton();
					jPanel4.add(jButton1);
					jButton1.setText("\u8fde\u63a5");
					jButton1.setPreferredSize(new java.awt.Dimension(65, 24));
					jButton1.addActionListener(new ActionListener() {
				

						public void actionPerformed(ActionEvent evt) {
							
							if(jTextField1.getText().equals("")){
								JOptionPane.showMessageDialog(null, "用户名不能为空", "错误",
										JOptionPane.ERROR_MESSAGE);
							}
							else{
	
								try {
									socket= new Socket_Client(jTextField2.getText(),jTextField1.getText(),iconnum);
									new GameHall(jTextField1.getText(),jLabel5.getIcon(),iconnum).setLocationRelativeTo(null);
									setVisible(false);
								} catch (UnknownHostException e) {
								} catch (IOException e) {
									JOptionPane.showMessageDialog(null, "服务器IP错误", "错误",
											JOptionPane.ERROR_MESSAGE);
									
								}
							}
						}
					});
				}
				{
					jButton2 = new JButton();
					jPanel4.add(jButton2);
					jButton2.setText("\u91cd\u7f6e");
					jButton2.setPreferredSize(new java.awt.Dimension(65, 24));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
				{
					exitButton = new JButton();
					jPanel4.add(exitButton);
					exitButton.setText("\u9000\u51fa");
					exitButton.setPreferredSize(new java.awt.Dimension(65, 24));
					exitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.exit(0);
						}
					});
				}
			}
			pack();
			this.setSize(427, 511);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void jButton2ActionPerformed(ActionEvent evt) {
		jTextField1.setText("");
		jTextField2.setText("");
		jLabel5.setIcon( new ImageIcon("./res/face/1-1.gif"));
	}

}
