package myshop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import myshop.CustomerCapability.chargeMoneyGoal;
import myshop.service.IMoneyService;
import myshop.service.IShopItemService;
import myshop.service.MoneyServiceImpl;
import myshop.service.ShopItemServiceImpl;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalResult;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ICapability;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

/**
 * 
 */
@Capability
@ProvidedServices(@ProvidedService(type=IShopService.class, //	implementation=@Implementation(value=ShopService.class)))
	implementation=@Implementation(expression="new ShopService($pojocapa.getShopSeriverName())")))
public class ShopCapa
{
	@Agent
	private ICapability capa;

	@Belief
	public native double getMoney();
	
	@Belief
	public native void setMoney(double money);
	
	/** The shop catalog.*/
	@Belief
	protected List<ItemInfo> catalog = new ArrayList<ItemInfo>();
	
	/** The shop name. */
	protected String shopname;
	
	protected IShopItemService shopItemService = new ShopItemServiceImpl();
	
	protected IMoneyService moneyService = new MoneyServiceImpl();
	
	/**
	 *  Create a shop capability.
	 */
	public ShopCapa(String shopname)
	{
		this.shopname	= shopname;
		setMoney(moneyService.getMoney(getShopname()));
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ShopFrame(capa);
			}
		});
	}
	
	/*
	 * 获取商店服务名字
	 */
	public String	getShopSeriverName()
	{
		return shopname+","+capa.getAgent().getComponentIdentifier().getPlatformName();
	}
	
	
	/**
	 *  获取商店名字.
	 */
	public String	getShopname()
	{
		return shopname;
	}
	
	/**
	 *  获取目录.
	 */
	public  List<ItemInfo> getCatalog()
	{
		return this.catalog;
	}
	
	/*
	 * 目标:卖
	 */
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
	
	/*
	 * 目标：获取商品数据
	 */
	@Goal
	public static class  getShopItemsGoal{
		public getShopItemsGoal(){
			
		}
	}
	
	/*
	 * 目标:添加商品
	 */
	@Goal
	public static class addItemGoal{
		protected String name;
		protected double price;
		protected int quantity;
		
		public addItemGoal(String name,double price,int quantity){
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

	}
	
	/*
	 * 目标：删除商品
	 */
	@Goal
	public static class  deleteItemGoal{
		
		protected String name;
		
		public deleteItemGoal(String name){
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/*
	 * 目标：更新商品
	 */
	@Goal
	public static class updateItemGoal{
		protected ItemInfo oldItem;
		protected ItemInfo newItem;
		
		public updateItemGoal(ItemInfo oldItem,ItemInfo newItem){
			this.oldItem = oldItem;
			this.newItem = newItem;
		}

		public ItemInfo getOldItem() {
			return oldItem;
		}

		public void setOldItem(ItemInfo oldItem) {
			this.oldItem = oldItem;
		}

		public ItemInfo getNewItem() {
			return newItem;
		}

		public void setNewItem(ItemInfo newItem) {
			this.newItem = newItem;
		}
		
	}
	
	/*
	 * 目标：充值金钱
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
	
	/**
	 *  规划：卖
	 *  @param goal The goal.
	 */
	@Plan(trigger=@Trigger(goals=SellGoal.class))
	public void sell(SellGoal goal)
	{
		ItemInfo tst = new ItemInfo(goal.getName());
		ItemInfo ii = null;
		int pos = 0;
		for(; pos<this.catalog.size(); pos++)
		{
			ItemInfo tmp = this.catalog.get(pos);
			if(tmp.equals(tst))
			{
				ii = tmp;
				break;
			}
		}
		
		// Check if enough money is given and it is in stock.
		if(ii==null || ii.getQuantity()==0)
		{
			throw new RuntimeException("商品已下架或库存不足: "+goal.getName());
		}
		else if(ii.getQuantity()>0 && ii.getPrice()<=goal.getPrice())
		{
			//对数据库进行卖商品操作
			shopItemService.sellItem(this.shopname, ii);
			
			// Sell item by updating catalog and account
////		System.out.println(getComponentName()+" sell item: "+name+" for: "+price);
			ii.setQuantity(ii.getQuantity()-1);
			goal.setResult(new ItemInfo(goal.getName(), ii.getPrice(), 1));
//			getBeliefbase().getBeliefSet("catalog").modified(ii);
			this.catalog.set(pos, ii);
			
			//更新金钱
			moneyService.updateMoney(getShopname(), goal.getPrice());
			setMoney(getMoney()+goal.getPrice());
		}
		else
		{
			throw new RuntimeException("金钱不足: "+goal.getPrice());
		}
	}
	/**
	 *  规划：获取商店的商品数据
	 */
	@Plan(trigger=@Trigger(goals=getShopItemsGoal.class))
	public void getShopItems(getShopItemsGoal goal){
		List<ItemInfo> result = shopItemService.findItemsByShopName(this.getShopname());
		for(ItemInfo item :result){
			this.getCatalog().add(item);
		}
	}
	
	/*
	 * 规划:删除商品
	 */
	@Plan(trigger=@Trigger(goals=deleteItemGoal.class))
	public void deleteItem(deleteItemGoal goal){
		
		//对数据库操作
		boolean flag = shopItemService.deleteItem(getShopname(), goal.getName());
		//对目录操作
		ItemInfo tst = new ItemInfo(goal.getName());
		if(flag){
			for(int pos = 0; pos<this.catalog.size(); pos++)
			{
				ItemInfo tmp = this.catalog.get(pos);
				if(tmp.equals(tst))
				{
					this.catalog.remove(pos);
					break;
				}
			}
		}
	}
	
	/*
	 * 规划：添加商品
	 */
	@Plan(trigger=@Trigger(goals=addItemGoal.class))
	public void addItem(addItemGoal goal){
		ItemInfo item = new ItemInfo(goal.getName(),goal.getPrice(),goal.getQuantity());
		//对数据库操作
		boolean flag = shopItemService.addItem(getShopname(), item);
		//对目录操作
		if(flag){
			this.catalog.add(item);
		}
	}
	
	/*
	 * 规划：更新商品
	 */
	@Plan(trigger=@Trigger(goals=updateItemGoal.class))
	public void updateItem(updateItemGoal goal){
		
		//对数据库操作
		boolean flag = shopItemService.updateItem(getShopname(), goal.getOldItem(),goal.getNewItem());
		//对目录操作
		ItemInfo oldItem = goal.getOldItem();
		if(flag){
			for(int pos = 0; pos<this.catalog.size(); pos++)
			{
				ItemInfo tmp = this.catalog.get(pos);
				if(tmp.equals(oldItem))
				{
					this.catalog.set(pos, goal.getNewItem());
					break;
				}
			}
		}else{
			throw new RuntimeException("后台暂时无法更新");
		}
	}
	
	/*
	 * 规划：充值金钱
	 */
	@Plan(trigger=@Trigger(goals=chargeMoneyGoal.class))
	public void	chargeMoney(chargeMoneyGoal goal){
		if( goal.getMoney()<=0){
			throw new RuntimeException("充值金额不能小于0或为0");
		}
		boolean flg = moneyService.chargeMoney(getShopname(), goal.getMoney());
		if(!flg){
			throw new RuntimeException("后台暂时无法充值");
		}
		//更新金钱信念
		setMoney(getMoney()+goal.money);
	}
	
}
