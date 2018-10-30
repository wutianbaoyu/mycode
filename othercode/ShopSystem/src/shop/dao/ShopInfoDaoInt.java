package shop.dao;

import java.util.List;

import domain.Item;
import domain.Shop;
import shop.ItemInfo;

public interface ShopInfoDaoInt {
	
	public List<Integer> getItemsByShopId(int shopId) throws Exception;
	
	public List getShopItemInfoByShopName(String shopname);
    
	public void add_shop_Item(Shop shop,Item item);

	public void update_shop_item(Shop shop,Item item);
	
	public int get_ItemID_From_shopItemInfoByShopNameAndItemName(String shopname,String itemname);
	
	public void remove_From_shopItemInfoByShopIdAndItemId(int shopId,int itemId);
	
}
