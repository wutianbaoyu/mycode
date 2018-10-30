package shop.dao;

import java.util.List;

import shop.ItemInfo;

public interface CustomerItemDaoInt {
	public List<ItemInfo> getCustomerInventoryById(int id);
	public void addCustomerInventory(String name,double price);
	public void updateCustomerInventory(String name,double price);
	public boolean checkCustomerInventory(String name,double price);
}
