package shop.dao;

import java.util.List;

import domain.Item;
import shop.ItemInfo;

public interface ItemInfoDaoInt {
	

	public List getItemsInfoByIds(List list) ;
	public int getItemInfoQualityByName(String Itemname);
	public Item  getItemInfoByName(String name);
	public void updateItemInfoQualityByName(String itemName,int quality);
	public void updateItemInfo(Item item);
	public void addItemInfo(ItemInfo iteminfo);
	
	public void removeItemInfoById(int id);
	
}
