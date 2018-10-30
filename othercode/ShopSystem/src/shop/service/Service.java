package shop.service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.ItemInfo;
import shop.dao.CustomerDaoInt;
import shop.dao.CustomerItemDaoInt;
import shop.dao.ItemInfoDaoInt;
import shop.dao.ShopDaoInt;
import shop.dao.ShopInfoDaoInt;
import shop.dao.impl.CustomerDaoImpl;
import shop.dao.impl.CustomerItemDaoImpl;
import shop.dao.impl.ItemInfoDaoImpl;
import shop.dao.impl.ShopDaoImpl;
import shop.dao.impl.ShopInfoDaoImpl;
import shop.util.DBUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import domain.Item;
import domain.Shop;

public class Service {

	private ItemInfoDaoInt itemInfoDao = new ItemInfoDaoImpl();
    private ShopInfoDaoInt shopInfoDao = new ShopInfoDaoImpl(); 
    private ShopDaoInt shopDao = new ShopDaoImpl();
    private CustomerDaoInt customerDao = new CustomerDaoImpl();
    private CustomerItemDaoInt customerItemDao = new CustomerItemDaoImpl();
    
    //根据顾客id获取顾客的钱
    public double getMoneyByCustomerId(int customerId) {
    	return customerDao.getMoneyByCustomerId(customerId);
    }
    //修改客户钱包
    public void updateCustomerMoney(double money, int customerId) {
    	customerDao.updateCustomerMomey(money, customerId);
    }
    public void updateCustomerMoney2(double money, String customername) {
    	customerDao.updateCustomerMomey(money, customername);
    }
    //获取客户的资产
    public List<ItemInfo> getCustomerInventory(int customer_id){
    	return customerItemDao.getCustomerInventoryById(customer_id);
    }
    //添加客户买的商品
    public void addCustomerInventory(String name, double price){
    	//先判断数据库里面是否已经有这个条目，有，则更新数量加1，没有则插入一条记录
    	boolean check = customerItemDao.checkCustomerInventory(name, price);
    	if(check){
    		//数据库已存在，则更新
    		customerItemDao.updateCustomerInventory(name, price);
    	}else{
    		//数据库没有则插入记录
    		customerItemDao.addCustomerInventory(name, price);
    	}
    }
	/**
	 * 根据Id号查询产品信息
	 */
	public List getShopInfo(int i) throws Exception{

		List<Integer> list = shopInfoDao.getItemsByShopId(i);
		return itemInfoDao.getItemsInfoByIds(list);
	}
	/**
	 * 根据商店名查询产品信息
	 */
	public List getShopInfoByShopName(String shopname) {
		
	//	System.out.println("验证参数：" + shopname );
		String name=this.spiltShopName(shopname);
		List<Integer> list = shopInfoDao.getShopItemInfoByShopName(name);
	
		return itemInfoDao.getItemsInfoByIds(list);
	}
	
	
	//======================================================
	
	/**
	 * 更新商店的资金
	 */
    public void updateCustomerAccountMoney(double money,String shopname){
    	shopDao.updateShopMomey(money, shopname);
    }
    
    /**
	 * 更新商店的商品数量
	 */
    
    public void updateItemQuantityByItemName(List<ItemInfo> Catalog){
    	System.out.println("shop.service.Service 更新商店的商品数量");
    	shopDao.updateItemQuantityByItemName(Catalog);
    }
    
    /**
     * 买商品，刷新数据 在CustomerPannel 上调用
     */
    public void buyItemFromShop(Object object , String itemName , double ItemPrice){
    	
    	String shopname  = String.valueOf(object);
    //	System.out.println(shopname+" = shopname");
    	//获取商店名
    	int index=shopname.lastIndexOf(",");
    	shopname = shopname.substring(0, index);
    	System.out.println("shop.service.Service  输出商店名称 并初始化 商店钱数 从数据库获取 ：" + shopname);
    	
    	System.out.println(" shop.Service() 商店名：" + shopname + " 商品名："+itemName + " 商品价格" + ItemPrice);
    	/*
    	 * 先在商品数量先减
    	 * 商店钱数加
    	 */
    	int quality= this.getItemInfoQalityByName(itemName);
        this.updateItemInfoQualityByName(itemName, quality-1);
        //商店钱数加
        double shopMoney = this.getShopMoneybyShopName(shopname);
        this.updateShopMoney(shopMoney+ItemPrice, shopname);
        
    }
    
    ///////////////////////////  ShopDao  //////////////////////////////////
    
    public double getShopMoneybyShopName(String shopname){
    	double result= shopDao.getShopMoneyByShopName(shopname);
		return result;
    }
	/**
	 * 更新商店卖出的商品收获的钱
	 */
    public void updateShopMoney(double money,String shopname){
    	System.out.println("更新数据  当前的钱："+money + " 商店名："+ shopname);
    	shopDao.updateShopMomey(money, shopname);
    }
    
	/**
	 * 更新商店卖出的商品收获的钱
	 */
    public void updateShopAccountMoney(double money,String shopname){
    	int index=shopname.lastIndexOf(",");//获取商店名
    	String str=shopname.substring(0, index);
    	System.out.println("shop.service.Service 更新商店的钱数  " + " 钱:"+money+" 商店名："+shopname);
    	shopDao.updateShopMomey(money, str);
    }
    
    
    /////////////////////////////  ItemInfoDao  ///////////////////////////////////////////
    
    /**
     * 
     * @param itemName
     * @return
     */
    public int getItemInfoQalityByName(String itemName){
    	return itemInfoDao.getItemInfoQualityByName(itemName);
    }
    public void updateItemInfoQualityByName(String itemName , int quality) {
    	 System.out.println("输出数量：" + quality);
    	 itemInfoDao.updateItemInfoQualityByName(itemName,quality);
    }
    
    //=================  add 业务  ============================================
    
    public boolean getItemInfoByName(String Itemname){
    	boolean flag = false;
    	Item result=new Item();
    	Item item = itemInfoDao.getItemInfoByName(Itemname);
    	
    	if(item.getName()!=null){
    		System.out.println("ming :"+item.getName()+" jiage :"+item.getPrice()+" shuliang:"+item.getQuantity());
    		flag = true;
    	}
    	return flag;
    }
    
    
    
    
    /**
     * add 业务  
     * @param iteminfo
     * @param shopName
     */
    public void addItemInfoByShopName(ItemInfo iteminfo , String shopName ) {
    	
    	System.out.println(" add 验证一下参数："+shopName+ " 商品名  "+iteminfo.getName());
    	//第一步：插入数据itemInfo
    	//第二步：获取新的iteminfo的全部信息
    	//第三步：获取shop的全部信息
    	//第四步：把全部信息写到关联表
    	
    	///////////////////////////////////////////
    	
    	itemInfoDao.addItemInfo(iteminfo);
    	Item item = itemInfoDao.getItemInfoByName(iteminfo.getName());
    	Shop shop = shopDao.getShopInfoByName(this.spiltShopName(shopName));
    	
    	shopInfoDao.add_shop_Item(shop, item);
    }
    
    /**
     * edit 业务  每操作一次ItemInfo都会调用一次，用来更新
     * @param iteminfo
     * @param shopName
     */
    public void editItemInfoByShopName(ItemInfo iteminfo , ItemInfo oldIteminfo, String shopName ){
 
    	 String shopname=this.spiltShopName(shopName);
    	 System.out.println(this.spiltShopName(shopName));
    	//第一步：根据商店名，获取商店信息，商店ID
    	//第二步：通过 商店——商品表找到 商品ID;根据  旧商店名 和  旧商品名：
    	//第三步：修改更新商品表数据
    	//第四步：更新 商店-商品的 数据：
    	 
    	 //////////////////////////////////////////////////////
    	 Item olditem =new Item();
    	 Item newitem =new Item();
    	 Shop shop = shopDao.getShopInfoByName(shopname);
    	 //根据旧商品名和 商店名 获取 商品ID
    	 int olditemId= shopInfoDao.get_ItemID_From_shopItemInfoByShopNameAndItemName(shopname,oldIteminfo.getName());
    	 olditem.setId(olditemId);
    	 olditem.setName(oldIteminfo.getName());
    	 olditem.setPrice(oldIteminfo.getPrice());
    	 olditem.setQuantity(oldIteminfo.getQuantity());
    	 
    	 /////////////////
    	 newitem.setId(olditemId);
    	 newitem.setName(iteminfo.getName());
    	 newitem.setPrice(iteminfo.getPrice());
    	 newitem.setQuantity(iteminfo.getQuantity());
    	 
    	 //修改item_info表的信息
    	 itemInfoDao.updateItemInfo(newitem);
    	 
    	//修改shop_info表的信息
    	 shopInfoDao.update_shop_item(shop, newitem);
    	 
    }
    /**
     * 移除数据  remove
     * @param iteminfo
     * @param shopName
     */
    public void removeItemInfoByShopName(ItemInfo iteminfo , String shopName ){

	   	 String shopname=this.spiltShopName(shopName);
	   	 System.out.println(this.spiltShopName(shopName));
	   	 
	   	 //第一步：去关联表，获取实体表Id,根据商店名和商品名；
	   	 //第二步：去商店表，获取商店信息，根据商店名
	     //第三步：删除实体表，根据实体表Id
	   	 //第四步：删除关联表，根据商店ID,实体ID
    	 Item item =new Item();
    	 int itemId= shopInfoDao.get_ItemID_From_shopItemInfoByShopNameAndItemName(shopname,iteminfo.getName());
    	 item.setId(itemId);
    	 item.setName(iteminfo.getName());
    	 item.setPrice(iteminfo.getPrice());
    	 item.setQuantity(iteminfo.getQuantity());
    	 
    	 ///获取商店信息
    	 Shop shop = shopDao.getShopInfoByName(shopname);
    	 
    	 //remove
    	 itemInfoDao.removeItemInfoById(item.getId());
    	 shopInfoDao.remove_From_shopItemInfoByShopIdAndItemId(shop.getId(), item.getId());
	   	 
    }
    
    
    ///////////////////////////////////////////////////////////////
    /*====================  util  =======================*/
    
    private String spiltShopName(String shopname){
 
    	int index=shopname.lastIndexOf(",");
    	String str=shopname.substring(0, index);
    	return str;
    }
    
    
    
}
