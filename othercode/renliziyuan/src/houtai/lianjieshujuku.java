package houtai;

import java.sql.Connection;
import java.sql.DriverManager;

public class lianjieshujuku {

	Connection conn=null;
	String driver=null;
	String url=null;
	String user=null;
	String password=null;
	
	public lianjieshujuku() {
		
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		
		// URLָ��Ҫ���ʵ����ݿ���Web_shopping
		url = "jdbc:sqlserver://localhost:1433;database=HumanSource";
	
		// MySQL����ʱ���û���
		user = "sa";
	
		// Java����MySQL����ʱ������
		password = "123456";
		
		try{
			//��һ����������Ӧ��JDBC����
			Class.forName(driver); 
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return conn;
	}

	public void setCon(Connection conn) {
		this.conn = conn;
	}
	
}
