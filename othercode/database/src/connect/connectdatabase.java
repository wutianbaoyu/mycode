package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class connectdatabase {

	Connection conn=null;
	String driver=null;
	String url=null;
	String user=null;
	String password=null;
	
	public connectdatabase() {
		

		driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		// URLָ��Ҫ���ʵ����ݿ���Web_shopping
		url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=renliziyuan";
	
		// MySQL����ʱ���û���
		user = "sa";
	
		// Java����MySQL����ʱ������
		password = "asdfghjkl168";
		
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

