package myshop.service;

public interface IMoneyService {
	
	//获取金额
	public double getMoney(String name);
	
	//
	public boolean chargeMoney(String name,double money);

	//更新金额
	boolean updateMoney(String name, double money);
}
