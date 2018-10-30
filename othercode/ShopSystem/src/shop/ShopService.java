package shop;

import shop.ShopCapa.SellGoal;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.ICapability;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;

/**
 *  The shop for buying goods at the shop.
 */
@Service
public class ShopService implements IShopService 
{
	//-------- attributes --------
	
	/** The component. */
	@ServiceComponent
	protected ICapability	capa;
	
	/** The shop name. */
	protected String name;
	
	//-------- constructors --------

	/**
	 *  Create a new shop service.
	 */
	public ShopService(String name)
	{
		this.name = name;
	}

	//-------- methods --------
	
	/**
	 *  Get the shop name. 
	 *  @return The name.
	 *  
	 *  @directcall (Is called on caller thread).
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 *  Buy an item.
	 *  @param item The item.
	 */

	public IFuture<ItemInfo> buyItem(final String item, final double price)
	{
		ShopCapa shop = (ShopCapa)capa.getPojoCapability();//能力实装
		shop.ShopCapa.SellGoal sell = shop.new SellGoal(item, price);
		return capa.getAgent().getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(sell);
	}
	
	
	/**
	 *  Get the item catalog.
	 *  @return  The catalog.
	 */	
	public IFuture<ItemInfo[]> getCatalog()//获取商品目录
	{
		final Future<ItemInfo[]> ret = new Future<ItemInfo[]>();
		ShopCapa shop = (ShopCapa)capa.getPojoCapability();
		
		////////////////////////////////////////////////////////////
		java.util.List<ItemInfo> catalog =new shop.service.Service().getShopInfoByShopName(shop.getShopname());
		shop.setCatalog(catalog);
		
		//通过商品名获得该商店目录 买后数量改变  返回数组 从新获取目录
		/////////////////////////////////////////////////////////////
				
		ret.setResult(shop.getCatalog().toArray(new ItemInfo[shop.getCatalog().size()]));
		return ret;
	}

	/**
	 *  Get the string representation.
	 *  @return The string representation.
	 */
	public String toString()
	{
		return name;
	}
}