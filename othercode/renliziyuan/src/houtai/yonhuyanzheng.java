package houtai;

import java.sql.*;



public class yonhuyanzheng {

	private PreparedStatement psm = null;
	private Connection ct = null;
	private ResultSet rs = null;

	// ��֤�û��Ƿ����
	public boolean checkUser(String u, String p) {

		boolean b = false;

		try {

			ct = new lianjieshujuku().getCon();

			psm = ct.prepareStatement("select nPassword from Users where nUsersName= ?");

			psm.setString(1, u);

			rs = psm.executeQuery();
			// ���ݽ���ж�
			if (rs.next()) {

				// ˵���û�������
				if (rs.getString(1).equals(p)) {

					// һ���Ϸ�
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

			this.close();
		}

		return b;
	}

	// �õ��û���Ȩ��
	public int getGrade(String u) {

		int grade = 1;

		try {

			ct = new lianjieshujuku().getCon();

			psm = ct.prepareStatement("select iUsersGrade from Users where nUsersName= ?");

			psm.setString(1, u);

			rs = psm.executeQuery();
			// ���ݽ���ж�
			if (rs.next()) {

				grade = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

			this.close();
		}

		return grade;

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
			// TODO: handle exception
			e2.printStackTrace();
		}

	}
}
