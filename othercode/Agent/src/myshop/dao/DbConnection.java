package myshop.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * Created by MCY on 2016/9/19.
 */
public class  DbConnection 
{
	private static String url = "jdbc:mysql://localhost:3306/shop";
	private static String name = "root";
	private static String passwd = "root";
	private static Connection conn = null ;
	private static DbConnection jdbcUtilSingle = null;

	//
	private DbConnection() {
	}

	//
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 
	public static DbConnection getInitInstance() {
		if (jdbcUtilSingle == null) {
			// 加锁
			synchronized (DbConnection.class) {
				if (jdbcUtilSingle == null) {
					jdbcUtilSingle = new DbConnection();
				}
			}
		}
		return jdbcUtilSingle;
	}


	public Connection getConnection() {
		try {
			conn = (Connection) DriverManager.getConnection(url, name, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}


	public void closeConnection(ResultSet rs, Statement statement,Connection conn) {
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
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
	}

	public void closeConnection(ResultSet rs, PreparedStatement pstm,Connection conn) {
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
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
	}
	
}