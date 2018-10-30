package checkuser;
import java.sql.*;



import connect.connectdatabase;;
public class check {
	private PreparedStatement psm = null;
	private Connection ct = null;
	private ResultSet rs = null;

	// 验证用户是否存在
	public boolean checkUser(String u, String p) {

		boolean b = false;

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select password from user2 where username= ?");

			psm.setString(1, u);

			rs = psm.executeQuery();
			// 根据结果判断
			if (rs.next()) {

				// 说明用户名存在
				if (rs.getString(1).equals(p)) {

					// 一定合法
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
			// TODO: handle exception
			e2.printStackTrace();
		}

	}
}
