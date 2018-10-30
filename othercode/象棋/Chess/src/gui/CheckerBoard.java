package gui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import qizi_zoufa.Qizi;

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
public class CheckerBoard extends JPanel {

	/*
	 * 画棋子需要借助的数组
	 */
	private int pos_red[][] = { { 17, 18, 19, 20, 21, 22, 23, 24, 25 },
								{ 0,   0,  0,  0,  0,  0,  0,  0,  0 },
								{ 0,  26,  0,  0,  0,  0,  0,  27, 0 },
								{ 28,  0, 29,  0, 30,  0, 31,  0, 32 },
								{ 0 ,  0,  0,  0,  0,  0,  0,  0,  0 },
								{ 0,   0,  0,  0,  0,  0,  0,  0,  0 }, 
								{ 12,  0, 13,  0, 14,  0, 15,  0, 16 },
								{ 0,  10,  0,  0,  0,  0,  0, 11,  0 }, 
								{ 0,   0,  0,  0,  0,  0,  0,  0,  0 },
								{ 1,   2,  3,  4,  5,  6,  7,  8,  9 } };

	private int pos_black[][] = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 10, 0, 0, 0, 0, 0, 11, 0 },
								{ 12, 0, 13, 0, 14, 0, 15, 0, 16 }, 
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
								{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
								{ 0, 26, 0, 0, 0, 0, 0, 27, 0 }, 
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };
	public int pos[][];
	private Image QiPan = null;
	private Image QiZi = null;
	private Image xuanze = null;
	public boolean checkselect = false;
	private boolean checkselect1 = false;
	int x = 0, y = 0;
	public int x1 = 0;

	public int y1 = 0;
	public int x2 = 0;// 原来的坐标,显示轨迹的用途

	public int y2 = 0;
	int oldx, oldy;
	public static String color;
	public static boolean ready = false;
	public boolean chess_able = false;
	public Qizi guize;

	private AudioClip audio;
	public CheckerBoard() {
		super();
		initGUI();
		try {
			xuanze = ImageIO.read(new File("./res/qizi/" + "select.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		// 在这里写自己的画图代码
		g.drawImage(QiPan, 0, 0, this);
		// if(check==false){
		if (ready) {

			if (color.equals("red")) {

				pos = pos_red;
				set_QiZi(g);
			}
			if (color.equals("black")) {

				pos = pos_black;
				set_QiZi(g);
			}
		}
		// }
		if (checkselect) {
			g.drawImage(xuanze, x1, y1, 42, 42, this);
			if (x2 != 0 && y2 != 0) {// 不是刚点的话就显示出轨迹
				g.drawImage(xuanze, x2, y2, 42, 42, this);
			}

		}

	}

	/**
	 * @param g
	 */
	private void set_QiZi(Graphics g) {
//		for (int i = 0; i < pos.length; i++) {
//			for (int j = 0; j < pos[i].length; j++) {
//				System.out.print(pos[i][j]);
//			}
//			System.out.println();
//			
//		}
		for (int i = 0; i < pos.length; i++) {

			for (int j = 0; j < pos[i].length; j++) {

				try {
					QiZi = ImageIO.read(new File("./res/qizi/" + pos[i][j]
							+ ".gif"));
					x = 12 + j * 48;
					y = 10 + i * 48;
					g.drawImage(QiZi, x, y, 42, 42, this);
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}

		}
	}

	private void initGUI() {
		try {
			{
				this.addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseMoved(MouseEvent evt) {
						thisMouseMoved(evt);
					}
				});
			}
			{
				this.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(MouseEvent evt) {
						thisMouseClicked(evt);
					}
				});
			}
			QiPan = ImageIO.read(new File("./res/qizi/xqboard.gif"));
			// ImageIcon qipan=new ImageIcon("./res/qizi/xqboard.gif");
			// setPreferredSize(new
			// Dimension(qipan.getIconWidth(),qipan.getIconHeight()));
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void thisMouseClicked(MouseEvent evt) {

		if (chess_able) {

			try {
				if (pos[evt.getY() / 48][evt.getX() / 48] == 0
						&& checkselect1 == true) {//有选中，走棋
					if(guize.check(color,pos, evt.getY() / 48, evt.getX() / 48, oldy, oldx)){//符合规则才能走
						
						Log_In_Window.output.writeUTF("zou_qi"+";"+(9-evt.getY() / 48)+";"+(8-evt.getX() / 48)+";"+(9-oldy)+";"+(8-oldx));
						pos[evt.getY() / 48][evt.getX() / 48] = pos[oldy][oldx];
						pos[oldy][oldx] = 0;
						x2 = 12 + oldx * 48;//显示轨迹
						y2 = 10 + oldy * 48;//显示轨迹
						checkselect1 = false;
						chess_able=false;//下一步不是轮到他
						this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						File f = new File("./res/audio/go.wav");
						audio = Applet.newAudioClip(f.toURI().toURL());
						audio.play();
					}
					else{
						checkselect = false;
						checkselect1=false;
						System.out.println("不能这样走");
					}
				} else if (pos[evt.getY() / 48][evt.getX() / 48] == 0) {//之前没选，只点空白处就不让显示选中光标
					checkselect = false;
				} else if (pos[evt.getY() / 48][evt.getX() / 48] != 0) {//点击的那里有棋子
					if (checkselect1) {//之 
						//前有点击过棋子，为选中状态，判断符合后让其走棋
						
						if(color.equals("red")){
							if(pos[evt.getY() / 48][evt.getX() / 48]>=17){//点对方
								
								eat_Chess(evt);
								
							}
							else{
								checkselect = false;
								checkselect1=false;
								System.out.println("不能这样走");
							}
						}
						else if(color.equals("black")){
							if(pos[evt.getY() / 48][evt.getX() / 48]<17){
								
								eat_Chess(evt);
								
							}
							else{
								checkselect = false;
								checkselect1=false;
								System.out.println("不能这样走");
							}
						}
					} else {//之前没选过棋子，判断是否要让其显示选中光标，点自己方则选中，点对方则不让其选中

						if(color.equals("red")){
							
							if(pos[evt.getY() / 48][evt.getX() / 48]<17){//小于17则为红棋本方
								
								checkselect = true;//让paint显示选择图标
								checkselect1 = true;//表为选中状态
								x2 = 0;//不需要显示轨迹
								y2 = 0;
								File f = new File("./res/audio/select.wav");
								audio = Applet.newAudioClip(f.toURI().toURL());
								audio.play();
							}
							else{//点击对方就取消光标显示
								checkselect = false;
							}
						}else if(color.equals("black")){
							if(pos[evt.getY() / 48][evt.getX() / 48]>=17){//大于等于17则为黑棋本方
								
								checkselect = true;//让paint显示选择图标
								checkselect1 = true;//表为选中状态
								x2 = 0;//不需要显示轨迹
								y2 = 0;
								File f = new File("./res/audio/select.wav");
								audio = Applet.newAudioClip(f.toURI().toURL());
								audio.play();
							}
							else{//点击对方就取消光标显示
								checkselect = false;
							}
						}
					}

				}
				oldx = evt.getX() / 48;
				oldy = evt.getY() / 48;
				x1 = 12 + oldx * 48;
				y1 = 10 + oldy * 48;
				repaint();
			} catch (Exception e) {

			}
			
			
		}
	}

	/**
	 * @param evt
	 * @throws IOException
	 */
	private void eat_Chess(MouseEvent evt) throws IOException {
		if(guize.check(color,pos, evt.getY() / 48, evt.getX() / 48, oldy, oldx)){//符合规则才能走
			
			Log_In_Window.output.writeUTF("zou_qi"+";"+(9-evt.getY() / 48)+";"+(8-evt.getX() / 48)+";"+(9-oldy)+";"+(8-oldx));
			if(pos[evt.getY() / 48][evt.getX() / 48]==5||pos[evt.getY() / 48][evt.getX() / 48]==21){
				JOptionPane.showMessageDialog(null, "你赢了！",
						"提示", JOptionPane.INFORMATION_MESSAGE);
			}
			if(pos[evt.getY() / 48][evt.getX() / 48]==17||pos[evt.getY() / 48][evt.getX() / 48]==25||pos[evt.getY() / 48][evt.getX() / 48]==1||pos[evt.getY() / 48][evt.getX() / 48]==9){
				
				File f = new File("./res/audio/unbelievable.wav");
				audio = Applet.newAudioClip(f.toURI().toURL());
				audio.play();
			}else if(pos[oldy][oldx]==10||pos[oldy][oldx]==11||pos[oldy][oldx]==26||pos[oldy][oldx]==27){
				
				File f = new File("./res/audio/Fireinthehole_Grenade_GR_C.wav");
				audio = Applet.newAudioClip(f.toURI().toURL());
				audio.play();
				f = new File("./res/audio/baozha.wav");
				audio = Applet.newAudioClip(f.toURI().toURL());
				audio.play();
			}
			else{
				File f = new File("./res/audio/Grenadekill_GR.wav");
				audio = Applet.newAudioClip(f.toURI().toURL());
				audio.play();
			}
			pos[evt.getY() / 48][evt.getX() / 48] = pos[oldy][oldx];
			pos[oldy][oldx] = 0;
			x2 = 12 + oldx * 48;//显示轨迹
			y2 = 10 + oldy * 48;//显示轨迹
			checkselect = true;
			checkselect1 = false;
			chess_able=false;
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
		}
		else{
			checkselect = false;
			checkselect1=false;
			System.out.println("不能这样走");
		}
	}
	
	private void thisMouseMoved(MouseEvent evt) {
		
		try {
			
			if (checkselect1) {//有选中，走棋
				if(guize.check(color,pos, evt.getY() / 48, evt.getX() / 48, oldy, oldx)){//符合规则才能走
					
					
					if(color.equals("red")){
						if(pos[evt.getY() / 48][evt.getX() / 48]>=17||pos[evt.getY() / 48][evt.getX() / 48]==0){//点对方
							
							this.setCursor(new Cursor(Cursor.HAND_CURSOR));
						}
						
					}
					else if(color.equals("black")){
						if(pos[evt.getY() / 48][evt.getX() / 48]<17){
							
							this.setCursor(new Cursor(Cursor.HAND_CURSOR));
						}
					}
				}
				else{
					
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			}
		} catch (Exception e) {
			
		}
		
	}

}
