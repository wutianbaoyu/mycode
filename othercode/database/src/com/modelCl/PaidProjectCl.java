package com.modelCl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;

import connect.*;

public class PaidProjectCl {

	Connection ct = null;
	PreparedStatement psm = null;
	ResultSet rs = null;

	// 添加薪酬项目
	public boolean addPaidProject(String nvPaidProjectPositionName,
			String nvPaidProjectName, String nvPaidProjectMaker, int iState,
			JTable table) {

		boolean b = true;

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("insert into PaidProject(nvPaidProjectPositionName,nvPaidProjectName,nvPaidProjectMaker,iState) values(?,?,?,?)");

			psm.setString(1, nvPaidProjectPositionName);
			psm.setString(2, nvPaidProjectName);
			psm.setString(3, nvPaidProjectMaker);
			psm.setInt(4, iState);

			int result = psm.executeUpdate();

			if (result == 1) {

				// 得到刚添加进去的iPaidProjectNo
				psm = ct.prepareStatement("select max(iPaidProjectNo) from PaidProject");

				rs = psm.executeQuery();

				int iPaidProjectNo = 0;

				if (rs.next()) {
					iPaidProjectNo = rs.getInt(1);
				}
				// 添加薪酬项目名称
				// 这里使用批处理
				Statement st = ct.createStatement();

				for (int i = 0; i < table.getRowCount(); i++) {

					st.addBatch("insert into PaidProjectName(iPaidProjectNameNo,nvPaidProjectNames) values("
							+ iPaidProjectNo
							+ ",'"
							+ table.getValueAt(i, 1)
							+ "')");

				}

				// 批量执行
				st.executeBatch();

			} else {
				b = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return b;
	}

	// 查询已添加酬薪的的酬薪项目（在PaidProject.java中用到，(*)）
	public Vector<String> selPaidProject(String nvPaidProjectPositionName) {

		Vector<String> vector = new Vector<String>();

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select nvPaidProjectName from PaidProject where nvPaidProjectPositionName = ?");

			psm.setString(1, nvPaidProjectPositionName);

			rs = psm.executeQuery();

			while (rs.next()) {

				vector.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return vector;
	}

	// 查询每个职位已有的酬薪项目
	public Vector<String> getPaidProjectByPosition(
			String nvPaidProjectPositionName) {

		Vector<String> vec = new Vector<String>();

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select nvPaidProjectName from PaidProject where nvPaidProjectPositionName = ? and dPaidProjectTotal = 0.00");

			psm.setString(1, nvPaidProjectPositionName);

			rs = psm.executeQuery();

			while (rs.next()) {

				vec.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return vec;

	}

	// 查询酬薪号
	public int getPaidNo(String nvPaidProjectPositionName,
			String nvPaidProjectName) {

		int PaidNo = 1;

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select iPaidProjectNo from PaidProject where nvPaidProjectPositionName = ? and nvPaidProjectName = ?");

			psm.setString(1, nvPaidProjectPositionName);
			psm.setString(2, nvPaidProjectName);

			rs = psm.executeQuery();

			if (rs.next()) {
				PaidNo = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return PaidNo;

	}

	// 查询有酬薪项目的职位
	public Vector<String> getPositionByPaid() {

		Vector<String> vec = new Vector<String>();
		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select distinct nvPaidProjectPositionName from PaidProject where dPaidProjectTotal = 0.00");

			rs = psm.executeQuery();

			while (rs.next()) {
				vec.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return vec;
	}

	// 查询制定人
	public String getMaker(String nvPaidProjectPositionName,
			String nvPaidProjectName) {

		String maker = "";

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select nvPaidProjectMaker from PaidProject where nvPaidProjectPositionName = ? and nvPaidProjectName = ?");

			psm.setString(1, nvPaidProjectPositionName);
			psm.setString(2, nvPaidProjectName);

			rs = psm.executeQuery();

			if (rs.next()) {
				maker = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return maker;
	}

	// 得到酬薪的表格信息
	public Vector<Vector<Object>> getPaidProjectName(
			String nvPaidProjectPositionName, String nvPaidProjectName) {

		Vector<Vector<Object>> vector = new Vector<Vector<Object>>();

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from PaidProjectName where iPaidProjectNameNo = (select iPaidProjectNo from PaidProject where nvPaidProjectPositionName = ? and nvPaidProjectName = ?)");

			psm.setString(1, nvPaidProjectPositionName);
			psm.setString(2, nvPaidProjectName);

			rs = psm.executeQuery();

			while (rs.next()) {
				Vector<Object> vec = new Vector<Object>();

				vec.add(vector.size() + 1);
				vec.add(rs.getString(2));
				vec.add(rs.getBigDecimal(3));

				vector.add(vec);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return vector;
	}

	// 酬薪标准登记,更新PaidProject的总金额,登记人,登记时间
	public boolean updatePaidProjectTotal(String Total,
			String nvPaidProjectPositionName, String nvPaidProjectName,
			JTable table, String nvPaidProjectRegistrant,
			String dPaidProjectRegTime) {

		boolean b = true;

		try {

			ct = new connectdatabase().getCon();
			ct.setAutoCommit(false);

			psm = ct.prepareStatement("update PaidProject set dPaidProjectTotal = ?,nvPaidProjectRegistrant = ?,dPaidProjectRegTime = ? where nvPaidProjectPositionName = ? and nvPaidProjectName = ?");

			psm.setString(1, Total);
			psm.setString(2, nvPaidProjectRegistrant);
			psm.setString(3, dPaidProjectRegTime);
			psm.setString(4, nvPaidProjectPositionName);
			psm.setString(5, nvPaidProjectName);

			int result = psm.executeUpdate();

			if (result == 1) {

				// 更新对应的PaidProjectName表
				// 这里使用批处理
				Statement st = ct.createStatement();

				for (int i = 0; i < table.getRowCount(); i++) {

					st.addBatch("update PaidProjectName set dPaidProjectNameSalary = "
							+ table.getValueAt(i, 2)
							+ " where nvPaidProjectNames = '"
							+ table.getValueAt(i, 1)
							+ "' and iPaidProjectNameNo = (select iPaidProjectNo from PaidProject where nvPaidProjectPositionName = '"
							+ nvPaidProjectPositionName
							+ "' and nvPaidProjectName = '"
							+ nvPaidProjectName
							+ "')");

				}

				// 批量执行
				st.executeBatch();

				ct.commit();
			} else {
				b = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} finally {
			this.close();
		}

		return b;
	}

	// 关闭资源
	public void close() {

		// 关闭打开的各种资源
		try {

			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (psm != null) {
				psm.close();
				psm = null;
			}
			if (ct != null) {
				ct.close();
				ct = null;
			}
		} catch (Exception e2) {

			e2.printStackTrace();
		}

	}
}
