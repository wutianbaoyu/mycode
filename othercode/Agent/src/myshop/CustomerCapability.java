package myshop;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalResult;
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

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import myshop.service.IInventoryService;
import myshop.service.IMoneyService;
import myshop.service.InventoryServiceImpl;
import myshop.service.MoneyServiceImpl;

/**
 *  Customer capability.
 */
@Capability
@Properties(@NameValue(name="componentviewer.viewerclass", value="\"myshop.CustomerViewerPanel\""))
@RequiredServices({
	@RequiredService(name="localshopservices", type=IShopService.class, multiple=true,
		binding=@Binding(scope=Binding.SCOPE_PLATFORM)),
	@RequiredService(name="remoteshopservices", type=IShopService.class, multiple=true,
		binding=@Binding(scope=Binding.SCOPE_GLOBAL)),
})
public class CustomerCapability
{
	//-------- attributes --------

	/** The capability. */
	@Agent
	protected ICapability capa;
	
	/** The inventory. */
	@Belief
	protected List<ItemInfo> inventory = new ArrayList<ItemInfo>();
	
	protected String customerName ;
	
	protected IMoneyService moneyService = new MoneyServiceImpl();
	//金额的服务
	protected IInventoryService inventoryService = new InventoryServiceImpl();
	//库存的服务
	//-------- constructors --------
	
	/**
	 *  Called when the agent is started.
	 */
	public CustomerCapability(String customerName)
	{
		this.customerName = customerName;
		setMoney(moneyService.getMoney(customerName));//根据顾客名获取相应金额
		SwingUtilities.invokeLater(new Runnable()//请求事件分发线程以运行customerFrame
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
	public native double getMoney();
	
	/**
	 *  Set the money.
	 */
	@Belief
	public native void setMoney(double money);
	
	/*
	 * 鑾峰彇鍚嶅瓧
	 */
	public String getCustomerName(){
		return this.customerName;
	}
	
	/*
	 * 鑾峰彇璧勪骇
	 */
	public List<ItemInfo> getInventory() {
		return this.inventory;
	}
	
	//-------- goals --------
	
	/**
	 *  Goal to buy an item.
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
		
		//-------- constructors --------

		/**
		 *  Create a buy item goal.
		 */
		public BuyItem(String name, IShopService shop, double price)
		{
			this.name	= name;
			this.shop	= shop;
			this.price	= price;
		}
	}
	
	/*
	 * 充值
	 */
	@Goal
	public static class chargeMoneyGoal{
		public double money;
		
		public chargeMoneyGoal(double money){
			this.money = money;
		}
		public double getMoney() {
			return money;
		}

		public void setMoney(double money) {
			this.money = money;
		}
	}
	
	/*
	 * 获取库存目标
	 */
	@Goal
	public static class getInventoryGoal{
		
	}
	//-------- plans --------
	
	/**
	 *  Plan for buying an item.
	 */
	@Plan(trigger=@Trigger(goals=BuyItem.class))
	public void	buyItem(BuyItem big)
	{
		// Check if enough money to buy the item
		if(getMoney()<big.price)
			throw new RuntimeException("你不够钱购买该商品: "+big.name);
		
		// Buy the item at the shop (the shop is a service at another agent)
		System.out.println("瀹㈡埛@"+getCustomerName()+"."+capa.getAgent().getComponentIdentifier().getPlatformName()
							+" 瑕佷拱: "+big.name);
		IFuture<ItemInfo>	future	= big.shop.buyItem(big.name, big.price);
		System.out.println("瀹㈡埛@"+getCustomerName()+"."+capa.getAgent().getComponentIdentifier().getPlatformName()
							+" 鎷垮埌: "+future);
		ItemInfo item = (ItemInfo)future.get();
		System.out.println("瀹㈡埛@"+getCustomerName()+"."+capa.getAgent().getComponentIdentifier().getPlatformName()
							+" 涔板埌 "+item);
		
		
		// 库存变化
		ItemInfo ii = new ItemInfo(big.name,big.price,1);//被购买了的商品数量为1
		boolean flag = inventoryService.updateInventoryItem(getCustomerName(), ii);
		if(flag){
			List<ItemInfo> list = inventoryService.getInventory(getCustomerName());
			inventory.clear();
			for(ItemInfo tmp :list){//在list中遍历，遍历之后一个个赋值给tmp
				inventory.add(tmp);//库存再添加
			}
		}
		
		// 金额设置
		moneyService.updateMoney(getCustomerName(), -big.price);
		setMoney(getMoney() - big.price);
	}
	
	/*
	 * 充值
	 */
	@Plan(trigger=@Trigger(goals=chargeMoneyGoal.class))
	public void	chargeMoney(chargeMoneyGoal goal){
		if( goal.getMoney()<=0){
			throw new RuntimeException("金额 不足");
		}
		boolean flg = moneyService.chargeMoney(getCustomerName(), goal.getMoney());
		if(!flg){
			throw new RuntimeException("充值失败");
		}
		//设置充值后的金额
		setMoney(getMoney()+goal.money);
	}
	
	/*
	 * //目的等于获取库存
	 */
	@Plan(trigger=@Trigger(goals=getInventoryGoal.class))
	public void	getInventory(getInventoryGoal goal){
		List<ItemInfo> result = inventoryService.getInventory(getCustomerName());
		for(ItemInfo item :result){//在result中遍历，遍历之后一个个赋值给item
			this.inventory.add(item);//库存中增加该顾客的库存项
		}
	}
}
