package myshop.dao;

import java.util.List;

import myshop.ItemInfo;

/**
 * Created by MCY on 2016/9/19.
 */
public interface IShopItemDao {
	
	//ͨ���̵���������
	public List<ItemInfo> findItemsByShopName(String shopName);
	
	//�����̵���
	public ItemInfo findItem(String shopName,String itemName);
	
	//�����̵���
	public boolean addItem(String shopName,ItemInfo item);
	
	//ɾ���̵���
	public boolean deleteItem(String shopName,String itemName);
	
	//�����̵���
	public boolean updataItem(String shopName,ItemInfo oldItem,ItemInfo newItem);
	
}
