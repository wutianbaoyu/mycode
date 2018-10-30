/*
 * 酬薪标准复核，查询，发放登记
 */

package com.modelCl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;

import com.model.PaidProjectBean;
import connect.connectdatabase;

public class PaidProjectRegCl {

	Connection ct = null;
	PreparedStatement psm = null;
	ResultSet rs = null;
	private int pageSize = 10;// 显示的信息数
	private int rowCount = 0;// 该值从数据库查询
	private int pageCount = 0;// 该值是通过pageSize和rowSize得到

	// 查询待复核的酬薪项目
	public Vector<String> selPaidProjectByReg() {

		Vector<String> vec = new Vector<String>();

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select distinct nvPaidProjectPositionName from PaidProject where dPaidProjectTotal != 0.00 and iState = 1");

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

	// 酬薪标准登记,更新PaidProject的状态位
	public boolean updatePaidProjectState(String nvPaidProjectPositionName,
			String nvPaidProjectName) {

		boolean b = false;

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("update PaidProjectName set iState = 4 where nvPaidProjectPositionName = ? and nvPaidProjectName = ?");

			psm.setString(1, nvPaidProjectPositionName);
			psm.setString(2, nvPaidProjectName);

			int result = psm.executeUpdate();

			if (result == 1) {
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return b;
	}

	// 查找该酬薪项目(参数)下的酬薪项目名称.金额！=0.00
	public Vector<String> getPaidProjectNameByPosition(
			String nvPaidProjectPositionName) {

		Vector<String> vec = new Vector<String>();

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select nvPaidProjectName from PaidProject where nvPaidProjectPositionName = ? and dPaidProjectTotal != 0.00 and iState = 1");

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

	// 得到登记人
	public String getRegistrant(String nvPaidProjectPositionName,
			String nvPaidProjectName) {

		String registrant = "";

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select nvPaidProjectRegistrant from PaidProject where nvPaidProjectPositionName = ? and nvPaidProjectName = ?");

			psm.setString(1, nvPaidProjectPositionName);
			psm.setString(2, nvPaidProjectName);

			rs = psm.executeQuery();

			if (rs.next()) {
				registrant = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return registrant;
	}

	// 得到更新后的总金额
	public String getTotal(String nvPaidProjectPositionName,
			String nvPaidProjectName) {

		String total = "";

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select dPaidProjectTotal from PaidProject where nvPaidProjectPositionName = ? and nvPaidProjectName = ?");

			psm.setString(1, nvPaidProjectPositionName);
			psm.setString(2, nvPaidProjectName);

			rs = psm.executeQuery();

			if (rs.next()) {
				total = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return total;
	}

	// 提交复核，更新相关内容(更新复核时间，更新复核意见,更新复核状态位)
	public boolean updatePaidMessge(String dPaidProjectRevTime, int iState,
			String nvReviewopinions, String nvPaidProjectPositionName,
			String nvPaidProjectName) {

		boolean b = false;

		try {

			ct = new connectdatabase().getCon();
			ct.setAutoCommit(false);

			psm = ct.prepareStatement("update PaidProject set dPaidProjectRevTime = ?,iState = ? where nvPaidProjectPositionName = ? and nvPaidProjectName = ?");

			psm.setString(1, dPaidProjectRevTime);
			psm.setInt(2, iState);
			psm.setString(3, nvPaidProjectPositionName);
			psm.setString(4, nvPaidProjectName);

			int result = psm.executeUpdate();

			if (result == 1) {
				psm = ct.prepareStatement("insert into Reviewopinions(nvPaidProjectPositionName,nvPaidProjectName,nvReviewopinions) values(?,?,?)");

				psm.setString(1, nvPaidProjectPositionName);
				psm.setString(2, nvPaidProjectName);
				psm.setString(3, nvReviewopinions);

				result = psm.executeUpdate();

				if (result == 1) {
					b = true;
					ct.commit();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				ct.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} finally {
			this.close();
		}

		return b;

	}

	// 通过编号进行的精确查询
	public Vector<Vector<String>> selPaidProjectMsgByNo(String iPaidProjectNo) {

		Vector<Vector<String>> vec = new Vector<Vector<String>>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from PaidProject where iPaidProjectNo = ?");

			psm.setString(1, iPaidProjectNo);

			rs = psm.executeQuery();

			if (rs.next()) {
				Vector<String> vec_paid = new Vector<String>();

				// PaidProjectBean ppb = new PaidProjectBean();
				vec_paid.add(rs.getInt(1) + "");
				vec_paid.add(rs.getString(2));
				vec_paid.add(rs.getString(3));
				vec_paid.add(rs.getString(4));
				vec_paid.add(rs.getString(5));
				vec_paid.add(rs.getString(8));
				vec_paid.add(rs.getString(6));
				vec_paid.add("查看明细");

				vec.add(vec_paid);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return vec;
	}

	// 得到相应的酬薪的信息详情，实际上是得到table的信息
	public Vector<Vector<Object>> getTableMsg(int iPaidProjectNo) {

		Vector<Vector<Object>> vector = new Vector<Vector<Object>>();

		try {

			ct = new connectdatabase().getCon();

			// 查询对应编号的酬薪项目名称
			psm = ct.prepareStatement("select * from PaidProjectName where iPaidProjectNameNo = ?");

			psm.setInt(1, iPaidProjectNo);

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

	// 通过关键字和时间模糊查询
	public Vector<Vector<String>> selPaidProjectMsgByKeywordAndTime(
			String keyword, String time1, String time2) {

		Vector<Vector<String>> vec = new Vector<Vector<String>>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from PaidProject where (nvPaidProjectName like ? or nvPaidProjectMaker like ? or nvPaidProjectRegistrant like ?) and dPaidProjectTotal != 0.00 and  dPaidProjectRegTime between ? and ?");

			psm.setString(1, keyword);
			psm.setString(2, keyword);
			psm.setString(3, keyword);
			psm.setString(4, time1);
			psm.setString(5, time2);

			rs = psm.executeQuery();

			while (rs.next()) {

				Vector<String> vec_paid = new Vector<String>();

				vec_paid.add(rs.getInt(1) + "");
				vec_paid.add(rs.getString(2));
				vec_paid.add(rs.getString(3));
				vec_paid.add(rs.getString(4));
				vec_paid.add(rs.getString(5));
				vec_paid.add(rs.getString(8));
				vec_paid.add(rs.getString(6));
				vec_paid.add("查看明细");

				vec.add(vec_paid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return vec;
	}

	// 查询的列表结果,分页
	public Vector<Vector<String>> getSelectByPage(String keyword, String time1,
			String time2, int pageNow) {

		Vector<Vector<String>> vector = new Vector<Vector<String>>();

		try {
			// 得到连接
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select top "
					+ pageSize
					+ " * from PaidProject where iPaidProjectNo not in (select top "
					+ pageSize
					* (pageNow - 1)
					+ " iPaidProjectNo from PaidProject where (nvPaidProjectName like ? or nvPaidProjectMaker like ? or nvPaidProjectRegistrant like ?) and dPaidProjectTotal != 0.00 and  dPaidProjectRegTime between ? and ?)"
					+ "and (nvPaidProjectName like ? or nvPaidProjectMaker like ? or nvPaidProjectRegistrant like ?) and dPaidProjectTotal != 0.00 and  dPaidProjectRegTime between ? and ?");

			psm.setString(1, keyword);
			psm.setString(2, keyword);
			psm.setString(3, keyword);
			psm.setString(4, time1);
			psm.setString(5, time2);
			psm.setString(6, keyword);
			psm.setString(7, keyword);
			psm.setString(8, keyword);
			psm.setString(9, time1);
			psm.setString(10, time2);

			// .查询
			rs = psm.executeQuery();

			while (rs.next()) {

				Vector<String> al = new Vector<String>();

				al.add(rs.getInt(1) + "");
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				al.add(rs.getString(8));
				al.add(rs.getString(6));
				al.add("查看明细");
				// 将cb放入arraylist
				vector.add(al);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			this.close();
		}

		return vector;
	}

	// 总结果数
	public int getCountTatol(String keyword, String time1, String time2) {

		try {
			// 得到连接
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select count(iPaidProjectNo) from PaidProject where (nvPaidProjectName like ? or nvPaidProjectMaker like ? or nvPaidProjectRegistrant like ?) and dPaidProjectTotal != 0.00 and  dPaidProjectRegTime between ? and ?");

			psm.setString(1, keyword);
			psm.setString(2, keyword);
			psm.setString(3, keyword);
			psm.setString(4, time1);
			psm.setString(5, time2);

			// .查询
			rs = psm.executeQuery();

			// 请注意，一定要rs.next()
			if (rs.next()) {

				rowCount = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			this.close();
		}

		return rowCount;
	}

	// 返回查询的总页数
	public int getSelectPageCount(String keyword, String time1, String time2) {

		try {
			rowCount = getCountTatol(keyword, time1, time2);

			// 计算pageCount
			if (rowCount % pageSize == 0) {

				pageCount = rowCount / pageSize;
			} else {

				pageCount = rowCount / pageSize + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			this.close();
		}

		return pageCount;
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
