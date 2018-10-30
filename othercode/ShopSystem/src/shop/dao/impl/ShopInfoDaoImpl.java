package shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shop.ItemInfo;
import shop.dao.ShopInfoDaoInt;
import shop.util.DBUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import domain.Item;
import domain.Shop;

/**
 * 对Shop_INFO表 进行  增删改查 操作 
 * @author J
 */

public class ShopInfoDaoImpl implements ShopInfoDaoInt {
   
	private PreparedStatement  pstmt=null;
	private ResultSet rs=null;
	private Statement stmt = null;
	private Connection conn = null;
	
	@Override
	public List<Integer> getItemsByShopId(int shopId) throws Exception {
		List<Integer> result = new ArrayList<Integer>();
		try {
			conn = DBUtils.getInitInstance().getConnection();
			String sql = "select itemId from shop_info where shopId = ? ";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, shopId);// 传入参数
			rs = pstmt.executeQuery();
			while (rs.next()) {				
				result.add(rs.getInt("itemId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List getShopItemInfoByShopName(String shopname) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			conn = DBUtils.getInitInstance().getConnection();
			String sql = "select itemId from shop_info where shopName = ? ";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, shopname);// 传入参数
			rs = pstmt.executeQuery();
			while (rs.next()) {				
				result.add(rs.getInt("itemId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 插入一条数据
	 */
	@Override
	public void add_shop_Item(Shop shop,Item item) {
		String  sql = "INSERT INTO shop_info (shopId,itemId,shopName,itemName) VALUES(?,?,?,?)";
		try {
			conn = DBUtils.getInitInstance().getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, shop.getId());
			pstmt.setInt(2, item.getId());
			pstmt.setString(3, shop.getName());
			pstmt.setString(4, item.getName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
	}
	
	
	/**
	 * 根据商店名和商品名，找到 商品Id; 
	 */
	@Override
	public int get_ItemID_From_shopItemInfoByShopNameAndItemName (
			String shopname, String itemname) {
		int result=0;
		String  sql = "select itemId from  shop_info where shopName=? and itemName=?";
		try {
			conn = DBUtils.getInitInstance().getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			
            pstmt.setString(1, shopname);
            pstmt.setString(2, itemname);
			rs = pstmt.executeQuery();
			while (rs.next()) {				
				result=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
		return result;
	}
	
	/**
	 * 更新关联表数据  
	 */
	@Override
	public void update_shop_item(Shop shop, Item item) {

		String sql = "UPDATE shop_info set  shopName =? , itemName=?  where shopId = ? and itemId=? ";
		try {
			conn = DBUtils.getInitInstance().getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, shop.getName());
			pstmt.setString(2, item.getName());
            pstmt.setInt(3, shop.getId());
			pstmt.setInt(4, item.getId());
            
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
	}
	
	@Override
	public void remove_From_shopItemInfoByShopIdAndItemId(int shopId, int itemId) {
		
		String sql = "DELETE FROM shop_info  WHERE shopId=? AND itemId=? ";
		try {
			conn = DBUtils.getInitInstance().getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, shopId);
            pstmt.setInt(2, itemId);
            
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
	}



}
