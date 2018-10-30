package calendar2;


import java.text.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

import javax.swing.*;
public class Simple_Calendar extends JFrame implements ActionListener{


// public static void main(String[] args) {
//  
//	new Simple_Calendar();
//  //SwingConsole.run(new Simple_Calendar(),620,480); 
// }

 public static Container c = null;
 JLabel title = null;
 
 public Simple_Calendar() {

  c = this.getContentPane();
	 
  calendar = Calendar.getInstance();
  today = calendar.get(Calendar.DAY_OF_MONTH);
  panel_Header=initializtion_Header();
  panel_Week =initializtion_Week();
  panel_Calendar=initializtion_Calendar();
  //title = new JLabel(lunar.today());
  
  Lunar lunar=new Lunar(calendar);
	//System.out.println(lunar.today());
	  title = new JLabel(lunar.today(),0);//0��ʾ������ʾ����
  
  setLayout(null);
  setBounds(185,125,0,0);//������ʱ���ʼλ��
  
  //������� 
  title.setBounds(0,0,620,35);
  //title.setBackground(new Color(1,0,127));
  title.setBackground(new Color(20,141,194));
  title.setOpaque(true); 
  title.setForeground(Color.WHITE);
  title.setFont(new Font( "΢���ź�",Font.BOLD,20));
  add(title);
  
  add(panel_Week);
  panel_Week.setBounds(0,37,623,25);

  add(panel_Calendar);
  panel_Calendar.setBounds(0,62,620, 300) ;
  panel_Calendar.setBackground(new Color(247,240,238));
  initializtion_Data(calendar); 
     
  add(panel_Header);
  panel_Header.setBounds(0,365,620,35);
  panel_Header.setBackground(new Color(247,240,238));
  
//  this.setVisible(true);
//  this.setSize(620,480);
//  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//  setResizable(false);
  
 }
    private  JPanel initializtion_Header(){//��ʾ��ͷ��panel
       
     JPanel panel = new JPanel();
     year_box  = new  JComboBox();
     month_box = new JComboBox();
//     try {
//    	 //UIManager ui = UIManager.getUI(c);
//    	 //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//    	 //SwingUtilities.updateComponentTreeUI(c);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
     cross=new JRadioButton("1",false);cross.addActionListener(new LookAndFeel_Listener());
     system=new JRadioButton("2",false);system.addActionListener(new LookAndFeel_Listener());
     motif=new JRadioButton("3",false);motif.addActionListener(new LookAndFeel_Listener());
     cross.setSelected(true);
     feel_group= new ButtonGroup();
     //show_help = new JButton("˵ ��");
     
     //show_help.addActionListener(new Statement_Listener());
     
     feel_group.add(cross);feel_group.add(system);feel_group.add(motif); 
     //panel.setBorder(new EtchedBorder(5,Color.red,Color.BLUE));
     JLabel year_l = new JLabel("����ѡ�����: ",JLabel.RIGHT);
     year_l.setFont(new Font( "΢���ź�",Font.PLAIN,12));
     
     JLabel month_l = new JLabel("�·�: ",JLabel.RIGHT);
     month_l.setFont(new Font( "΢���ź�",Font.PLAIN,12));
     
     panel.setLayout(null);
     panel.setSize(620,25);
 


     for(int i = 1900 ;i < 2050 ; i++)
        year_box.addItem(""+i);
     for(int j = 1 ; j <= 12 ;j++)
        month_box.addItem(""+j) ;
    
     year_box.setSelectedIndex(calendar.get(Calendar.YEAR)-1900);
     month_box.setSelectedIndex(calendar.get(Calendar.MONTH));
     //year_box.setBorder(BorderFactory.createBevelBorder(0));
     panel.add(year_l);      year_l.setBounds(0,5,95,25);
     panel.add(year_box);  year_box.setBounds(100,5,65,25);
     panel.add(month_l);    month_l.setBounds(160,5,45,25);
     panel.add(month_box);month_box.setBounds(210,5,45,25);
     JLabel look_feel = new JLabel("���:",JLabel.RIGHT);
     look_feel.setFont(new Font( "΢���ź�",Font.PLAIN,12));
     
     panel.add(look_feel);look_feel.setBounds(282,5,38,25);
     panel.add(cross);       cross.setBounds(325,5,38,25);
     panel.add(system);     system.setBounds(362,5,32,25);
     panel.add(motif);       motif.setBounds(399,5,38,25);
     panel.setBackground(new Color(247,240,238));
     cross.setBackground(new Color(247,240,238));
     system.setBackground(new Color(247,240,238));
     motif.setBackground(new Color(247,240,238));
     
     year_box.addActionListener(this);
     month_box.addActionListener(this);
     return panel;
    }
    private JPanel initializtion_Week(){//��ʾ���ڵ�panel
  JPanel panel = new JPanel();
  
  panel.setLayout(new GridLayout(1,7,1,0));
  
  String columnNames[]={"������","����һ","���ڶ�","������",
                                  "������","������","������"};
        JLabel label =null;
        for(int i=0;i<7;i++){
         label = new JLabel(columnNames[i],JLabel.CENTER);
         if(i == 0 || i == 6)
           label.setForeground(Color.RED);
         	label.setBackground(new Color(153,204,255));
         	label.setFont(new Font( "΢���ź�",Font.BOLD,12));
         	label.setOpaque(true); 
         
         //label.setBorder(new LineBorder(Color.BLUE));
         panel.add(label);
        }
        return panel;
 }
    private JPanel initializtion_Calendar(){//��ʾ���ڵ�panel
        JPanel panel = new JPanel();
      
        panel.setLayout(new GridLayout(6,7));
        for( int i = 0 ; i < 6 ; i++ ){
          for(int j = 0 ; j < 7 ; j++ ){
         label=new JLabel("",JLabel.CENTER);
         
         datas[i][j] = label;
         //label.setBorder(new LineBorder(Color.BLUE));
         if(j==0 || j==6)
            label.setForeground(Color.RED);  
         datas[i][j].addMouseListener(new List_MouseListener());
         panel.add(label);
        }
     }
     return panel;
    }
    
    public  void clear_Data(){//������ݵ�
     for(int i = 0 ; i < 6 ; i++ )
      for(int j = 0 ; j < 7 ; j++ ){
       datas[i][j].setText("");
       if(j==0 || j==6)
          datas[i][j].setForeground(Color.RED);
       else
          datas[i][j].setForeground(null);
      }
          
    }
    public  void initializtion_Data(Calendar calendar){//��ʼ������
   ////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////
    /* ���պͼ�����
��ʽ����ʼ��(yyyy)+��(mm)+��(dd)


0000��ʾ��ʼ�겻��*/
HashMap<String,String>  sFestival =new  HashMap<String,String>();
//String []sFestival_={
sFestival.put("0101","  Ԫ��");
sFestival.put("0214","���˽�");
sFestival.put("0308","��Ů��");
sFestival.put("0312","ֲ����");
sFestival.put("0401","���˽�");
sFestival.put("0501","�Ͷ���");
sFestival.put("0504","�����");
sFestival.put("0601","��ͯ��");
sFestival.put("0701","������");
sFestival.put("0801","������");
sFestival.put("0910","��ʦ��");
sFestival.put("1001","�����");
sFestival.put("1031","��ʥ��");
sFestival.put("1112","����ɽ����");
sFestival.put("1225","ʥ����");
sFestival.put("1226","ë�󶫵���");
//};
//ĳ�µڼ������ڼ�
//��ʼ��(4λ)+��(2λ)+�ڼ���(1λ)+���ڼ�(1λ)
HashMap<String,String>  wFestival =new  HashMap<String,String>();
//String []wFestival={
wFestival.put("0520","ĸ�׽�");
wFestival.put("0630","���׽�");
wFestival.put("1144","�ж���");
//};
//ũ�� 99��ʾ�����һ��
HashMap<String,String>  lFestival =new  HashMap<String,String>();
//String []lFestival={
lFestival.put("0101","�� ��");
lFestival.put("0102","�������");
lFestival.put("0103","�������");
lFestival.put("0115","Ԫ����");
lFestival.put("0505","�����");
lFestival.put("0707","�� Ϧ");
lFestival.put("0815","�����");
lFestival.put("0909","������");
lFestival.put("1208","���˽�");
lFestival.put("1299","�� Ϧ");
//};
    
/////////////////////////////////////////////////////////////
    
   //////////////////////////////////////////////////// 
      this.calendar = calendar;
      today = calendar.get(Calendar.DAY_OF_MONTH);
      int month = calendar.get(Calendar.MONTH);
      int weekindexDay;
   int weekindexMonth;
   
   calendar.set(Calendar.DATE,1);
  
  while(calendar.get(Calendar.MONTH)==month)
  {   weekindexMonth=calendar.get(Calendar.WEEK_OF_MONTH)-1;
      weekindexDay=calendar.get(Calendar.DAY_OF_WEEK)-1;
      int day=calendar.get(Calendar.DAY_OF_MONTH);
      ///////////////////////////////////////////////
      String today_,month_;
      today_ =day < 10?  "0" + day:"" + day;
      month_ =month<10?  "0" +(month+1):""+(month+1);
      Lunar lunar = new Lunar(calendar);
      String lunar_= lunar.toString();
      ///////////////////////////////////////////
      if( null != sFestival.get(month_+today_))
           lunar_="<font color=red>"+sFestival.get(month_+today_);
      ///////
      String wFestival_=month_+(weekindexMonth)+(weekindexDay);
      
      if( null != wFestival.get(wFestival_)){
       lunar_="<font color=red>"+wFestival.get(wFestival_);
       //System.out.println(wFestival_);
      }
          
      
      
      
      if( null != lFestival.get(lunar.numeric_md()))
          lunar_="<font color=red>"+lFestival.get(lunar.numeric_md());
      
      
          
      //�����Ϧ
      Calendar temp_calendar = Calendar.getInstance();
      temp_calendar.set(calendar.get(Calendar.YEAR),month,day+1);
      
      //temp_calendar.add(Calendar.DAY_OF_MONTH,1);
      Lunar temp_lunar = new Lunar(temp_calendar);
      String temp_str = temp_lunar.numeric_md();
       if(temp_str.equals("0101"))
          lunar_="<font color=red>"+lFestival.get("1299");
      ///�����Ϧ����
      //////////////////////////////////////////
      String day_str;
      if(day<10) day_str="<html><center><font size=6>"+today_;
      else       day_str="<html><center><font size=6>"+today_;
      
      day_str+="</font><br>"+lunar_;
    
      if(day==today)
         datas[weekindexMonth][weekindexDay].setForeground(Color.GREEN);
      datas[weekindexMonth][weekindexDay].setText(day_str);
      calendar.add(Calendar.DATE,1);
  } 
     
     
     
 /////////////////////////////////////////////////    
    }
    public  void actionPerformed(ActionEvent e){
    	//���ں���ݵ�ѡ�����
	     int year = Integer.parseInt(year_box.getSelectedItem().toString()) ;
	     int month= Integer.parseInt(month_box.getSelectedItem().toString())-1 ;
	     calendar.set(year,month,today);
	     clear_Data();
	     initializtion_Data(calendar);
     
    }
    class LookAndFeel_Listener implements ActionListener{//ѡ����۵���ʽ
 public void actionPerformed(ActionEvent ev){
  JRadioButton o = (JRadioButton)ev.getSource();
  String str=o.getText();
  if("1" == str){
   try {
	   
	   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch(Exception e) { e.printStackTrace(); } 
  }
  else if("2" == str){
   try{
	   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             } catch(Exception e) {e.printStackTrace();}
  }else if("3" == str){
   try{
	   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");}
            catch(Exception e) {e.printStackTrace(); }
  } 
  	SwingUtilities.updateComponentTreeUI(c);
   }
 
    }
    class Statement_Listener implements ActionListener{//��ʾ������Ϣ
     JDialog dialog=null;
     public void actionPerformed(ActionEvent ev){
        String statment = "";

        dialog=new  JDialog(Simple_Calendar.this, "������������� 2008 beta1",true); 
              dialog.setLayout(null);
              dialog.setBounds(285,215,365,185);
              JLabel label_s = new JLabel(statment);//label_s.setBackground(Color.RED);
              JButton button=new JButton("ȷ ��");
              button.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent evet){
                  dialog.setVisible(false);
               }
              });
              dialog.add(label_s);label_s.setBounds(20,0,365,100);
              dialog.add(button);  button.setBounds(145,110,65,25);
              dialog.setVisible(true);
       }
     
    }
    class List_MouseListener implements MouseListener{//�������ʱ��ʾ����Ϣ
     JLabel labe=null;
     String weeks[]={"������","����һ","���ڶ�","������",
                                  "������","������","������"};
                                 


        public String constellation(Calendar cal){
   
                String [][]con ={
                     {"ˮƿ��","0122","0221"},
                     {"˫����","0222","0321"},
                     {"������","0322","0420"}, 
                     {"��ţ��","0421","0521"}, 
                     {"˫����","0522","0621"}, 
                     {"��з��","0621","0721"}, 
                     {"ʨ����","0722","0821"}, 
                     {"��Ů��","0822","0921"},  
                     {"�����","0922","1021"}, 
                     {"��Ы��","1022","1121"}, 
                     {"������","1122","1221"}, 
                     {"Ħ����","1222","0121"}};
                int month=cal.get(Calendar.MONTH)+1;
                int today=cal.get(Calendar.DAY_OF_MONTH);
                String month_str=month<10?"0"+month:""+month;
                String today_str=today<10?"0"+today:""+today;
                String str=month_str+today_str;
                for( int i = 0 ; i < con.length-1 ; i++ )
                {
                     if(Integer.parseInt(str) >= Integer.parseInt(con[i][1]) && Integer.parseInt(str) <= Integer.parseInt(con[i][2]))
                        return con[i][0]; 
                 
                }
                if((Integer.parseInt(str) >= Integer.parseInt(con[11][1]) && Integer.parseInt(str) < 1232) ||  Integer.parseInt(str) <=Integer.parseInt( con[11][2]) )
                   return  con[11][0];
        
               return "error!";
        }
        
     public void mouseClicked(MouseEvent e) {//��갴��������ϵ��������²��ͷţ�ʱ���á�
         
        }     
        public void mouseEntered(MouseEvent e) {//�����뵽�����ʱ���á� 
             labe=(JLabel)e.getSource();
             String lab =labe.getText();
             if(lab != ""){
              labe.setBackground(Color.red);
              String day=lab.substring(lab.indexOf("size=6>")+7,lab.indexOf("</font>"));
                //String lun=lab.substring(lab.indexOf("<br>")+4);
                String message = "<html><body><center>��Ԫ "+year_box.getSelectedItem()+"��"+
                                 month_box.getSelectedItem()+"��"+Integer.parseInt(day)+"��";
                calendar.set(Integer.parseInt(year_box.getSelectedItem().toString()),
                             Integer.parseInt(month_box.getSelectedItem().toString())-1,
                             Integer.parseInt(day));
                Lunar lunar=new Lunar(calendar);
                message+="<br><font color=red>"+weeks[(calendar.get(calendar.DAY_OF_WEEK)-1)];
                message+="&nbsp;&nbsp;&nbsp;&nbsp;"+constellation(calendar)+"</font><br>ũ�� ";
                message+=lunar.get_month()+"��"+lunar.get_Big_Or_Small()+"&nbsp;&nbsp;&nbsp;&nbsp;"+lunar.get_date()+"��";
                //message+=lunar.get_JQ();
                labe.setToolTipText(message);
                labe.setBackground(new Color(192,192,192));
                //labe.setBackground(Color.gray);
                labe.setOpaque(true);
               // System.out.println(day+":"+lun);
             }
             
        }  
        public void mouseExited(MouseEvent e) {//  ����뿪���ʱ���á�
                   
         
                        labe.setBackground(null);
                        labe.setOpaque(true);
        }
        public void mousePressed(MouseEvent e) {//  ��갴��������ϰ���ʱ���á� 
        
        }
        public void mouseReleased(MouseEvent e) {//  ��갴ť��������ͷ�ʱ���á� 
        }


    }
    /////////////////////////////////////////////////////////
    private JLabel datas[][]=new JLabel[6][7];//��ʾһ�µ�����
    private JLabel temp_label=null;
    private  JLabel label=null;
    private JPanel panel_Header,panel_Week,panel_Calendar;
    private Calendar calendar=null;
    private  JComboBox  year_box = null ;
    private  JComboBox month_box = null ;
    private int today ; 
    private JRadioButton cross=null,system=null,motif=null;
    private ButtonGroup feel_group=null;
    private JButton show_help =null;
    
     
}


 


/*
 *��������������
 * �Ǵ������е����ģ�
 */
  class  Lunar  { 
     private static   int  year;
     private   int  month;
     private   int  day;
     private   boolean  leap;
     final   static  String chineseNumber[]  =   { "һ","��","��","��","��","��","��","��","��","ʮ","ʮһ","ʮ��"};
     final   static  String Big_Or_Small[]  =    { "��","С","��","С","��","С","��","��","С","��","С"  ,"��"};
     private String[] LunarHolDayName = 
            { 
                "С��", "��", "����", "��ˮ", 
                "����", "����", "����", "����", 
                "����", "С��", "â��", "����", 
                "С��", "����", "����", "����", 
                "��¶", "���", "��¶", "˪��", 
                "����", "Сѩ", "��ѩ", "����"};


     
     static  SimpleDateFormat chineseDateFormat  =   new  SimpleDateFormat( " yyyy��MM��dd�� " );
     final   static   long [] lunarInfo  =   new   long [] 
     { 0x04bd8 ,  0x04ae0 ,  0x0a570 ,  0x054d5 ,  0x0d260 ,  0x0d950 ,  0x16554 ,  0x056a0 ,  0x09ad0 ,  0x055d2 ,
      0x04ae0 ,  0x0a5b6 ,  0x0a4d0 ,  0x0d250 ,  0x1d255 ,  0x0b540 ,  0x0d6a0 ,  0x0ada2 ,  0x095b0 ,  0x14977 ,
      0x04970 ,  0x0a4b0 ,  0x0b4b5 ,  0x06a50 ,  0x06d40 ,  0x1ab54 ,  0x02b60 ,  0x09570 ,  0x052f2 ,  0x04970 ,
      0x06566 ,  0x0d4a0 ,  0x0ea50 ,  0x06e95 ,  0x05ad0 ,  0x02b60 ,  0x186e3 ,  0x092e0 ,  0x1c8d7 ,  0x0c950 ,
      0x0d4a0 ,  0x1d8a6 ,  0x0b550 ,  0x056a0 ,  0x1a5b4 ,  0x025d0 ,  0x092d0 ,  0x0d2b2 ,  0x0a950 ,  0x0b557 ,
      0x06ca0 ,  0x0b550 ,  0x15355 ,  0x04da0 ,  0x0a5d0 ,  0x14573 ,  0x052d0 ,  0x0a9a8 ,  0x0e950 ,  0x06aa0 ,
      0x0aea6 ,  0x0ab50 ,  0x04b60 ,  0x0aae4 ,  0x0a570 ,  0x05260 ,  0x0f263 ,  0x0d950 ,  0x05b57 ,  0x056a0 ,
      0x096d0 ,  0x04dd5 ,  0x04ad0 ,  0x0a4d0 ,  0x0d4d4 ,  0x0d250 ,  0x0d558 ,  0x0b540 ,  0x0b5a0 ,  0x195a6 ,
      0x095b0 ,  0x049b0 ,  0x0a974 ,  0x0a4b0 ,  0x0b27a ,  0x06a50 ,  0x06d40 ,  0x0af46 ,  0x0ab60 ,  0x09570 ,
      0x04af5 ,  0x04970 ,  0x064b0 ,  0x074a3 ,  0x0ea50 ,  0x06b58 ,  0x055c0 ,  0x0ab60 ,  0x096d5 ,  0x092e0 ,
      0x0c960 ,  0x0d954 ,  0x0d4a0 ,  0x0da50 ,  0x07552 ,  0x056a0 ,  0x0abb7 ,  0x025d0 ,  0x092d0 ,  0x0cab5 ,
      0x0a950 ,  0x0b4a0 ,  0x0baa4 ,  0x0ad50 ,  0x055d9 ,  0x04ba0 ,  0x0a5b0 ,  0x15176 ,  0x052b0 ,  0x0a930 ,
      0x07954 ,  0x06aa0 ,  0x0ad50 ,  0x05b52 ,  0x04b60 ,  0x0a6e6 ,  0x0a4e0 ,  0x0d260 ,  0x0ea65 ,  0x0d530 ,
      0x05aa0 ,  0x076a3 ,  0x096d0 ,  0x04bd7 ,  0x04ad0 ,  0x0a4d0 ,  0x1d0b6 ,  0x0d250 ,  0x0d520 ,  0x0dd45 ,
      0x0b5a0 ,  0x056d0 ,  0x055b2 ,  0x049b0 ,  0x0a577 ,  0x0a4b0 ,  0x0aa50 ,  0x1b255 ,  0x06d20 ,  0x0ada0 } ;
      
     public final static String[] nStr1 = new String[] { "", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "ʮһ",
     "ʮ��" };
          // ====== ����ũ�� y��������� 
     final   private   static   int  yearDays( int  y)  {
         int  i, sum  =   348 ;
         for  (i  =   0x8000 ; i  >   0x8 ; i  >>=   1 )  {
             if  ((lunarInfo[y  -   1900 ]  &  i)  !=   0 ) sum  +=   1 ;
        } 
         return  (sum  +  leapDays(y));
    }


     // ====== ����ũ�� y�����µ����� 
     final   private   static   int  leapDays( int  y)  {
         if  (leapMonth(y)  !=   0 )  {
             if  ((lunarInfo[y  -   1900 ]  &   0x10000 )  !=   0 )
                 return   30 ;
             else 
                 return   29 ;
        }   else 
             return   0 ;
    }


     // ====== ����ũ�� y�����ĸ��� 1-12 , û�򴫻� 0 
     final   private   static   int  leapMonth( int  y)  {
         return  ( int ) (lunarInfo[y  -   1900 ]  &   0xf );
    }


     // ====== ����ũ�� y��m�µ������� 
     final   private   static   int  monthDays( int  y,  int  m)  {
         if  ((lunarInfo[y  -   1900 ]  &  ( 0x10000   >>  m))  ==   0 )
             return   29 ;
         else 
             return   30 ;
    }


     // ====== ����ũ�� y�����Ф 
     final   public static  String animalsYear()  {
         final  String[] Animals  =   new  String[] { "��","ţ","��","��","��","��","��","��","��","��","��","��"};
        return  Animals[(year  -   4 )  %   12 ];
    }


     // ====== ���� ���յ�offset ���ظ�֧, 0=���� 
     final   private   static  String cyclicalm( int  num)  {
         final  String[] Gan  =   new  String[] { "��","��","��","��","��","��","��","��","��","��"};
         final  String[] Zhi  =   new  String[] { "��","��","��","î","��","��","��","δ","��","��","��","��"};
         return  (Gan[num  %   10 ]  +  Zhi[num  %   12 ]);
    }


     // ====== ���� offset ���ظ�֧, 0=���� 
     final   public static  String cyclical()  {
         int  num  =  year  -   1900   +   36 ;
         return  (cyclicalm(num));
    }


     /** */ /** 
     * ����y��m��d�ն�Ӧ��ũ��.
     * yearCyl3:ũ������1864�������              ?
     * monCyl4:��1900��1��31������,������
     * dayCyl5:��1900��1��31����������,�ټ�40      ?
     *  @param  cal 
     *  @return  
      */ 
     public  Lunar(Calendar cal)  {
      //cal.add(cal.get(Calendar.DAY_OF_MONTH),1);
        @SuppressWarnings( " unused " )  int  yearCyl, monCyl, dayCyl;
         int  leapMonth  =   0 ;
        Date baseDate  =   null ;
         try   {
            baseDate  =  chineseDateFormat.parse( " 1900��1��31�� " );
        }   catch  (ParseException e)  {
            e.printStackTrace();   // To change body of catch statement use Options | File Templates. 
        }


         // �����1900��1��31���������� 
         int  offset  =  ( int ) ((cal.getTime().getTime()  -  baseDate.getTime())  /   86400000L );
        dayCyl  =  offset  +   40 ;
        monCyl  =   14 ;


         // ��offset��ȥÿũ���������
         //  ���㵱����ũ���ڼ���
         // i���ս����ũ�������
         // offset�ǵ���ĵڼ��� 
         int  iYear, daysOfYear  =   0 ;
         for  (iYear  =   1900 ; iYear  <   2050   &&  offset  >   0 ; iYear ++ )  {
            daysOfYear  =  yearDays(iYear);
            offset  -=  daysOfYear;
            monCyl  +=   12 ;
        } 
         if  (offset  <   0 )  {
            offset  +=  daysOfYear;
            iYear -- ;
            monCyl  -=   12 ;
        } 
         // ũ����� 
        year  =  iYear;


        yearCyl  =  iYear  -   1864 ;
        leapMonth  =  leapMonth(iYear);  // ���ĸ���,1-12 
        leap  =   false ;


         // �õ��������offset,�����ȥÿ�£�ũ��������������������Ǳ��µĵڼ��� 
         int  iMonth, daysOfMonth  =   0 ;
         for  (iMonth  =   1 ; iMonth  <   13   &&  offset  >   0 ; iMonth ++ )  {
             // ���� 
             if  (leapMonth  >   0   &&  iMonth  ==  (leapMonth  +   1 )  &&   ! leap)  {
                 -- iMonth;
                leap  =   true ;
                daysOfMonth  =  leapDays(year);
            }   else 
                daysOfMonth  =  monthDays(year, iMonth);


            offset  -=  daysOfMonth;
             // ������� 
             if  (leap  &&  iMonth  ==  (leapMonth  +   1 )) leap  =   false ;
             if  ( ! leap) monCyl ++ ;
        } 
         // offsetΪ0ʱ�����Ҹղż�����·������£�ҪУ�� 
         if  (offset  ==   0   &&  leapMonth  >   0   &&  iMonth  ==  leapMonth  +   1 )  {
             if  (leap)  {
                leap  =   false ;
            }   else   {
                leap  =   true ;
                 -- iMonth;
                 -- monCyl;
            } 
        } 
         // offsetС��0ʱ��ҲҪУ�� 
         if  (offset  <   0 )  {
            offset  +=  daysOfMonth;
             -- iMonth;
             -- monCyl;
        } 
        month  =  iMonth;
        day  =  offset  +   1 ;
    }


     public   static  String getChinaDayString( int  day)  {
        String chineseTen[]  =   { "��" ,  "ʮ" ,  "إ" ,  "ئ" } ;
         int  n  =  day  %   10   ==   0   ?   9  : day  %   10   -   1 ;
         if  (day  >   30 )
             return   "" ;
         if  (day  ==   10 )
             return   "��ʮ" ;
         else 
             return  chineseTen[day  /   10 ]  +  chineseNumber[n];
    }


    public  String toString()  {
         return  /*cyclical() +   "��"   + */ (leap  ?   "��"  :  "" )  +  chineseNumber[month  -   1 ]  +   "��"   +  getChinaDayString(day);
    } 
    public String numeric_md(){//���ذ��������ֵ���������
     String temp_day;
     String temp_mon;
     temp_mon=month<10?"0"+month:""+month;
     temp_day=day < 10?"0"+ day:""+ day;
     
     return temp_mon+temp_day;
    }
    public String get_month(){//�����������·�
     return chineseNumber[month  -   1 ];
    }
    public String get_date(){//������������
     return getChinaDayString(day);
    }
    public String get_Big_Or_Small(){//���ص��·ݵĴ��С
     return Big_Or_Small[month  -   1 ];
    }        
    public static String today() {
 	   Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
 	   int year = today.get(Calendar.YEAR);
 	   int month = today.get(Calendar.MONTH) + 1;
 	   int date = today.get(Calendar.DATE);

 	   StringBuffer sToday = new StringBuffer();
 	   try {
 	    sToday.append(" ũ��");
 	    sToday.append(cyclical());
 	   sToday.append("��");
 	    sToday.append('��');
 	    sToday.append(animalsYear());
 	    sToday.append('��');
 	    return sToday.toString();
 	   } finally {
 	    sToday = null;
 	   }
 	}   
}
  
  
//class SwingConsole {//�ṩ��ȫ�̻߳���
// public static void run(final JFrame f,final int width,final int height){
//  SwingUtilities.invokeLater(new Runnable(){
//   public void run(){
//   
//    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    f.setSize(width,height);
//    f.setVisible(true);
//   }
//  });
// }
//}
