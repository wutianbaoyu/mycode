package myshop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MoneyDaoImpl implements IMoneyDao {

	private Connection conn = null;
	private PreparedStatement  pstm=null;
	private ResultSet rs=null;
	
	//获取金钱
	@Override
	public double getMoneyByName(String name) {
		double money = -1;		//设为-1判断是否存在这个name对应的钱
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "select money from Money where name = ? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				money =rs.getDouble("money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbConnection.getInitInstance().closeConnection(rs, pstm, conn);
		}
		return money;
	}

	//建立新的钱包记录
	@Override
	public boolean insertMoney(String name,double money){
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "insert into Money(name,money) values(?,?)";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setDouble(2, money);
			pstm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			DbConnection.getInitInstance().closeConnection(rs, pstm, conn);
		}
	}
	
	//更新金钱
	@Override
	public boolean updateMoneyByName(String name, double money) {
		conn = DbConnection.getInitInstance().getConnection();
		String sql = "update Money set money=? where name=? ";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setDouble(1, money);
			pstm.setString(2, name);
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
