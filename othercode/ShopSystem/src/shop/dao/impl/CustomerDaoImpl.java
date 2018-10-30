package shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import shop.dao.CustomerDaoInt;
import shop.util.DBUtils;
//数据库操作
public class CustomerDaoImpl implements CustomerDaoInt{

	private PreparedStatement  pstmt=null;
	private ResultSet rs=null;
	private Statement stmt = null;
	private Connection conn = DBUtils.getInitInstance().getConnection();
	
	@Override
	//得到用户有多少钱
	public double getMoneyByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		double result=0;
		try {
			
		    String sql = "SELECT customer_money FROM customer WHERE id=? ";
			pstmt =  (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("CustomerDao 输出钱数：" + rs.getDouble("customer_money"));
				result = rs.getDouble("customer_money");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
//通过用户的名字得到用户有多少钱
	@Override
	public double getMoneyByCustomerName(String customerName) {
		// TODO Auto-generated method stub
		double result=0;
		try {
			
		    String sql = "SELECT customer_money FROM customer WHERE customer_name=? ";
			pstmt =  (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, customerName);
			rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("CustomerDao 输出钱数：" + rs.getDouble("Customer_money"));
				result = rs.getDouble("customer_money");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getNameByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}
//更新用户有多少钱，通过id和用户名字
	@Override
	public void updateCustomerMomey(double money, int customerId) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE customer t SET t.customer_money=? WHERE t.id=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setDouble(1, money);// 传入参数
			pstmt.setInt(2, customerId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

/*	public static void main(String[] args){
		CustomerDaoImpl c = new CustomerDaoImpl();
		c.updateCustomerMomey(600,1);
	}*/
	
	/*
		根据顾客名，更新顾客的钱
	 */
	@Override
	public void updateCustomerMomey(double money, String customername) {
		// TODO Auto-generated method stub
		try {
			System.out.println("shop.dao.ShopDao "+"dao层更新数据");
			String sql = "UPDATE customer t SET t.customer_money=? WHERE t.customer_name=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setDouble(1, money);// 传入参数
			pstmt.setString(2, customername);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
