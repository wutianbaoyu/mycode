package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import action.GoddessAction;
import model.Goddess;

public class View {

	private static final String CONTEXT="欢迎来到福彩3d筛选：\n" +
			"下面是福彩3d筛选的功能列表：\n" +
			"[MCHAZHICHUSAN/M]:除三差值获取\n" +
			"[AYCCHAZHICHUSAN/A]:预测差值除三\n" +
			"[READ/R]除三本位(+试机后)及差(+试机后)预测\n"+
			"[EXIT/E]:读取横向位\n" 
			+"[COLS/C]:读取除三百位\n"
			+"[SHIWEI/S]:读取除三十位\n"
			+"[GEWEI/G]:读取除三个位\n"
			+"[QUAN/Q]:百十个全读取\n"
			+"[YUCE/Y]:预测除三百位\n"
			+"[BYUCE/B]:预测除三十位\n"
			+"[DYUCE/D]:预测除三个位\n"
			+"[TEST/T]:测试\n"
			+"[HJIOUBAI/H]:读取奇偶百\n"
			+"[IJIOUSHI/I]:读取奇偶十\n"
			+"[JJIOUGE/J]:读取奇偶个\n"
			+"[KYUCEJIOUBAI/K]:预测奇偶百\n"
			+"[LYUCEJIOUSHI/L]:预测奇偶十\n"
			+"[NYUCEJIOUGE/N]:预测奇偶个\n"
			+"[OBENWEI/O]:读取本位\n"
			+"[PSJ/P]:读取试机位\n"
			+"[XSHUNXUWEI/X]:读取顺序位\n"
			+"[UYCBENWEI/U]:预测本位\n"
			+"[VYCSJ/V]:预测试机位\n"
			+"[FYUCE/F]:全预测012\n"
			+"[WYCJIOU/W]:预测横向除三位\n"
			+"[ZYCSHUNXUWEI/Z]:预测顺序位\n";

	
	private static final String OPERATION_MCHAZHICHUSAN="MCHAZHICHUSAN";
	private static final String OPERATION_AYCCHAZHICHUSAN="AYCCHAZHICHUSAN";
	private static final String OPERATION_EXIT="EXIT";
	private static final String OPERATION_READ="READ";
	private static final String OPERATION_COLS="COLS";
	private static final String OPERATION_TEST="TEST";
	private static final String OPERATION_YUCE="YUCE";
	private static final String OPERATION_SHIWEI="SHIWEI";
	private static final String OPERATION_GEWEI="GEWEI";
	private static final String OPERATION_QUAN="QUAN";
	private static final String OPERATION_BYUCE="BYUCE";
	private static final String OPERATION_DYUCE="DYUCE";
	private static final String OPERATION_FYUCE="FYUCE";
	private static final String OPERATION_HJIOUBAI="HJIOUBAI";
	private static final String OPERATION_IJIOUSHI="IJIOUSHI";
	private static final String OPERATION_JJIOUGE="JJIOUGE";
	private static final String OPERATION_KYUCEJIOUBAI="KYUCEJIOUBAI";
	private static final String OPERATION_LYUCEJIOUSHI="LYUCEJIOUSHI";
	private static final String OPERATION_NYUCEJIOUGE="NYUCEJIOUGE";
	private static final String OPERATION_OBENWEI="OBENWEI";
	private static final String OPERATION_PSJ="PSJ";
	private static final String OPERATION_UYCBENWEI="UYCBENWEI";
	private static final String OPERATION_VYCSJ="VYCSJ";
	private static final String OPERATION_WYCJIOU="WYCJIOU";
	private static final String OPERATION_XSHUNXUWEI="XSHUNXUWEI";
	private static final String OPERATION_ZYCSHUNXUWEI="ZYCSHUNXUWEI";
	
	public static void main(String[] args) throws Exception {
		System.out.println(CONTEXT);
		
		Scanner s=new Scanner(System.in);
		GoddessAction action=new GoddessAction();
		
		String pervious=null;
		Integer step=1;
		Goddess go=null;
		
		while(s.hasNext()){
			String in=s.next();
			if(OPERATION_EXIT.equals(in.toUpperCase())
					||OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())){
				go=new Goddess();
				try {
					action.gethxchusan(go);
					System.out.println("读取横向位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取横向位失败");
				}
			}
			
			else if(OPERATION_COLS.equals(in.toUpperCase())
					||OPERATION_COLS.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_COLS.equals(pervious)){
				go=new Goddess();
				try {
					action.getcolsone(go);
					System.out.println("读取百位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取百位失败");
				}
			}	
				
			else if(OPERATION_TEST.equals(in.toUpperCase())
					||OPERATION_TEST.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_TEST.equals(pervious)){
				go=new Goddess();
				try {
					action.test(go);
					System.out.println("测试成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("测试失败");
				}
			}
			
			else if(OPERATION_YUCE.equals(in.toUpperCase())
					||OPERATION_YUCE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_YUCE.equals(pervious)){
				go=new Goddess();
				try {
					action.yc(go);
					System.out.println("预测成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测失败");
				}
			}
			
			
			else if(OPERATION_SHIWEI.equals(in.toUpperCase())
					||OPERATION_SHIWEI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_SHIWEI.equals(pervious)){
				go=new Goddess();
				try {
					action.getcolstwo(go);
					System.out.println("读取十位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取十位失败");
				}
			}	
			
			else if(OPERATION_GEWEI.equals(in.toUpperCase())
					||OPERATION_GEWEI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_GEWEI.equals(pervious)){
				go=new Goddess();
				try {
					action.getcolsthree(go);
					System.out.println("读取个位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取个位失败");
				}
			}
			
			else if(OPERATION_QUAN.equals(in.toUpperCase())
					||OPERATION_QUAN.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_QUAN.equals(pervious)){
				go=new Goddess();
				try {
					action.getcolsone(go);
					action.getcolstwo(go);
					action.getcolsthree(go);
					System.out.println("读取个十百位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取个十百位失败");
				}
			}
			
			else if(OPERATION_BYUCE.equals(in.toUpperCase())
					||OPERATION_BYUCE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_BYUCE.equals(pervious)){
				go=new Goddess();
				try {
					action.yc2(go);
					System.out.println("预测十位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测十位失败");
				}
			}
			
			
			else if(OPERATION_DYUCE.equals(in.toUpperCase())
					||OPERATION_DYUCE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_DYUCE.equals(pervious)){
				go=new Goddess();
				try {
					action.yc3(go);
					System.out.println("预测个位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测个位失败");
				}
			}
			
			
			else if(OPERATION_FYUCE.equals(in.toUpperCase())
					||OPERATION_FYUCE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_FYUCE.equals(pervious)){
				go=new Goddess();
				try {
					action.yc(go);
					action.yc2(go);
					action.yc3(go);
					System.out.println("预测成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测失败");
				}
			}
			
			
			else if(OPERATION_HJIOUBAI.equals(in.toUpperCase())
					||OPERATION_HJIOUBAI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_HJIOUBAI.equals(pervious)){
				go=new Goddess();
				try {
					action.getjioucolsone(go);
					System.out.println("读取奇偶百成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取奇偶百失败");
				}
			}
			
			else if(OPERATION_IJIOUSHI.equals(in.toUpperCase())
					||OPERATION_IJIOUSHI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_IJIOUSHI.equals(pervious)){
				go=new Goddess();
				try {
					action.getjioucolstwo(go);
					System.out.println("读取奇偶十成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取奇偶十失败");
				}
			}
			
			else if(OPERATION_JJIOUGE.equals(in.toUpperCase())
					||OPERATION_JJIOUGE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_JJIOUGE.equals(pervious)){
				go=new Goddess();
				try {
					action.getjioucolsthree(go);
					System.out.println("读取奇偶个成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取奇偶个失败");
				}
			}
			
			
			
			
			else if(OPERATION_KYUCEJIOUBAI.equals(in.toUpperCase())
					||OPERATION_KYUCEJIOUBAI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_KYUCEJIOUBAI.equals(pervious)){
				go=new Goddess();
				try {
					action.ycjioubai(go);
					System.out.println("预测奇偶百成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测奇偶百失败");
				}
			}
			
			
			else if(OPERATION_KYUCEJIOUBAI.equals(in.toUpperCase())
					||OPERATION_KYUCEJIOUBAI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_KYUCEJIOUBAI.equals(pervious)){
				go=new Goddess();
				try {
					action.ycjioubai(go);
					System.out.println("预测奇偶百成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测奇偶百失败");
				}
			}
			
			else if(OPERATION_LYUCEJIOUSHI.equals(in.toUpperCase())
					||OPERATION_LYUCEJIOUSHI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_LYUCEJIOUSHI.equals(pervious)){
				go=new Goddess();
				try {
					action.ycjioushi(go);
					System.out.println("预测奇偶十成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测奇偶十失败");
				}
			}
			
			else if(OPERATION_NYUCEJIOUGE.equals(in.toUpperCase())
					||OPERATION_NYUCEJIOUGE.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_NYUCEJIOUGE.equals(pervious)){
				go=new Goddess();
				try {
					action.ycjiouge(go);
					System.out.println("预测奇偶个成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测奇偶个失败");
				}
			}
			
			
			else if(OPERATION_OBENWEI.equals(in.toUpperCase())
					||OPERATION_OBENWEI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_OBENWEI.equals(pervious)){
				go=new Goddess();
				try {
					action.getbenwei(go);
					System.out.println("读取本位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取本位失败");
				}
			}
			
			else if(OPERATION_PSJ.equals(in.toUpperCase())
					||OPERATION_PSJ.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_PSJ.equals(pervious)){
				go=new Goddess();
				try {
					action.getsj(go);
					System.out.println("读取试机位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取试机位失败");
				}
			}
			
			
			else if(OPERATION_UYCBENWEI.equals(in.toUpperCase())
					||OPERATION_UYCBENWEI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_UYCBENWEI.equals(pervious)){
				go=new Goddess();
				try {
					action.ycwuwei(go);
					System.out.println("预测五位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测五位失败");
				}
			}
			
			
			else if(OPERATION_WYCJIOU.equals(in.toUpperCase())
					||OPERATION_WYCJIOU.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_WYCJIOU.equals(pervious)){
				go=new Goddess();
				try {
					action.getwu(go);
					System.out.println("预测横向位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测横向位失败");
				}
			}
			
			
			else if(OPERATION_VYCSJ.equals(in.toUpperCase())
					||OPERATION_VYCSJ.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_VYCSJ.equals(pervious)){
				go=new Goddess();
				try {
					action.ycsj(go);
					System.out.println("预测试机位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测试机位失败");
				}
			}
			
			
			
			else if(OPERATION_XSHUNXUWEI.equals(in.toUpperCase())
					||OPERATION_XSHUNXUWEI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_XSHUNXUWEI.equals(pervious)){
				go=new Goddess();
				try {
					action.getshunxuwei(go);
					System.out.println("读取顺序位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("读取顺序位失败");
				}
			}
			
			
			
			else if(OPERATION_ZYCSHUNXUWEI.equals(in.toUpperCase())
					||OPERATION_ZYCSHUNXUWEI.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_ZYCSHUNXUWEI.equals(pervious)){
				go=new Goddess();
				try {
					action.ycshunxuwei(go);
					System.out.println("预测顺序位成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测顺序位失败");
				}
			}
			
			else if(OPERATION_MCHAZHICHUSAN.equals(in.toUpperCase())
					||OPERATION_MCHAZHICHUSAN.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_MCHAZHICHUSAN.equals(pervious)){
				go=new Goddess();
				try {
					action.getchazhichusan(go);
					System.out.println("获取差值除三成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("获取差值除三失败");
				}
			}
			
			else if(OPERATION_AYCCHAZHICHUSAN.equals(in.toUpperCase())
					||OPERATION_AYCCHAZHICHUSAN.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_AYCCHAZHICHUSAN.equals(pervious)){
				go=new Goddess();
				try {
					action.ycchazhichusan(go);
					System.out.println("预测差值除三成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测差值除三失败");
				}
			}
			
			
			else if(OPERATION_READ.equals(in.toUpperCase())
					||OPERATION_READ.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_READ.equals(pervious)){
				go=new Goddess();
				try {
					
					action.liangchusan(go);
					System.out.println("预测除三成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("预测除三失败");
				}
			}
			
			
				}

			}

			

			
		
	}

