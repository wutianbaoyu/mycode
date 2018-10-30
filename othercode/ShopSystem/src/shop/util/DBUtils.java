package shop.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;



/**
 * 采用单例模式实现JDBC连接的一个工具类
 * 
 * @author J
 *
 */
public final class DBUtils {

	private static String url = "jdbc:mysql://localhost:3306/shopsystem?useUnicode=true&characterEncoding=UTF8";
	private static String name = "root";
	private static String passwd = "root";
	private static Connection conn;
	private static DBUtils jdbcUtilSingle = null;

	// Constructor
	private DBUtils() {
		
		
	}

	// 在加载类执行，通过静态代码块注册数据库驱动，保证注册驱动只执行1次
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取单例 ， 取代构造函数
	public static DBUtils getInitInstance() {
		if (jdbcUtilSingle == null) {
			// 加锁
			synchronized (DBUtils.class) {
				if (jdbcUtilSingle == null) {
					jdbcUtilSingle = new DBUtils();
				}
			}
		}
		return jdbcUtilSingle;
	}

	// 获得连接
	public Connection getConnection() {
		try {
			conn = (Connection) DriverManager.getConnection(url, name, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭连接
	public void closeConnection(ResultSet rs, Statement statement,Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (con != null) {
							con.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
	}
	// 关闭连接
	public void closeConnection(ResultSet rs, PreparedStatement pstm,Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if (pstm != null) {
						pstm.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (con != null) {
							con.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
	}
	
}
