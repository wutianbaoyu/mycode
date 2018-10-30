package myshop.dao;

import java.util.List;

import myshop.ItemInfo;

public interface IInventoryDao {
	
	//获取商品名称
	public List<ItemInfo> getInventoryByName(String name);
	
	//增加商品名称
	public boolean addInventory(String name,ItemInfo item);
	
	//更新商品名称
	public boolean updateInventory(String name,ItemInfo item);
}
