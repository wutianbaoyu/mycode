package check;

import java.sql.*;   
public class check {  
public static void main(String[] srg) {  
     //����JDBC����  
   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    //���ӷ����������ݿ�sample  
   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=renliziyuan";  
   String userName = "sa";   //Ĭ���û���  
   String userPwd = "asdfghjkl168";   //����  
 
   Connection dbConn;  
   try {  
   Class.forName(driverName);  
   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);  
   System.out.println("Connection Successful!");   //������ӳɹ� ����̨���
   } catch (Exception e) {  
   e.printStackTrace();  
   }  
}  
} 