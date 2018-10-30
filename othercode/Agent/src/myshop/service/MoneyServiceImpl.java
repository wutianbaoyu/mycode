package myshop.service;

import myshop.dao.IMoneyDao;
import myshop.dao.MoneyDaoImpl;

public class MoneyServiceImpl implements IMoneyService {

	private IMoneyDao moneyDao = new MoneyDaoImpl();
	//��ȡ���
	@Override
	public double getMoney(String name) {
		double money = moneyDao.getMoneyByName(name);
		if(money==-1){ //������ʱ������Ϊ0
			moneyDao.insertMoney(name, 0);
			return 0;
		}else{
			return money;
		}
	}
	
	//���½��
	@Override
	public boolean updateMoney(String name, double money){
		double oldMoney = moneyDao.getMoneyByName(name);
		double newMoney = oldMoney+money;
		return moneyDao.updateMoneyByName(name, newMoney);
		//��Ǯ�Ӿ�Ǯ
	}

	//��ֵ
	@Override
	public boolean chargeMoney(String name, double money) {
		double oldMoney = moneyDao.getMoneyByName(name);//���ݹ˿�����ȡ��money��ʾ��ֵ����
		double newMoney = oldMoney+money; 
		return moneyDao.updateMoneyByName(name, newMoney);
	}

}
