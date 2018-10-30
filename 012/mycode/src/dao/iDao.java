package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBUtil;
import model.Goddess;
import model.i;
import dao.GoddessDao;
public class iDao {
	public static void yc(String stringq,String shouhang)throws Exception{
		Connection conn=DBUtil.getConnection();
		String sql5="select jieguo,shenglv from"+"a"+"?"+"where shouhang="+"'"+"?"+"'";
		PreparedStatement ptmt=conn.prepareStatement(sql5);
		ptmt.setString(1, stringq);
		ptmt.setString(2, shouhang);
		ResultSet rs=ptmt.executeQuery(sql5);
		System.out.println(rs.getString("jieguo"));
		System.out.println(rs.getInt("shenglv"));
	}
}
