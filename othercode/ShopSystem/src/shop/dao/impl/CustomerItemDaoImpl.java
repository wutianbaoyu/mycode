package shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import shop.ItemInfo;
import shop.dao.CustomerItemDaoInt;
import shop.util.DBUtils;

public class CustomerItemDaoImpl implements CustomerItemDaoInt{
	
	private PreparedStatement  pstmt=null;
	private ResultSet rs=null;
	private Statement stmt = null;
	private Connection conn = DBUtils.getInitInstance().getConnection();
	//通过用户id得到用户拥有的商品
	@Override
	public List<ItemInfo> getCustomerInventoryById(int id) {
		List<ItemInfo> result = new ArrayList<ItemInfo>();		
		try {
			String sql = "select * from customer_item where customer_id=" + id ;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("goods name is "+rs.getString(2));
				ItemInfo itemInfo = new ItemInfo(
							rs.getString("name"),
							rs.getDouble("price"), 
							rs.getInt("quantity")
						);
				result.add(itemInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void addCustomerInventory(String name,double price) {
		//先检查是否已经有这个商品了，有的话就直接加1，没有的话就添加一个
		String  sql = "INSERT INTO customer_item (customer_id,name	,price,quantity) VALUES(?,?,?,?)";
		try {
			conn = DBUtils.getInitInstance().getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2,name);
			pstmt.setDouble(3, price);
			pstmt.setInt(4,1);			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//更新用户拥有的商品
	@Override
	public void updateCustomerInventory(String name,double price) {	
		String  sql = "update customer_item set quantity = quantity+1 where name=? and price=?";
		try {
			conn = DBUtils.getInitInstance().getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setDouble(2, price);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//通过商品名和价格检查顾客拥有的商品
	@Override
	public boolean checkCustomerInventory(String name, double price) {
		boolean result = false;	
		try {
			String sql = "select * from customer_item where name='" + name+"' and price="+price ;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
