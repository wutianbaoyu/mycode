package com.modelCl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.model.CasesBean;
import connect.connectdatabase;

public class CasesCl {

	Connection ct = null;
	PreparedStatement psm = null;
	ResultSet rs = null;
	private int pageSize = 10;// 显示的信息数
	private int rowCount = 0;// 该值从数据库查询
	private int pageCount = 0;// 该值是通过pageSize和rowSize得到

	public boolean addCases(String nvOneOrganizationName,
			String nvTwoOrganizationName, String nvThreeOrganizationName,
			String nvPositionClassificationName, String nvPositionName,
			String nvJobName, String nvName, String ncSex, String vEmail,
			String iPhone, String iQQ, String vIdCart, String nvAddress,
			String iPost, String nvNationality, String nvBirthPlace,
			String cBirthday, String nvNation, String nvReligious,
			String nvPolitics, String cMobilePhone, String vSocialNo,
			String vAge, String nvDegree, int iEductionAge,
			String nvdegreeProfessinal, String nvPaidStandardNo,
			String nvBanks, String vAccount, String nvRegistration,
			String vFilebuildingTime, String nvSpecialty, String nvHobby,
			String nvResume, String nvFamilyrelations, String nvNote,
			String vHead, int iState, String iNo) {

		boolean b = false;

		try {
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select max(iCase) from Cases");

			rs = psm.executeQuery();

			int maxNo = 0;

			if (rs.next()) {

				maxNo = rs.getInt(1) + 1;
			}

			psm = ct.prepareStatement("insert into Cases(nvOneOrganizationName,nvTwoOrganizationName,nvThreeOrganizationName,nvPositionClassificationName,"
					+ "nvPositionName,nvJobName,nvName,ncSex,vEmail,iPhone,iQQ,vIdCart,nvAddress,iPost,nvNationality,nvBirthPlace,"
					+ "cBirthday,nvNation,nvReligious,nvPolitics,cMobilePhone,vSocialNo,vAge,nvDegree,iEductionAge,nvdegreeProfessinal,"
					+ "nvPaidStandardNo,nvBanks,vAccount,nvRegistration,vFilebuildingTime,nvSpecialty,nvHobby,"
					+ "nvResume,nvFamilyrelations,nvNote,vHead,iState,iNo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			psm.setString(1, nvOneOrganizationName);
			psm.setString(2, nvTwoOrganizationName);
			psm.setString(3, nvThreeOrganizationName);
			psm.setString(4, nvPositionClassificationName);
			psm.setString(5, nvPositionName);
			psm.setString(6, nvJobName);
			psm.setString(7, nvName);
			psm.setString(8, ncSex);
			psm.setString(9, vEmail);
			psm.setString(10, iPhone);
			psm.setString(11, iQQ);
			psm.setString(12, vIdCart);
			psm.setString(13, nvAddress);
			psm.setString(14, iPost);
			psm.setString(15, nvNationality);
			psm.setString(16, nvBirthPlace);
			psm.setString(17, cBirthday);
			psm.setString(18, nvNation);
			psm.setString(19, nvReligious);
			psm.setString(20, nvPolitics);
			psm.setString(21, cMobilePhone);
			psm.setString(22, vSocialNo);
			psm.setString(23, vAge);
			psm.setString(24, nvDegree);
			psm.setInt(25, iEductionAge);
			psm.setString(26, nvdegreeProfessinal);
			psm.setString(27, nvPaidStandardNo);
			psm.setString(28, nvBanks);
			psm.setString(29, vAccount);
			psm.setString(30, nvRegistration);
			psm.setString(31, vFilebuildingTime);
			psm.setString(32, nvSpecialty);
			psm.setString(33, nvHobby);
			psm.setString(34, nvResume);
			psm.setString(35, nvFamilyrelations);
			psm.setString(36, nvNote);
			psm.setString(37, vHead);
			psm.setInt(38, iState);
			psm.setString(39, iNo + maxNo);

			int result = psm.executeUpdate();

			if (result == 1) {
				b = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}

		return b;
	}

	// 返回分页的总页数(待复核的总页数)
	public int getPageCount() {

		try {
			// 得到连接
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select count(*) from Cases where iState = 1");
			// .查询
			rs = psm.executeQuery();

			// 请注意，一定要rs.next()
			if (rs.next()) {

				rowCount = rs.getInt(1);
			}

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

	// 得到用户需要显示的用户信息（分页）
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector<Vector<String>> getRegCasesByPage(int pageNow) {

		@SuppressWarnings("rawtypes")
		Vector<Vector<String>> CasesVector = new Vector<Vector<String>>();

		try {

			ct = new connectdatabase().getCon();
			// 3.创建Statement
			psm = ct.prepareStatement("select top " + pageSize
					+ " * from Cases where iCase not in (select top "
					+ pageSize * (pageNow - 1)
					+ " iCase from Cases where iState = 1) and iState = 1");

			// 查询出需要显示的记录

			rs = psm.executeQuery();

			// 开始将rs封装到Arraylist
			while (rs.next()) {

				Vector<String> al = new Vector<String>();

				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				al.add(rs.getString(7));
				al.add(rs.getString(9));
				al.add(rs.getString(10));
				al.add("复核");
				// 将cb放入arraylist
				CasesVector.add(al);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

			this.close();
		}

		return CasesVector;
	}

	// 返回总复核数
	public int getRegCount() {

		try {
			// 得到连接
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select count(*) from Cases where iState = 1");
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

	// 复核
	public CasesBean getRegMessage(String iNo) {

		CasesBean cb = new CasesBean();

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select * from Cases where iState = 1 and iNo =?");

			psm.setString(1, iNo);

			rs = psm.executeQuery();

			if (rs.next()) {

				cb.setiNo(rs.getString(2));
				cb.setNvOneOrganizationName(rs.getString(3));
				cb.setNvTwoOrganizationName(rs.getString(4));
				cb.setNvThreeOrganizationName(rs.getString(5));
				cb.setNvPositionClassificationName(rs.getString(6));
				cb.setNvPositionName(rs.getString(7));
				cb.setNvJobName(rs.getString(8));
				cb.setNvName(rs.getString(9));
				cb.setNcSex(rs.getString(10));
				cb.setvEmail(rs.getString(11));
				cb.setiPhone(rs.getString(12));
				cb.setiQQ(rs.getString(13));
				cb.setvIdCart(rs.getString(14));
				cb.setNvAddress(rs.getString(15));
				cb.setiPost(rs.getString(16));
				cb.setNvNationality(rs.getString(17));
				cb.setNvBirthPlace(rs.getString(18));
				cb.setcBirthday(rs.getString(19));
				cb.setNvNation(rs.getString(20));
				cb.setNvReligious(rs.getString(21));
				cb.setNvPolitics(rs.getString(22));
				cb.setcMobilePhone(rs.getString(23));
				cb.setvSocialNo(rs.getString(24));
				cb.setvAge(rs.getString(25));
				cb.setNvDegree(rs.getString(26));
				cb.setiEductionAge(rs.getInt(27));
				cb.setNvdegreeProfessinal(rs.getString(28));
				cb.setNvPaidStandardNo(rs.getString(29));
				cb.setNvBanks(rs.getString(30));
				cb.setvAccount(rs.getString(31));
				cb.setNvRegistration(rs.getString(32));
				cb.setNvChecker(rs.getString(33));
				cb.setNvSpecialty(rs.getString(36));
				cb.setNvHobby(rs.getString(37));
				cb.setNvResume(rs.getString(39));
				cb.setNvFamilyrelations(rs.getString(40));
				cb.setNvNote(rs.getString(41));
				cb.setvHead(rs.getString(42));

			}

			// .查询
			rs = psm.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.close();
		}
		return cb;

	}

	// 提交复核,实际上是更新数据
	public boolean updateReg(String nvJobName, String nvName, String ncSex,
			String vEmail, String iPhone, String iQQ, String iPost,
			String nvAddress, String vIdCart, String nvNationality,
			String nvBirthPlace, String cBirthday, String nvNation,
			String nvReligious, String nvPolitics, String cMobilePhone,
			String vSocialNo, String vAge, String nvDegree, int iEductionAge,
			String nvdegreeProfessinal, String nvPaidStandardNo,
			String nvBanks, String vAccount, String vFilechangeTime,
			String nvSpecialty, String nvHobby, String nvResume,
			String nvFamilyrelations, String nvNote, String vHead, int iState,
			String iNo, String nvChecker) {

		boolean b = false;

		try {

			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("update Cases set nvJobName = ?,nvName = ?,"
					+ "ncSex = ?,vEmail = ?,iPhone = ?,iQQ = ?,iPost = ?,nvAddress = ?,vIdCart = ?,"
					+ "nvNationality = ?,nvBirthPlace = ?,cBirthday = ?,nvNation = ?,nvReligious = ?,"
					+ "nvPolitics = ?,cMobilePhone = ?,vSocialNo = ?,vAge = ?,nvDegree = ?,iEductionAge = ?,"
					+ "nvdegreeProfessinal = ?,nvPaidStandardNo = ?,nvBanks = ?,vAccount = ?,vFilechangeTime = ?,nvSpecialty = ?,"
					+ "nvHobby = ?,nvResume = ?,nvFamilyrelations = ?,vHead = ?,nvNote = ?,iState = ? ,nvChecker =  ? where iNo = ?");

			psm.setString(1, nvJobName);
			psm.setString(2, nvName);
			psm.setString(3, ncSex);
			psm.setString(4, vEmail);
			psm.setString(5, iPhone);
			psm.setString(6, iQQ);
			psm.setString(7, iPost);
			psm.setString(8, nvAddress);
			psm.setString(9, vIdCart);
			psm.setString(10, nvNationality);
			psm.setString(11, nvBirthPlace);
			psm.setString(12, cBirthday);
			psm.setString(13, nvNation);
			psm.setString(14, nvReligious);
			psm.setString(15, nvPolitics);
			psm.setString(16, cMobilePhone);
			psm.setString(17, vSocialNo);
			psm.setString(18, vAge);
			psm.setString(19, nvDegree);
			psm.setInt(20, iEductionAge);
			psm.setString(21, nvdegreeProfessinal);
			psm.setString(22, nvPaidStandardNo);
			psm.setString(23, nvBanks);
			psm.setString(24, vAccount);
			psm.setString(25, vFilechangeTime);
			psm.setString(26, nvSpecialty);
			psm.setString(27, nvHobby);
			psm.setString(28, nvResume);
			psm.setString(29, nvFamilyrelations);
			psm.setString(30, vHead);
			psm.setString(31, nvNote);
			psm.setInt(32, iState);
			psm.setString(33, nvChecker);
			psm.setString(34, iNo);

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

	// 返回查询的结果
	public Vector<Vector<String>> getSelect(String nvOneOrganizationName,
			String nvTwoOrganizationName, String nvThreeOrganizationName,
			String nvPositionClassificationName, String nvPositionName,
			String time1, String time2, int pageNow) {

		Vector<Vector<String>> vector = new Vector<Vector<String>>();

		try {
			// 得到连接
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select top "
					+ pageSize
					+ " * from Cases where iCase not in (select top "
					+ pageSize
					* (pageNow - 1)
					+ " iCase from Cases) and nvOneOrganizationName = ? and nvTwoOrganizationName = ? and nvThreeOrganizationName = ? and nvPositionClassificationName = ? and nvPositionName = ? and vFilebuildingTime between ? and ?");

			psm.setString(1, nvOneOrganizationName);
			psm.setString(2, nvTwoOrganizationName);
			psm.setString(3, nvThreeOrganizationName);
			psm.setString(4, nvPositionClassificationName);
			psm.setString(5, nvPositionName);
			psm.setString(6, time1);
			psm.setString(7, time2);

			// .查询
			rs = psm.executeQuery();

			while (rs.next()) {

				Vector<String> al = new Vector<String>();

				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				al.add(rs.getString(7));
				al.add(rs.getString(9));
				al.add(rs.getString(10));
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

	// 返回查询的总结果数
	public int getSelectCount(String nvOneOrganizationName,
			String nvTwoOrganizationName, String nvThreeOrganizationName,
			String nvPositionClassificationName, String nvPositionName,
			String time1, String time2) {

		try {
			// 得到连接
			ct = new connectdatabase().getCon();

			psm = ct.prepareStatement("select count(*) from Cases where nvOneOrganizationName = ? and nvTwoOrganizationName = ? and nvThreeOrganizationName = ? and nvPositionClassificationName = ? and nvPositionName = ? and vFilebuildingTime between ? and ?");

			psm.setString(1, nvOneOrganizationName);
			psm.setString(2, nvTwoOrganizationName);
			psm.setString(3, nvThreeOrganizationName);
			psm.setString(4, nvPositionClassificationName);
			psm.setString(5, nvPositionName);
			psm.setString(6, time1);
			psm.setString(7, time2);
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
	public int getSelectPageCount(String nvOneOrganizationName,
			String nvTwoOrganizationName, String nvThreeOrganizationName,
			String nvPositionClassificationName, String nvPositionName,
			String time1, String time2) {

		try {
			rowCount = getSelectCount(nvOneOrganizationName,
					nvTwoOrganizationName, nvThreeOrganizationName,
					nvPositionClassificationName, nvPositionName, time1, time2);

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
