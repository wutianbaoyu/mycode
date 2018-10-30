package shop;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalResult;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ICapability;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import shop.store.ShopFrame;

import java.util.List;

import javax.swing.SwingUtilities;
/**
 * 商店能力
 */
@Capability
@ProvidedServices(@ProvidedService(type=IShopService.class, //	implementation=@Implementation(value=ShopService.class)))
	implementation=@Implementation(expression="new ShopService($pojocapa.getShopname())")))
public class ShopCapa
{
	@Belief
	public native double getMoney();
	
	@Belief
	public native void setMoney(double money);
	
	/** The shop name. */
	protected String shopname;
	
	/** The capability. */
	@Agent
	protected ICapability	capa;//自定义
	
	/** The shop catalog. */
	@Belief
	protected List<ItemInfo> catalog;
	
	shop.service.Service service = new shop.service.Service();
	/**
	 *  Create a shop capability.
	 */
	public ShopCapa(String shopname, List<ItemInfo> catalog)
	{
		this.shopname	= shopname;
		this.catalog	= catalog;
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ShopFrame(capa);
			}
		});
	}
	
	/**
	 *  Get the shop name.
	 */
	public String	getShopname()
	{
		return shopname;
	}
	
	/**
	 *  Get the catalog.
	 */
	public List<ItemInfo>	getCatalog()
	{
		return catalog;
	}
	
	/**
	 * Set the catalog.
	 */
	public void setCatalog(List<ItemInfo> catalog)
	{
		this.catalog=catalog;
	}
	
	
	@Goal
	public static class AddItem   //
	{
		//-------- attributes --------
		
		/** The item. */
		public ItemInfo item;
		
		/** The shop. */
		public String	shopname;
		
		//-------- constructors --------

		/**
		 *  Create a buy item goal.
		 */
		public AddItem(ItemInfo item,String shopname)
		{
			this.item = item;
			this.shopname	= shopname;
		}
	}
	
	@Goal
	public static class EditItem
	{
		//-------- attributes --------
		
		/** The new itemInfo. */
		public ItemInfo itemInfo;
		/** The old oldItemInfo. */
		public ItemInfo oldItemInfo;
		
		/** The shop. */
		public String	shop;
		
		//-------- constructors --------

		/**
		 *  Create a buy item goal.
		 */
		public EditItem(ItemInfo itemInfo,ItemInfo oldItemInfo,String shopname)
		{
			this.itemInfo    = itemInfo;
			this.oldItemInfo = oldItemInfo;
			this.shop	     = shopname;
		}
	}
	
	
	@Goal
	public static class RemoveItem
	{
		//-------- attributes --------
		
		/** The item. */
		public ItemInfo item;
		
		/** The shop. */
		public String	shop;
		
		//-------- constructors --------

		/**
		 *  Create a buy item goal.
		 */
		public RemoveItem(ItemInfo item,String shopname)
		{
			this.item = item;
			this.shop	= shopname;
		}
	}
	
	
	
	
	
	@Goal
	public class SellGoal
	{
		/** The text. */
		protected String name;
		
		/** The price. */
		protected double price;
		
		/** The result. */
		@GoalResult
		protected ItemInfo result;

		/**
		 *  Create a new SellGoal. 
		 */
		public SellGoal(String name, double price)
		{
			this.name = name;
			this.price = price;
		}

		/**
		 *  Get the name.
		 *  @return The name.
		 */
		public String getName()
		{
			return name;
		}

		/**
		 *  Get the price.
		 *  @return The price.
		 */
		public double getPrice()
		{
			return price;
		}

		/**
		 *  Get the result.
		 *  @return The result.
		 */
		public ItemInfo getResult()
		{
			return result;
		}

		/**
		 *  Set the result.
		 *  @param result The result to set.
		 */
		public void setResult(ItemInfo result)
		{
			this.result = result;
		}
	}
	
	
	
	
	
	/**
	 * Plan for handling an add item info
	 * @param
	 */
	@Plan(trigger=@Trigger(goals=AddItem.class))
	public void add(AddItem big)
	{	
		if(service.getItemInfoByName(big.item.name)==true)	//查看是否存在商品
			throw new RuntimeException(big.item.name+"已经存在");
		ItemInfo tst = new ItemInfo(big.item.name,big.item.price,big.item.quantity);
		service.addItemInfoByShopName(tst, shopname);
	}
	
	/*
	 * 
	 */
	@Plan(trigger=@Trigger(goals=EditItem.class))
	public void edit(EditItem big)
	{	
		if(service.getItemInfoByName(big.itemInfo.name)&&!(big.oldItemInfo.name.equals(big.itemInfo.name)))
			throw new RuntimeException(big.itemInfo.name+"已经存在");
		service.editItemInfoByShopName(big.itemInfo, big.oldItemInfo, shopname);
	}
	
	
	/**
	 * Plan for handling an remove item info
	 * @param
	 */
	@Plan(trigger=@Trigger(goals=RemoveItem.class))
	public void Remove(RemoveItem big)
	{
		ItemInfo tst = new ItemInfo(big.item.name,big.item.price,big.item.quantity);
		ItemInfo ii = null;
		System.out.println("在这里做数据库删除操作！");
		service.removeItemInfoByShopName(tst, shopname);
	}
	
	
	
	/**
	 *  Plan for handling a sell goal.得到商店中选中的目标
	 *  @param goal The goal.
	 */
	@Plan(trigger=@Trigger(goals=SellGoal.class))
	public void sell(SellGoal goal)
	{
		ItemInfo tst = new ItemInfo(goal.getName());
		ItemInfo ii = null;
		int pos = 0;
		for(; pos<catalog.size(); pos++)
		{
			ItemInfo tmp = catalog.get(pos);
			if(tmp.equals(tst))
			{
				ii = tmp;
				break;
			}
		}
		
		// Check if enough money is given and it is in stock.
		//检查是否足够数量
		if(ii==null || ii.getQuantity()==0)
		{
			throw new RuntimeException("Item not in store: "+goal.getName());
		}
		//否则的话商店的营业额增加
		else if(ii.getQuantity()>0 && ii.getPrice()<=goal.getPrice())
		{
			// Sell item by updating catalog and account
////		System.out.println(getComponentName()+" sell item: "+name+" for: "+price);
			//商品数量-1
			ii.setQuantity(ii.getQuantity()-1);
			goal.setResult(new ItemInfo(goal.getName(), ii.getPrice(), 1));
//			getBeliefbase().getBeliefSet("catalog").modified(ii);
			//刷新商品目录
			catalog.set(pos, ii);
			
			setMoney(getMoney()+goal.getPrice());
		}
		else
		{
			throw new RuntimeException("Payment not sufficient: "+goal.getPrice());
		}
	}
}
