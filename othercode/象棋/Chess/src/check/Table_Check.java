package check;

import gui.GameHall;

import javax.swing.ImageIcon;

public class Table_Check {
	
	public boolean left=false;
	public boolean right=false;
	public  String left_iconNum="1";
	public  String right_iconNum="1";
	public static boolean game_star=false;//������Ϊ��Ϸ��ʼ���ܻ���λ�ı�־
	public static Table_Check[] table = new Table_Check[15];// ��������Ƿ�������
//	public int num=0;
	/**
	 * ����table���飬��������
	 */
	public Table_Check(){
		
	}
	public static void ergodic_Table() {
		for (int i = 0; i < 15; i++) {
			if(table[i].left){
				GameHall.buttonarray[2*i].setIcon(new ImageIcon("./res/face/" + table[i].left_iconNum
						+ "-" + "1.gif"));

			}
			else{
				GameHall.buttonarray[2*i].setIcon(new ImageIcon(
						"./res/img/noone.gif"));
			}
			if(table[i].right){
				GameHall.buttonarray[2*i+1].setIcon(new ImageIcon("./res/face/" +table[i].right_iconNum
						+ "-" + "1.gif"));
				
			}
			else{
				GameHall.buttonarray[2*i+1].setIcon(new ImageIcon(
						"./res/img/noone.gif"));
			}
		}
	}

}
