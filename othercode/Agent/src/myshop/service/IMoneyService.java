package myshop.service;

public interface IMoneyService {
	
	//��ȡ���
	public double getMoney(String name);
	
	//
	public boolean chargeMoney(String name,double money);

	//���½��
	boolean updateMoney(String name, double money);
}
