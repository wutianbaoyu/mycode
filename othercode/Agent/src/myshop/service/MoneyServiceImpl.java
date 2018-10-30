package myshop.service;

import myshop.dao.IMoneyDao;
import myshop.dao.MoneyDaoImpl;

public class MoneyServiceImpl implements IMoneyService {

	private IMoneyDao moneyDao = new MoneyDaoImpl();
	//获取金额
	@Override
	public double getMoney(String name) {
		double money = moneyDao.getMoneyByName(name);
		if(money==-1){ //当金额不足时，金额变为0
			moneyDao.insertMoney(name, 0);
			return 0;
		}else{
			return money;
		}
	}
	
	//更新金额
	@Override
	public boolean updateMoney(String name, double money){
		double oldMoney = moneyDao.getMoneyByName(name);
		double newMoney = oldMoney+money;
		return moneyDao.updateMoneyByName(name, newMoney);
		//新钱加旧钱
	}

	//充值
	@Override
	public boolean chargeMoney(String name, double money) {
		double oldMoney = moneyDao.getMoneyByName(name);//根据顾客名获取金额，money表示充值多少
		double newMoney = oldMoney+money; 
		return moneyDao.updateMoneyByName(name, newMoney);
	}

}
