package myshop.service;

import java.util.List;

import myshop.ItemInfo;

public interface IInventoryService {
	
	//������
	public List<ItemInfo> getInventory(String name);
	
	//���¿��
	public boolean updateInventoryItem(String name,ItemInfo item);
	
}
