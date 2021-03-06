package myshop.service;

import java.util.List;

import myshop.ItemInfo;
import myshop.dao.IShopItemDao;
import myshop.dao.ShopItemDaoImpl;

/**
 * Created by MCY on 2016/9/19.
 */
public class ShopItemServiceImpl implements IShopItemService {

	private IShopItemDao shopItemDao = new ShopItemDaoImpl();
	
	/*
	 * Ν¨ΉύΙΜΖ·
	 */
	@Override
	public List<ItemInfo> findItemsByShopName(String shopName) {
		List<ItemInfo> result = shopItemDao.findItemsByShopName(shopName);
		return result;
	}
	
	/*
	 * εεΊεε
	 */
	@Override
	public boolean sellItem(String shopName,ItemInfo item) {
		ItemInfo fItem = shopItemDao.findItem(shopName, item.getName());
		if(fItem.getName()==null||fItem.getQuantity()==0){
			return false;
		}
		fItem.setQuantity(fItem.getQuantity()-1);
		return shopItemDao.updataItem(shopName, fItem, fItem);
	}

	/*
	 * ζ·»ε εε
	 */
	@Override
	public boolean addItem(String shopName, ItemInfo item) {
		ItemInfo fItem = shopItemDao.findItem(shopName, item.getName());
		if(fItem.getName()!=null){
			return false;
		}
		if(item.getName().equals("")){
			return false;
		}
		return shopItemDao.addItem(shopName, item);
	}

	/*
	 * ε ι€εε
	 */
	@Override
	public boolean deleteItem(String shopName, String itemName) {
		ItemInfo fItem = shopItemDao.findItem(shopName, itemName);
		if(fItem.getName()==null){
			return false;
		} 
		return shopItemDao.deleteItem(shopName, itemName);
	}

	/*
	 * ζ΄ζ°εε
	 */
	@Override
	public boolean updateItem(String shopName, ItemInfo oldItem,
			ItemInfo newItem) {
		ItemInfo fItem = shopItemDao.findItem(shopName, oldItem.getName());
		if(fItem.getName()==null){
			return false;
		}
		return shopItemDao.updataItem(shopName, oldItem, newItem);
	}

}
