package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import check.Table_Check;

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
public class GameHall extends javax.swing.JFrame {
	private JTabbedPane gameHallTabbedPane;
	private JSplitPane gameSplitPane;
	private JPanel gamePanel;
	private JButton joinButton;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel tableLabel;
	private JLabel numLabel;
	private JPanel tablePanel;
	private JTextArea jTextArea1;
	private JTabbedPane jTabbedPane2;
	private JPanel jPanel5;
	private JTabbedPane jTabbedPane1;
	private JButton exitButton;
	private JPanel gameBuuttonPanel;
	private JSplitPane jSplitPane2;
	private JPanel gameTablePanel;
	private JLabel titleCheesLabel;
	private JPanel gameTopPanel;
	private String userName;// 用户名
	public Icon userIcon;// 用户头像
	public static JButton[] buttonarray = new JButton[30];
	private int check = 0;
	int old_num = 0;
	String location = "left";
	private int iconnum;

	// private int

	/**
	 * Auto-generated main method to display this JFrame
	 * @param iconnum 
	 */

	public GameHall(String userName, Icon userIcon, int iconnum) {
		super();
		this.iconnum=iconnum;
		for (int i = 0; i < 15; i++) {

			Table_Check.table[i] = new Table_Check();

		}
		this.userName = userName;
		this.userIcon = userIcon;
		initGUI();
		try {
			Log_In_Window.output.writeUTF("want_f");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				try {
					Log_In_Window.output.writeUTF("close");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}

		});
	}

	private void initGUI() {
		try {
			// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			{ // 游戏大厅大选项卡
				gameHallTabbedPane = new JTabbedPane();
				gameSplitPane = new JSplitPane();
				gameSplitPane.setDividerLocation(675);
				gameHallTabbedPane.addTab("游戏大厅", gameSplitPane);
				{ // 下面的游戏面板
					gamePanel = new JPanel();
					gameSplitPane.add(gamePanel, JSplitPane.LEFT);// 左面板
					BorderLayout jPanel1Layout = new BorderLayout();
					gamePanel.setLayout(jPanel1Layout);
					{ // 显示象棋游戏和加入退出的面板
						gameTopPanel = new JPanel();
						BorderLayout jPanel2Layout = new BorderLayout();
						gameTopPanel.setLayout(jPanel2Layout);
						gamePanel.add(gameTopPanel, BorderLayout.NORTH);
						{ // 显示<<<<象棋游戏>>>>
							titleCheesLabel = new JLabel();
							gameTopPanel.add(titleCheesLabel,
									BorderLayout.CENTER);
							titleCheesLabel
									.setText("<<<<   \u8c61\u68cb\u6e38\u620f   >>>>");
						}
						{ // 加入退出按钮面板
							gameBuuttonPanel = new JPanel();
							gameTopPanel.add(gameBuuttonPanel,
									BorderLayout.EAST);
							{ // 加入按钮
								joinButton = new JButton();
								gameBuuttonPanel.add(joinButton);
								joinButton.setText("\u52a0\u5165");
								joinButton
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent evt) {
												// gameHallTabbedPane.addTab(
												// "象棋游戏",
												// new ChecksGame());
												// gameHallTabbedPane
												// .setSelectedIndex(1);

											}
										});
							}
							{ // 退出按钮
								exitButton = new JButton();
								gameBuuttonPanel.add(exitButton);
								exitButton.setText("\u9000\u51fa");
								exitButton
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent evt) {
												GameHall.this.setVisible(false);

											}
										});
							}
						}
					}
					{ // 下面蓝色的那一块大面板
						gameTablePanel = new JPanel();
						gamePanel.add(gameTablePanel, BorderLayout.CENTER);
						gameTablePanel.setBackground(new java.awt.Color(81,
								114, 159));
						gameTablePanel.setLayout(null);
						int num = 0;
						for (int i = 0; i < 5; i++) {

							for (int j = 0; j < 3; j++) {

								num++;
								setElementPanel(28 + 230 * j, i * 110, num);// 一块桌子椅子单元
							}
						}

					}
				}
				{ // 右边的个人信息和服务器信息面板
					jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
					jSplitPane2.setDividerLocation(311);
					gameSplitPane.add(jSplitPane2, JSplitPane.RIGHT);// 右面板
					{ // 个人信息选项卡
						jTabbedPane1 = new JTabbedPane();
						jSplitPane2.add(jTabbedPane1, JSplitPane.TOP);
						{ // 存放图片秀和头像用户名的面板
							jPanel5 = new JPanel();
							BorderLayout jPanel5Layout = new BorderLayout();
							jPanel5.setLayout(jPanel5Layout);
							jTabbedPane1.addTab("个人信息", jPanel5);
							{ // 图片秀标签
								jLabel2 = new JLabel();
								ImageIcon boy = new ImageIcon(
										"./res/img/boy1.gif");
								jLabel2.setIcon(boy);
								jLabel2.setPreferredSize(new java.awt.Dimension(
										boy.getIconWidth(), boy.getIconHeight()));
								jPanel5.add(jLabel2, BorderLayout.CENTER);
								jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
							}
							{ // 头像和用户名标签
								jLabel3 = new JLabel(userName);
								jLabel3.setIcon(userIcon);
								jLabel2.setPreferredSize(new java.awt.Dimension(
										userIcon.getIconWidth(), userIcon
												.getIconHeight()));
								jPanel5.add(jLabel3, BorderLayout.SOUTH);
								jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
							}
						}
					}
					{ // 服务器信息选项卡
						jTabbedPane2 = new JTabbedPane();
						jSplitPane2.add(jTabbedPane2, JSplitPane.BOTTOM);
						{ // 下方显示服务员信息的文本框
							jTextArea1 = new JTextArea();
							jTabbedPane2.addTab("服务器信息", jTextArea1);
							jTextArea1.append(userName + "登录");
						}
					}
				}
				getContentPane().add(gameHallTabbedPane, BorderLayout.CENTER);
			}
			pack();
			this.setSize(858, 690);
			this.setVisible(true);
			this.setTitle("\u5ba2\u6237\u7aef\u6e38\u620f\u7a97\u53e3");
			this.setResizable(false);// 不可调整大小
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 一个单元格的面板
	 * 
	 * @param 左右坐标
	 * @param 上下坐标
	 * @param 标号
	 * 
	 */
	private void setElementPanel(int x, int y, final int num) {

		buttonarray[num * 2 - 2] = new JButton();
		buttonarray[num * 2 - 1] = new JButton();
		final JButton chair1Button = buttonarray[num * 2 - 2];
		final JButton chair2Button = buttonarray[num * 2 - 1];
		tablePanel = new JPanel();
		FlowLayout f = new FlowLayout();
		f.setVgap(32);
		tablePanel.setLayout(f);
		gameTablePanel.add(tablePanel);
		tablePanel.setBounds(x, y, 150, 142);
		tablePanel.setBackground(new java.awt.Color(81, 114, 159));
		{ // 椅子1按钮

			tablePanel.add(chair1Button);
			ImageIcon chairFistIcon = new ImageIcon("./res/img/noone.gif");
			chair1Button.setIcon(chairFistIcon);
			chair1Button.setPreferredSize(new java.awt.Dimension(chairFistIcon
					.getIconWidth(), chairFistIcon.getIconHeight()));
			chair1Button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if ((num != old_num || old_num == -1 || !location
							.equals("left")) && !Table_Check.game_star) {// 是否点自己或初始化或游戏已经开始

						if (Table_Check.table[num - 1].left == true) {// 是否是对方
							JOptionPane.showMessageDialog(null, "该位已经有人坐啦！",
									"提示", JOptionPane.INFORMATION_MESSAGE);
						} else {

							if (old_num != 0) {

								if (location.equals("left")) {

									Table_Check.table[old_num - 1].left = false;
								} else if (location.equals("right")) {

									Table_Check.table[old_num - 1].right = false;
								}
							}
							try {
								Log_In_Window.output.writeUTF("num_and_table"+";"+(num - 1) + ";"
										+ "left" + ";" + (old_num - 1) + ";"
										+ location);
								Table_Check.table[num - 1].left = true;
								// access_Table();
							} catch (IOException e1) {

							}
							try {

								gameHallTabbedPane.remove(1);
							} catch (Exception e1) {

							}
							gameHallTabbedPane.addTab("象棋游戏", new ChecksGame(
									num - 1, "left", old_num - 1, location,userName,userIcon));
							gameHallTabbedPane.setSelectedIndex(1);
							Table_Check.table[num - 1].left_iconNum=iconnum+"";
							Table_Check.ergodic_Table();
							CheckerBoard.ready = false;// 坐下，还未准备开始
							old_num = num;
							location = "left";
						}
					}
					// buttonarray[check].setIcon(new ImageIcon(
					// "./res/img/noone.gif"));
					// check = num * 2 - 2;
					// chair1Button.setIcon(new ImageIcon("./res/face/" + "1"
					// + "-" + "1.gif"));
					// chair2Button.setIcon(new
					// ImageIcon("./res/img/noone.gif"));

				}
			});

		}
		{ // 桌子标签
			tableLabel = new JLabel();
			tablePanel.add(tableLabel);
			ImageIcon tableIcon = new ImageIcon("./res/img/xqnoone.gif");
			tableLabel.setIcon(tableIcon);
			tableLabel.setPreferredSize(new java.awt.Dimension(tableIcon
					.getIconWidth(), tableIcon.getIconHeight()));
		}
		{ // 椅子2按钮

			tablePanel.add(chair2Button);
			ImageIcon chairFistIcon = new ImageIcon("./res/img/noone.gif");
			chair2Button.setIcon(chairFistIcon);
			chair2Button.setPreferredSize(new java.awt.Dimension(chairFistIcon
					.getIconWidth(), chairFistIcon.getIconHeight()));
			chair2Button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if ((num != old_num || old_num == -1 || !location
							.equals("right")) && !Table_Check.game_star) {// 是否点自己或初始化或游戏已经开始
																			// {//
																			// 是否点自己或初始化

						if (Table_Check.table[num - 1].right == true) {// 是否是对方
							JOptionPane.showMessageDialog(null, "该位已经有人坐啦！",
									"提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							if (old_num != 0) {

								if (location.equals("left")) {// 清掉之前的座位

									Table_Check.table[old_num - 1].left = false;
								} else if (location.equals("right")) {

									Table_Check.table[old_num - 1].right = false;
								}
							}
							try {
								Log_In_Window.output.writeUTF("num_and_table"+";"+(num - 1) + ";"
										+ "right" + ";" + (old_num - 1) + ";"
										+ location);
								Table_Check.table[num - 1].right = true;

								// access_Table();
							} catch (IOException e1) {

							}
							try {

								gameHallTabbedPane.remove(1);
							} catch (Exception e1) {

							}
							gameHallTabbedPane.addTab("象棋游戏", new ChecksGame(
									num - 1, "right", old_num - 1, location,userName,userIcon));
							gameHallTabbedPane.setSelectedIndex(1);
							Table_Check.table[num - 1].right_iconNum=iconnum+"";
							Table_Check.ergodic_Table();
							CheckerBoard.ready = false;// 坐下，还未准备开始
							old_num = num;
							location = "right";
						}
					}
					// buttonarray[check].setIcon(new ImageIcon(
					// "./res/img/noone.gif"));
					// check = num * 2 - 1;
					// chair2Button.setIcon(new ImageIcon("./res/face/" + "1"
					// + "-" + "1.gif"));
					// chair1Button.setIcon(new
					// ImageIcon("./res/img/noone.gif"));
				}
			});

		}
		{ // 编号标签
			numLabel = new JLabel();
			tablePanel.add(numLabel);
			numLabel.setText("- " + num + " -");
			numLabel.setForeground(new java.awt.Color(255, 255, 255));
		}
	}

	/**
	 * @throws IOException
	 */
	public static void access_Table() throws IOException {
		for (int i = 0; i < 15; i++) {

			Log_In_Window.output.writeUTF("left_ready");
			Table_Check.table[i].left = Boolean
					.parseBoolean(Log_In_Window.input.readUTF());
			Log_In_Window.output.writeUTF("right_ready");
			Table_Check.table[i].right = Boolean
					.parseBoolean(Log_In_Window.input.readUTF());
		}
	}

}
