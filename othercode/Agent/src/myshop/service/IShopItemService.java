package myshop.service;

import java.util.List;

import myshop.ItemInfo;

/**
 * Created by MCY on 2016/9/19.
 */
public interface IShopItemService {

	//查找商店的全部商品
	public List<ItemInfo> findItemsByShopName(String shopName);
		
	//卖出商品
	public boolean sellItem(String shopName,ItemInfo item);
	
	//添加商品
	public boolean addItem(String shopName,ItemInfo item);
		
	//删除商品
	public boolean deleteItem(String shopName,String itemName);
		
	//更改商品
	public boolean updateItem(String shopName,ItemInfo oldItem,ItemInfo newItem);
}
