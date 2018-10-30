package shop;

import jadex.commons.future.IFuture;

/**
 *  The shop interface for buying goods at the shop.
 *  在商店买东西的接口
 */
public interface IShopService
{
	/**
	 *  Get the shop name. 
	 *  @return The name.
	 */
	public String getName();
	

	/**
	 *  Buy an item.
	 *  @param item The item.
	 */
	public IFuture<ItemInfo> buyItem(String item, double price);
	
	/**
	 *  Get the item catalog.
	 *  @return  The catalog.
	 */
	public IFuture<ItemInfo[]> getCatalog();
}
