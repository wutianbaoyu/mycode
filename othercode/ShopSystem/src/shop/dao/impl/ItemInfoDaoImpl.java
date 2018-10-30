package shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.ItemInfo;
import shop.dao.ItemInfoDaoInt;
import shop.util.DBUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import domain.Item;

/**
 * 获取实体货物信息，对item_info 表进行 增删改查
 * 
 * @author J
 *
 */
public class ItemInfoDaoImpl implements ItemInfoDaoInt {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private Connection conn = DBUtils.getInitInstance().getConnection();

	/**
	 * 根据商品的多个IDs 获取商品
	 * @param a
	 * @return
	 */
//获取每种商品的详细信息
	public List getItemsInfoByIds(List list) {
		
		List<ItemInfo> result = new ArrayList<ItemInfo>();
		
		try {
			
			StringBuffer in = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				in.append(String.valueOf(list.get(i)));
				in.append(",");
			}

			String str = in.substring(0, in.length() - 1);
			String sql = "select * from item_info where id in(" + str + ")";
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

      /**
       * 
       * @param Itemname
       * @return
       */
	//获取每种商品的数量
	  public int getItemInfoQualityByName(String Itemname) {
		  int result=0;	
		  try {
				conn = DBUtils.getInitInstance().getConnection();
				String sql = "select quantity from item_info  where name = ? ";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, Itemname);
				rs = pstmt.executeQuery();
				while (rs.next()) {				
					result=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  return result;
	  }
	  
	  
	/**
	 * 通过商品名更新每种商品的数量
	 */
	@Override
	public void updateItemInfoQualityByName(String itemName , int quality) {
		  try {
				conn = DBUtils.getInitInstance().getConnection();
				String sql = "UPDATE item_info set  quantity=?  where name = ? ";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				
				pstmt.setInt(1, quality);
				pstmt.setString(2, itemName);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
	}
		
	/**
	 *  增加ItemInfo
	 */
	@Override
	public void addItemInfo(ItemInfo iteminfo) {
          String  sql = "INSERT INTO item_info (name,price,quantity) VALUES(?,?,?)";
		  try {
				conn = DBUtils.getInitInstance().getConnection();
				pstmt = (PreparedStatement) conn.prepareStatement(sql);		
				pstmt.setString(1, iteminfo.getName());
				pstmt.setDouble(2, iteminfo.getPrice());
				pstmt.setInt(3, iteminfo.getQuantity());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  
		   DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
	}
	
	
	/**
	 * 根据名字获取商品信息：
	 */
	@Override
	public Item getItemInfoByName(String Itemname) {
		Item result=new Item();	
		  try {
				conn = DBUtils.getInitInstance().getConnection();
				String sql = "select * from item_info  where name = ? ";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, Itemname);
				rs = pstmt.executeQuery();
				while (rs.next()) {				
					result.setId(rs.getInt(1));
					result.setName(rs.getString(2));
					result.setPrice(rs.getDouble(3));
					result.setQuantity(rs.getInt(4));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  return result;
	}
	
	/**
	 * 更新商品的信息 根据Id
	 */
	@Override
	public void updateItemInfo(Item item) {
		
		  try {
				conn = DBUtils.getInitInstance().getConnection();
				
				String sql = "UPDATE item_info set  name=? , price=? , quantity=?  where id = ? ";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, item.getName());
				pstmt.setDouble(2, item.getPrice());
				pstmt.setInt(3, item.getQuantity());
				pstmt.setInt(4, item.getId());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
		
	}
	
	/**
	 * remove 数据根据Id 
	 */
	@Override
	public void removeItemInfoById(int id) {
		
		   String sql = "DELETE FROM item_info WHERE id=? ";
		  try {
				conn = DBUtils.getInitInstance().getConnection();
				pstmt = (PreparedStatement) conn.prepareStatement(sql);	
				pstmt.setInt(1, id);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  DBUtils.getInitInstance().closeConnection(rs, pstmt, conn);
	}
	
	
	public static void main(String[] args) throws Exception {
		ItemInfoDaoImpl dao = new ItemInfoDaoImpl();
		ItemInfo iteminfo=new ItemInfo();
		iteminfo.setName("鸡蛋");
		iteminfo.setPrice(3);
		iteminfo.setQuantity(34);
		
		 dao.addItemInfo(iteminfo);
		 
	}
    





}
