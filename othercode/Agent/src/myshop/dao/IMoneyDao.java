package myshop.dao;

public interface IMoneyDao {

	//��ȡ���
	public double getMoneyByName(String name);
	
	//������
	public boolean insertMoney(String name,double money);
		
	//���½��
	public boolean updateMoneyByName(String name,double money);
}
