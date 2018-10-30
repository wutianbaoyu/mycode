package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
public class ChecksGame extends JPanel {
	private JSplitPane jSplitPane1;
	private JPanel jPanel1;
	private JSplitPane jSplitPane4;
	private JPanel jPanel11;
	private JPanel jPanel10;
	private JPanel jPanel9;
	private JPanel jPanel8;
	private JLabel jLabel6;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JButton jButton3;
	public static JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton2;
	private JPanel jPanel7;
	private JPanel jPanel6;
	public static JButton jButton1;
	private JTextField jTextField1;
	private JPanel jPanel5;
	public static JTextArea jTextArea2;
	private JPanel jPanel4;
	private JTextArea jTextArea1;
	private JTabbedPane jTabbedPane4;
	private JTabbedPane jTabbedPane3;
	private JLabel jLabel2;
	public static JLabel jLabel1;
	private JPanel jPanel3;
	private JSplitPane jSplitPane3;
	private JPanel jPanel2;
	private JTabbedPane jTabbedPane2;
	private JSplitPane jSplitPane2;
	private JTabbedPane jTabbedPane1;
	private int num;
	private Object location;
	private int old_num;
	private String old_location;
	private String username;
	private Icon usericon;
	public static CheckerBoard board;

	/**
	 * Auto-generated main method to display this JFrame
	 * @param string 
	 * @param num 
	 * @param old_location 
	 * @param old_num 
	 * @param userIcon 
	 * @param userName 
	 * @param gameHall 
	 */

	public ChecksGame(int num, String location, int old_num, String old_location, String userName, Icon userIcon) {
		super();
		this.username=userName;
		this.usericon=userIcon;
		this.num=num;
		this.location=location;
		this.old_num=old_num;
		this.old_location=old_location;
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			setLayout(thisLayout);
			{
				jSplitPane1 = new JSplitPane();
				jSplitPane1.setDividerLocation(185);
				add(jSplitPane1, BorderLayout.CENTER);
				{
					jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
					jSplitPane2.setDividerLocation(285);
					jSplitPane1.add(jSplitPane2, JSplitPane.LEFT);
					{
						jTabbedPane1 = new JTabbedPane();
						jSplitPane2.add(jTabbedPane1, JSplitPane.TOP);
						{
							FlowLayout fl = new FlowLayout();
							fl.setVgap(48);
							jPanel1 = new JPanel();
							jTabbedPane1.addTab("对手", jPanel1);
							jPanel1.setLayout(fl);
							{
								
								jLabel1 = new JLabel();
								jPanel1.add(jLabel1);
//								jLabel1.setText("jLabel1");
							}
							{
								jPanel8 = new JPanel();
								jPanel1.add(jPanel8);
								jPanel8.setPreferredSize(new java.awt.Dimension(135, 46));
								{
									jLabel6 = new JLabel();
									jPanel8.add(jLabel6);
									jLabel6.setText("\u672c\u6b65\u5269\u4f59\u65f6\u95f4\uff1a");
								}
							}

						}
					}
					{
						jTabbedPane2 = new JTabbedPane();
						jSplitPane2.add(jTabbedPane2, JSplitPane.BOTTOM);
						{
							BorderLayout bor= new BorderLayout();
							bor.setVgap(39);
							jPanel2 = new JPanel();
							jPanel2.setLayout(bor);
							jTabbedPane2.addTab("自己", jPanel2);
							{
								jPanel9 = new JPanel();
								jPanel9.setLayout(new BorderLayout());
								jPanel2.add(jPanel9, BorderLayout.NORTH);
								jPanel9.setPreferredSize(new java.awt.Dimension(177, 37));
								{
									jLabel2 = new JLabel(username);
									jLabel2.setIcon(usericon);
									jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
									
								}
								jPanel9.add(jLabel2,BorderLayout.SOUTH);
							}
							{
								jPanel10 = new JPanel();
								jPanel2.add(jPanel10, BorderLayout.CENTER);
								{
									jLabel3 = new JLabel();
									jPanel10.add(jLabel3);
									jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
									
								}
							}
							{
								jPanel11 = new JPanel();
								jPanel2.add(jPanel11, BorderLayout.SOUTH);
								jPanel11.setPreferredSize(new java.awt.Dimension(177, 64));
								{
									jLabel4 = new JLabel();
									jPanel11.add(jLabel4);
									jLabel4.setText("本步剩余时间：");
									jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
									
								}
							}

						}
					}
				}
				{
					jSplitPane3 = new JSplitPane();
					jSplitPane3.setDividerLocation(456);
					jSplitPane1.add(jSplitPane3, JSplitPane.RIGHT);
					{
						jPanel3 = new JPanel();
						BorderLayout jPanel3Layout = new BorderLayout();
						jPanel3Layout.setVgap(22);
//						jPanel3Layout.setHgap(32);
						jPanel3.setLayout(jPanel3Layout);
						jSplitPane3.add(jPanel3, JSplitPane.LEFT);
						{
							jPanel6 = new JPanel();
							BorderLayout jPanel6Layout = new BorderLayout();
							jPanel6.setLayout(jPanel6Layout);
							jPanel3.add(jPanel6, BorderLayout.NORTH);
							{
								jLabel5 = new JLabel();
								jPanel6.add(jLabel5, BorderLayout.WEST);
								jLabel5.setText("<<<\u8c61\u68cb\u6e38\u620f\u2014\u623f\u95f4>>>");
							}
							{
								jButton2 = new JButton();
								jPanel6.add(jButton2, BorderLayout.EAST);
								jButton2.setText("\u9000\u51fa");
								jButton2.setPreferredSize(new java.awt.Dimension(70, 33));
							}
						}
						{
							board=new CheckerBoard();
							board.setBackground(new java.awt.Color(81, 114, 159));
							
							jPanel3.add(board, BorderLayout.CENTER);
						}
						{
							jPanel7 = new JPanel();
							jPanel7.setPreferredSize(new Dimension(83,43));
							jPanel7.setBackground(new java.awt.Color(81, 114, 159));
							jPanel3.add(jPanel7, BorderLayout.SOUTH);
							{
								jButton3 = new JButton();
								jPanel7.add(jButton3);
								jButton3.setText("开始");
								jButton3.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										try {//准备
											Log_In_Window.output.writeUTF("ready"+";"+num+";"+location+";"+old_num+";"+old_location);
											jButton3.setEnabled(false);
//											while(!CheckerBoard.ready);
//											board.repaint();
										} catch (IOException e) {
											System.out.println("准备出错");
										}
									}
								});
							}
							{
								jButton4 = new JButton();
								jPanel7.add(jButton4);
								jButton4.setText("\u6c42\u548c");
							}
							{
								jButton5 = new JButton();
								jPanel7.add(jButton5);
								jButton5.setText("\u6094\u68cb");
							}
							{
								jButton6 = new JButton();
								jPanel7.add(jButton6);
								jButton6.setText("\u8ba4\u8f93");
							}
						}
						jPanel3.setBackground(new java.awt.Color(81, 114, 159));
					}
					{
						jSplitPane4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
						jSplitPane4.setDividerLocation(285);
						jSplitPane3.add(jSplitPane4, JSplitPane.RIGHT);
						{
							jTabbedPane3 = new JTabbedPane();
							jSplitPane4.add(jTabbedPane3, JSplitPane.TOP);
							{
								jTextArea1 = new JTextArea();
								jTabbedPane3.addTab("用户列表", null, jTextArea1, null);
								jTextArea1.setText("jTextArea1");
							}
						}
						{
							jTabbedPane4 = new JTabbedPane();
							jSplitPane4.add(jTabbedPane4, JSplitPane.BOTTOM);
							{
								jPanel4 = new JPanel();
								BorderLayout jPanel4Layout = new BorderLayout();
								jPanel4.setLayout(jPanel4Layout);
								jTabbedPane4.addTab("聊天", null, jPanel4, null);
								{
									jTextArea2 = new JTextArea();
									jPanel4.add(new JScrollPane(jTextArea2), BorderLayout.CENTER);
								}
								{
									jPanel5 = new JPanel();
									jPanel4.add(jPanel5, BorderLayout.SOUTH);
									{
										jTextField1 = new JTextField();
										jPanel5.add(jTextField1);
										jTextField1.setPreferredSize(new java.awt.Dimension(105, 26));
									}
									{
										jButton1 = new JButton();
										jPanel5.add(jButton1);
										jButton1.setText("\u53d1\u9001");
										jButton1.setEnabled(false);
										jButton1.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent evt) {
												
												String say=username+"说:  "+jTextField1.getText()+"\n";
												jTextArea2.append(say);
												try {
													Log_In_Window.output.writeUTF("speak"+";"+say);
												} catch (IOException e) {
													e.printStackTrace();
												} 
												jTextField1.setText("");
											}
										});
									}
								}
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
