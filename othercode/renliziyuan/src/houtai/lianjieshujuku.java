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
		
		// URL指向要访问的数据库名Web_shopping
		url = "jdbc:sqlserver://localhost:1433;database=HumanSource";
	
		// MySQL配置时的用户名
		user = "sa";
	
		// Java连接MySQL配置时的密码
		password = "123456";
		
		try{
			//第一步：加载相应的JDBC驱动
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
