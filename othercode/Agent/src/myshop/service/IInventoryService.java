package myshop.service;

import java.util.List;

import myshop.ItemInfo;

public interface IInventoryService {
	
	//库存服务
	public List<ItemInfo> getInventory(String name);
	
	//更新库存
	public boolean updateInventoryItem(String name,ItemInfo item);
	
}
