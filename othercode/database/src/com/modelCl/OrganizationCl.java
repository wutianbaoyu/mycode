/*
 * 对机构表的处理，即点击一级机构表后，二级机构表出现联动，点击二级就三级联动
 */
package com.modelCl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.model.TwoOrganizationBean;
import connect.*;

public class OrganizationCl {

	Connection ct = null;
	PreparedStatement psm = null;
	ResultSet rs = null;

	// 获得一级机构
	public Vector<String> getOneOrganization() {

		Vector<String> oneVector = new Vector<String>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from OneOrganization");

			rs = psm.executeQuery();

			while (rs.next()) {
				oneVector.add(rs.getString(2));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return oneVector;
	}

	// 获得二级机构
	/**
	 * @param OrganizationNo
	 *            :选中的一级机构编号
	 */
	public Vector<String> getTwoOrganization(int OrganizationNo) {

		Vector<String> twoVector = new Vector<String>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from TwoOrganization where iTwoOrganizationNo=?");

			psm.setInt(1, OrganizationNo);

			rs = psm.executeQuery();

			while (rs.next()) {

				twoVector.add(rs.getString(2));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return twoVector;
	}

	// 获得三级机构
	/**
	 * @param OrganizationNo
	 *            :选中的二级机构的编号
	 * @param OrganizationName
	 *            :选中的二级机构的名称
	 */
	public Vector<String> getThreeOrganization(int OrganizationNo,
			String OrganizationName) {

		Vector<String> threeVector = new Vector<String>();

		try {
			ct = new connectdatabase().getCon();

			// psm =
			// ct.prepareStatement("select * from ThreeOrganization where iThreeOrganizationNo=? and nvTwoOrganizationName='"+OrganizationName+"'");
			psm = ct.prepareStatement("select * from ThreeOrganization where iThreeOrganizationNo=? and nvTwoOrganizationName=?");

			psm.setInt(1, OrganizationNo);
			psm.setString(2, OrganizationName);

			rs = psm.executeQuery();

			while (rs.next()) {

				threeVector.add(rs.getString(3));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return threeVector;
	}

	// 获得职位分类
	public Vector<String> getPositionClassification() {

		Vector<String> PositionClassificationVector = new Vector<String>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from PositionClassification");

			rs = psm.executeQuery();

			while (rs.next()) {

				PositionClassificationVector.add(rs.getString(2));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return PositionClassificationVector;
	}

	// 获得职位名称
	public Vector<String> getPosition(int iPositionNo) {

		Vector<String> PositionVector = new Vector<String>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from Position where iPositionNo=?");

			psm.setInt(1, iPositionNo);

			rs = psm.executeQuery();

			while (rs.next()) {

				PositionVector.add(rs.getString(2));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return PositionVector;
	}

	// 获得职称
	public Vector<String> getJob() {

		Vector<String> JobVector = new Vector<String>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from Job");

			rs = psm.executeQuery();

			while (rs.next()) {

				JobVector.add(rs.getString(2));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return JobVector;
	}

	// 获得学历专业表
	public Vector<String> getDegreeProfessinal() {

		Vector<String> DegreeProfessinalVector = new Vector<String>();

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from DegreeProfessinal");

			rs = psm.executeQuery();

			while (rs.next()) {

				DegreeProfessinalVector.add(rs.getString(2));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return DegreeProfessinalVector;
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
