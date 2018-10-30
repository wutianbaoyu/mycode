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
	private String userName;// �û���
	public Icon userIcon;// �û�ͷ��
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

			{ // ��Ϸ������ѡ�
				gameHallTabbedPane = new JTabbedPane();
				gameSplitPane = new JSplitPane();
				gameSplitPane.setDividerLocation(675);
				gameHallTabbedPane.addTab("��Ϸ����", gameSplitPane);
				{ // �������Ϸ���
					gamePanel = new JPanel();
					gameSplitPane.add(gamePanel, JSplitPane.LEFT);// �����
					BorderLayout jPanel1Layout = new BorderLayout();
					gamePanel.setLayout(jPanel1Layout);
					{ // ��ʾ������Ϸ�ͼ����˳������
						gameTopPanel = new JPanel();
						BorderLayout jPanel2Layout = new BorderLayout();
						gameTopPanel.setLayout(jPanel2Layout);
						gamePanel.add(gameTopPanel, BorderLayout.NORTH);
						{ // ��ʾ<<<<������Ϸ>>>>
							titleCheesLabel = new JLabel();
							gameTopPanel.add(titleCheesLabel,
									BorderLayout.CENTER);
							titleCheesLabel
									.setText("<<<<   \u8c61\u68cb\u6e38\u620f   >>>>");
						}
						{ // �����˳���ť���
							gameBuuttonPanel = new JPanel();
							gameTopPanel.add(gameBuuttonPanel,
									BorderLayout.EAST);
							{ // ���밴ť
								joinButton = new JButton();
								gameBuuttonPanel.add(joinButton);
								joinButton.setText("\u52a0\u5165");
								joinButton
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent evt) {
												// gameHallTabbedPane.addTab(
												// "������Ϸ",
												// new ChecksGame());
												// gameHallTabbedPane
												// .setSelectedIndex(1);

											}
										});
							}
							{ // �˳���ť
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
					{ // ������ɫ����һ������
						gameTablePanel = new JPanel();
						gamePanel.add(gameTablePanel, BorderLayout.CENTER);
						gameTablePanel.setBackground(new java.awt.Color(81,
								114, 159));
						gameTablePanel.setLayout(null);
						int num = 0;
						for (int i = 0; i < 5; i++) {

							for (int j = 0; j < 3; j++) {

								num++;
								setElementPanel(28 + 230 * j, i * 110, num);// һ���������ӵ�Ԫ
							}
						}

					}
				}
				{ // �ұߵĸ�����Ϣ�ͷ�������Ϣ���
					jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
					jSplitPane2.setDividerLocation(311);
					gameSplitPane.add(jSplitPane2, JSplitPane.RIGHT);// �����
					{ // ������Ϣѡ�
						jTabbedPane1 = new JTabbedPane();
						jSplitPane2.add(jTabbedPane1, JSplitPane.TOP);
						{ // ���ͼƬ���ͷ���û��������
							jPanel5 = new JPanel();
							BorderLayout jPanel5Layout = new BorderLayout();
							jPanel5.setLayout(jPanel5Layout);
							jTabbedPane1.addTab("������Ϣ", jPanel5);
							{ // ͼƬ���ǩ
								jLabel2 = new JLabel();
								ImageIcon boy = new ImageIcon(
										"./res/img/boy1.gif");
								jLabel2.setIcon(boy);
								jLabel2.setPreferredSize(new java.awt.Dimension(
										boy.getIconWidth(), boy.getIconHeight()));
								jPanel5.add(jLabel2, BorderLayout.CENTER);
								jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
							}
							{ // ͷ����û�����ǩ
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
					{ // ��������Ϣѡ�
						jTabbedPane2 = new JTabbedPane();
						jSplitPane2.add(jTabbedPane2, JSplitPane.BOTTOM);
						{ // �·���ʾ����Ա��Ϣ���ı���
							jTextArea1 = new JTextArea();
							jTabbedPane2.addTab("��������Ϣ", jTextArea1);
							jTextArea1.append(userName + "��¼");
						}
					}
				}
				getContentPane().add(gameHallTabbedPane, BorderLayout.CENTER);
			}
			pack();
			this.setSize(858, 690);
			this.setVisible(true);
			this.setTitle("\u5ba2\u6237\u7aef\u6e38\u620f\u7a97\u53e3");
			this.setResizable(false);// ���ɵ�����С
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * һ����Ԫ������
	 * 
	 * @param ��������
	 * @param ��������
	 * @param ���
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
		{ // ����1��ť

			tablePanel.add(chair1Button);
			ImageIcon chairFistIcon = new ImageIcon("./res/img/noone.gif");
			chair1Button.setIcon(chairFistIcon);
			chair1Button.setPreferredSize(new java.awt.Dimension(chairFistIcon
					.getIconWidth(), chairFistIcon.getIconHeight()));
			chair1Button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if ((num != old_num || old_num == -1 || !location
							.equals("left")) && !Table_Check.game_star) {// �Ƿ���Լ����ʼ������Ϸ�Ѿ���ʼ

						if (Table_Check.table[num - 1].left == true) {// �Ƿ��ǶԷ�
							JOptionPane.showMessageDialog(null, "��λ�Ѿ�����������",
									"��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
							gameHallTabbedPane.addTab("������Ϸ", new ChecksGame(
									num - 1, "left", old_num - 1, location,userName,userIcon));
							gameHallTabbedPane.setSelectedIndex(1);
							Table_Check.table[num - 1].left_iconNum=iconnum+"";
							Table_Check.ergodic_Table();
							CheckerBoard.ready = false;// ���£���δ׼����ʼ
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
		{ // ���ӱ�ǩ
			tableLabel = new JLabel();
			tablePanel.add(tableLabel);
			ImageIcon tableIcon = new ImageIcon("./res/img/xqnoone.gif");
			tableLabel.setIcon(tableIcon);
			tableLabel.setPreferredSize(new java.awt.Dimension(tableIcon
					.getIconWidth(), tableIcon.getIconHeight()));
		}
		{ // ����2��ť

			tablePanel.add(chair2Button);
			ImageIcon chairFistIcon = new ImageIcon("./res/img/noone.gif");
			chair2Button.setIcon(chairFistIcon);
			chair2Button.setPreferredSize(new java.awt.Dimension(chairFistIcon
					.getIconWidth(), chairFistIcon.getIconHeight()));
			chair2Button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if ((num != old_num || old_num == -1 || !location
							.equals("right")) && !Table_Check.game_star) {// �Ƿ���Լ����ʼ������Ϸ�Ѿ���ʼ
																			// {//
																			// �Ƿ���Լ����ʼ��

						if (Table_Check.table[num - 1].right == true) {// �Ƿ��ǶԷ�
							JOptionPane.showMessageDialog(null, "��λ�Ѿ�����������",
									"��ʾ", JOptionPane.INFORMATION_MESSAGE);
						} else {
							if (old_num != 0) {

								if (location.equals("left")) {// ���֮ǰ����λ

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
							gameHallTabbedPane.addTab("������Ϸ", new ChecksGame(
									num - 1, "right", old_num - 1, location,userName,userIcon));
							gameHallTabbedPane.setSelectedIndex(1);
							Table_Check.table[num - 1].right_iconNum=iconnum+"";
							Table_Check.ergodic_Table();
							CheckerBoard.ready = false;// ���£���δ׼����ʼ
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
		{ // ��ű�ǩ
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
