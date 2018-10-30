package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DBUtil;
import model.yc;
public class ycDao {
		public void addYc(yc y)throws Exception{
			Connection conn=DBUtil.getConnection();
			String sql="" +
					"insert into yc" +
					"(baiyc,shiyc,geyc)" +
					"values(" +
					"?,?,?)";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ptmt.setInt(1, y.getBaiyc());
			ptmt.setInt(2, y.getShiyc());
			ptmt.setInt(3, y.getGeyc());
			ptmt.execute();
		}
		
		public void updateYc()throws Exception{
			
		}
}
