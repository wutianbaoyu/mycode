/*
 * �Ի�����Ĵ��������һ��������󣬶�����������������������������������
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

	// ���һ������
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

	// ��ö�������
	/**
	 * @param OrganizationNo
	 *            :ѡ�е�һ���������
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

	// �����������
	/**
	 * @param OrganizationNo
	 *            :ѡ�еĶ��������ı��
	 * @param OrganizationName
	 *            :ѡ�еĶ�������������
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

	// ���ְλ����
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

	// ���ְλ����
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

	// ���ְ��
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

	// ���ѧ��רҵ��
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

	// �ر���Դ
	public void close() {

		// �رմ򿪵ĸ�����Դ
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
