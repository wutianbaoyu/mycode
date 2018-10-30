package DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBUtil {
	private static String url = "jdbc:mysql://localhost:3306/caipiao?useUnicode=true&characterEncoding=UTF8";
	private static String name = "root";
	private static String passwd = "root";
	private static Connection conn;
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection(url, name, passwd);
		Statement stmt=(Statement) conn.createStatement();
		
	}
}
