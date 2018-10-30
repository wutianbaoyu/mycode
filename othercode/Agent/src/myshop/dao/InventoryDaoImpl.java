package myshop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import myshop.ItemInfo;
//数据库操作
public class InventoryDaoImpl implements IInventoryDao {


	private Connection conn = null;
	private PreparedStatement  pstm=null;
	private ResultSet rs=null;
	
	//获取商品名字，得到它的库存
	@Override
	public List<ItemInfo> getInventoryByName(String name) {
		List<ItemInfo> result = new ArrayList<ItemInfo>();
		conn = DbConnection.getInitInstance().getConnection();//获取数据库连接
		String sql = "select * from Inventory where name = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
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

	//增加相应商品的库存
	@Override
	public boolean addInventory(String name, ItemInfo item) {
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "insert into Inventory(ItemName,Name,Price,Quantity) values(?,?,?,?)";//插入数据项，（序号，商品名，价格，数量）
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, item.getName());
			pstm.setString(2, name);
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

	//更新商品库存
	@Override
	public boolean updateInventory(String name, ItemInfo item) {
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "update Inventory set Quantity=? where Name=? and ItemName=? and Price=?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, item.getQuantity());
			pstm.setString(2, name);
			pstm.setString(3, item.getName());
			pstm.setDouble(4, item.getPrice());
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
