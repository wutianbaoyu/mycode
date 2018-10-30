package myshop.dao;

import java.util.List;

import myshop.ItemInfo;

public interface IInventoryDao {
	
	//��ȡ��Ʒ����
	public List<ItemInfo> getInventoryByName(String name);
	
	//������Ʒ����
	public boolean addInventory(String name,ItemInfo item);
	
	//������Ʒ����
	public boolean updateInventory(String name,ItemInfo item);
}
