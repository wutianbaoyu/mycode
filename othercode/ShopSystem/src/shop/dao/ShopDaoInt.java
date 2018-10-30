package shop.dao;

import java.sql.SQLException;
import java.util.List;

import domain.Shop;
import shop.ItemInfo;
import shop.ShopCapa;

public interface ShopDaoInt  {

	/**
	 * 更新商店的钱数
	 */
	public void updateShopMomey(double money,int shopId);
	public void updateShopMomey(double money,String shopname);
	
	public double getShopMoneyByShopId(int shopId);
	public Shop getShopInfoByName(String shopname) ;
	
	public double getShopMoneyByShopName(String shopname);
	void updateItemQuantityByItemName(List<ItemInfo> Catalog);
	
}
