package shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.ItemInfo;
import shop.ShopCapa;
import shop.dao.ShopDaoInt;
import shop.util.DBUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import domain.Shop;

/**
 * 对Shop表 进行  增删改查 操作 
 * @author J
 *
 */

public class ShopDaoImpl implements ShopDaoInt{
	
	private PreparedStatement  pstmt=null;
	private ResultSet rs=null;
	private Statement stmt = null;
	private Connection conn = DBUtils.getInitInstance().getConnection();
	
	
	/**
	 * 根据商店的ID  更新商店的钱数
	 */
	@Override
	public void updateShopMomey(double money,int shopId) {
		
		try {
			String sql = "UPDATE shop t SET t.shop_money=? WHERE t.id=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setDouble(1, money);// 传入参数
			pstmt.setInt(2, shopId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据商店的名字  更新商店的钱数
	 * 
	 */
	@Override
	public void updateShopMomey(double money, String shopname) {
		try {
			System.out.println("shop.dao.ShopDao "+"dao层更新数据");
			String sql = "UPDATE shop t SET t.shop_money=? WHERE t.shop_name=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setDouble(1, money);// 传入参数
			pstmt.setString(2, shopname);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
   /**
    * 根据商店的ID 获取商店的钱数
    */
	@Override
	public double getShopMoneyByShopId(int shopId) {
		
		double result=0;
		
		try {
			
		    String sql = "SELECT shop_money FROM shop WHERE id=? ";
			pstmt =  (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, shopId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("ShopDao 输出钱数：" + rs.getDouble("shop_money"));
				result = rs.getDouble("shop_money");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 根据商店名获取 商店当前的资金 
	 * 
	 */
	@Override
	public double getShopMoneyByShopName(String shopname) {
	  
		double result=0;
		try {
			
		    String sql = "SELECT shop_money FROM shop WHERE shop_name=? ";
			pstmt =  (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, shopname);
			rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("ShopDao 输出钱数：" + rs.getDouble("shop_money"));
				result = rs.getDouble("shop_money");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 根据商品名更新商品数量
	 * 
	 */
	@Override
	public void updateItemQuantityByItemName(List<ItemInfo> Catalog){
		try {
			
			for (ItemInfo itemInfo : Catalog) {
				String sql = "UPDATE item_info info SET info.quantity =? "
						+ "WHERE info.NAME = ?";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);

				pstmt.setInt(1, itemInfo.getQuantity());// 传入参数
				pstmt.setString(2, itemInfo.getName());
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 获取商店全部信息，根据名字
	 */
	@Override
	public Shop getShopInfoByName(String shopname) {
		Shop result=new Shop();
		try {
		    String sql = "SELECT * FROM shop WHERE shop_name=? ";
			pstmt =  (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, shopname);
			rs=pstmt.executeQuery();
			while(rs.next()){
				
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
                result.setMoney(rs.getDouble(3));
                
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args ) {
		ShopDaoImpl shopDao = new ShopDaoImpl();
		List<ItemInfo> Catalog =new ArrayList();
		ItemInfo item1=new ItemInfo();
		item1.setName("帽子");
		item1.setPrice(10);
		item1.setQuantity(10); 
		Catalog.add(item1);
		shopDao.updateItemQuantityByItemName(Catalog);
		System.out.println("OK");
	}


}
