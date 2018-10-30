package myshop.dao;

import java.util.List;

import myshop.ItemInfo;

/**
 * Created by MCY on 2016/9/19.
 */
public interface IShopItemDao {
	
	//通过商店名查找项
	public List<ItemInfo> findItemsByShopName(String shopName);
	
	//查找商店项
	public ItemInfo findItem(String shopName,String itemName);
	
	//增加商店项
	public boolean addItem(String shopName,ItemInfo item);
	
	//删除商店项
	public boolean deleteItem(String shopName,String itemName);
	
	//更新商店项
	public boolean updataItem(String shopName,ItemInfo oldItem,ItemInfo newItem);
	
}
