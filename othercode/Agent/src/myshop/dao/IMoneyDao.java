package myshop.dao;

public interface IMoneyDao {

	//获取金额
	public double getMoneyByName(String name);
	
	//插入金额
	public boolean insertMoney(String name,double money);
		
	//更新金额
	public boolean updateMoneyByName(String name,double money);
}
