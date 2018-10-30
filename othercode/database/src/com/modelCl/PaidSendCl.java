package com.modelCl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import connect.connectdatabase;;

public class PaidSendCl {

	Connection ct = null;
	PreparedStatement psm = null;
	ResultSet rs = null;

	// �õ������ŵĳ�н
	public Vector<Vector<Object>> getPaidSend() {

		Vector<Vector<Object>> vector = new Vector<Vector<Object>>();

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from PaidProject where iState = 4");

			rs = psm.executeQuery();

			while (rs.next()) {
				Vector<Object> vec = new Vector<Object>();

				vec.add(rs.getInt(1));
				vec.add(rs.getString(2));
				vec.add(rs.getBigDecimal(3));
				vec.add(rs.getString(5));

				vector.add(vec);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return vector;

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
