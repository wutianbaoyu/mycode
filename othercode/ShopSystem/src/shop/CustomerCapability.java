package shop;

/*import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ICapability;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Properties;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;*/
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ICapability;
import jadex.bridge.nonfunctional.annotation.NameValue;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Properties;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;
import shop.service.Service;

/**
 *  Customer capability.
 */
@Capability
@Properties(@NameValue(name="componentviewer.viewerclass", value="\"shop.CustomerViewerPanel\""))
@RequiredServices({
	@RequiredService(name="localshopservices", type=IShopService.class, multiple=true,
		binding=@Binding(scope=Binding.SCOPE_PLATFORM)),
	@RequiredService(name="remoteshopservices", type=IShopService.class, multiple=true,
		binding=@Binding(scope=Binding.SCOPE_GLOBAL)),
})
public class CustomerCapability
{
	//-------- attributes --------
	protected Service service = new Service() ;
	/** The capability. */
	@Agent
	protected ICapability	capa;
	
	/** The inventory. */ //客户资产
	@Belief
	protected List<ItemInfo>	inventory	= new ArrayList<ItemInfo>();
//    protected List<ItemInfo>	inventory	= service.getCustomerInventory(1);
	
	
	//-------- constructors --------
	
	/**
	 *  Called when the agent is started.   当智能体启动时调用
	 */
	public CustomerCapability()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new CustomerFrame(capa);
			}
		});
	}
	
	/**
	 *  Get the money.
	 */
	@Belief
	public native double	getMoney();
	
	/**
	 *  Set the money.
	 */
	@Belief
	public native void	setMoney(double money);
	
	//-------- goals --------
	
	/**
	 *  Goal to buy an item. 购买商品的目标
	 */
	@Goal
	public static class BuyItem
	{
		//-------- attributes --------
		
		/** The item name. */
		public String	name;
		
		/** The shop. */
		public IShopService	shop;
		
		/** The price. */
		public double	price;
		
		/** The quantity. */
		public int quantity;
		
		//-------- constructors --------

		/**
		 *  Create a buy item goal.
		 */
		public BuyItem(String name, IShopService shop, double price,int quantity)
		{
			this.name	  = name;
			this.shop	  = shop;
			this.price    = price;
			this.quantity = quantity;
		}
	}
	
	//-------- plans --------
	
	/**
	 *  Plan for buying an item. 买一个商品的计划，触发BuyItem目标
	 */
	@Plan(trigger=@Trigger(goals=BuyItem.class))
	public void	buyItem(BuyItem big)
	{
		// Check if enough money to buy the item
		if(getMoney()<big.price)
			throw new RuntimeException("   不够去买: "+big.name);
		
		if(big.quantity<1)
			throw new RuntimeException("商品数量不足");
		
		// Buy the item at the shop (the shop is a service at another agent)
		//在商店买东西（这个商店是其他智能体的一个服务）

		System.out.println(capa.getAgent().getComponentIdentifier().getName()+" buying item: "+big.name);
		IFuture<ItemInfo>	future	= big.shop.buyItem(big.name, big.price);//通知商店智能体更新商店界面的数据
		System.out.println("输出购买了的那个商店那个商品并刷到数据库上, 商店名：" + big.shop.getName()
				+ "商品名：" + big.name + "商品价格：" + big.price);
		//shop.service.Service service = new shop.service.Service();
		service.buyItemFromShop(big.shop.getName(), big.name, big.price); // 业务层
		ItemInfo item = (ItemInfo)future.get();
		
		// Update the customer inventory  更新顾客资产
		ItemInfo ii = null;
		for(ItemInfo test: inventory)
		{
			//判断资产里面是否已有该物品，通过对提交的商品的名字和列表中的商店名字对比确认
			if(test.getName().equals(item.getName()) && test.getPrice() == item.getPrice()) 
			{
				ii	= test;
				break;
			}
		}
		if(ii==null)//如果顾客资产中没有该物品，则数量初始为1
		{
			ii = new ItemInfo(big.name, big.price, 1);
			inventory.add(ii);
		}
		else//假如物品已有，则数量+1
		{
			ii.setQuantity(ii.getQuantity()+1);
			int	index	= inventory.indexOf(ii);
			inventory.set(index, ii);
		}
		
		// Update the account 更新顾客钱包
		//减去商品价格
		setMoney(getMoney() - big.price);
		// 更改数据库中用户金额
		 service.updateCustomerMoney(Double.valueOf(getMoney()),1);
		 service.addCustomerInventory(ii.getName(), ii.getPrice());
	}
}
