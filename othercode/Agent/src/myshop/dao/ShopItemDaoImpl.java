package myshop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import myshop.ItemInfo;

/**
 * Created by MCY on 2016/9/19.
 */
public class ShopItemDaoImpl implements IShopItemDao {

	private Connection conn = null;
	private PreparedStatement  pstm=null;
	private ResultSet rs=null;
	
	/*
	 * 查找商店的全部商品
	 */
	@Override
	public List<ItemInfo> findItemsByShopName(String shopName){
		List<ItemInfo> result = new ArrayList<ItemInfo>();
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "select * from ShopItem where shopName = ?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, shopName);
			rs = pstm.executeQuery();
			while(rs.next()){
				ItemInfo item = new ItemInfo(rs.getString("ItemName"),rs.getDouble("Price"),rs.getInt("Quantity"));
				result.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbConnection.getInitInstance().closeConnection(rs, pstm, conn);
		}
		return result;
	}
	
	/*
	 * 查找商店的一个商品
	 */
	@Override
	public ItemInfo findItem(String shopName, String itemName) {
		ItemInfo item = new ItemInfo();
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "select * from ShopItem where shopName = ? and ItemName = ?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, shopName);
			pstm.setString(2, itemName);
			rs = pstm.executeQuery();
			while(rs.next()){
				item.setName(rs.getString("ItemName"));
				item.setPrice(rs.getDouble("Price"));
				item.setQuantity(rs.getInt("Quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbConnection.getInitInstance().closeConnection(rs, pstm, conn);
		}
		return item;
	}
	
	/*
	 * 添加商品
	 */
	@Override
	public boolean addItem(String shopName, ItemInfo item) {
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "insert into ShopItem(ShopName,ItemName,Price,Quantity) values(?,?,?,?)";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, shopName);
			pstm.setString(2, item.getName());
			pstm.setDouble(3, item.getPrice());
			pstm.setInt(4, item.getQuantity());
			pstm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			DbConnection.getInitInstance().closeConnection(rs, pstm, conn);
		}
	}
	
	/*
	 * 删除商品
	 */
	@Override
	public boolean deleteItem(String shopName, String itemName) {
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "delete from ShopItem where ShopName=? and ItemName=?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, shopName);
			pstm.setString(2, itemName);
			pstm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			DbConnection.getInitInstance().closeConnection(rs, pstm, conn);
		}
	}
	
	/*
	 * 更改商品
	 */
	@Override
	public boolean updataItem(String shopName, ItemInfo oldItem,
			ItemInfo newItem) {
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "update ShopItem set ItemName=?,Price=?,Quantity=? where ShopName=? and ItemName=?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, newItem.getName());
			pstm.setDouble(2, newItem.getPrice());
			pstm.setInt(3, newItem.getQuantity());
			pstm.setString(4, shopName);
			pstm.setString(5, oldItem.getName());
			if(pstm.executeUpdate()!=0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			DbConnection.getInitInstance().closeConnection(rs, pstm, conn);
		}
	}
	
}
