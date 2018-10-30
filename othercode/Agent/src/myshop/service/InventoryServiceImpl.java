package myshop.service;

import java.util.List;

import myshop.ItemInfo;
import myshop.dao.IInventoryDao;
import myshop.dao.InventoryDaoImpl;

public class InventoryServiceImpl implements IInventoryService {
//顾客库存的实现类
	private IInventoryDao inventoryDao =new InventoryDaoImpl();
//创建数据库接口的实现类
	//获取顾客库存
	@Override
	public List<ItemInfo> getInventory(String name) {
		List<ItemInfo> result = inventoryDao.getInventoryByName(name);
		return result;
	}

	//更新顾客库存
	@Override
	public boolean updateInventoryItem(String name, ItemInfo item) {//顾客的名字
		List<ItemInfo> find = inventoryDao.getInventoryByName(name);
		ItemInfo ii = null;
		for(ItemInfo tmp : find){//在find中遍历，遍历之后一个个赋值给tmp
			if(tmp.getName().equals(item.getName()) && tmp.getPrice()==item.getPrice()){
				ii = tmp;
				break;
			}	
		}	
		if(ii==null){
			return inventoryDao.addInventory(name, item);//增加消耗库存项
		}else{
			ii.setQuantity(ii.getQuantity()+item.getQuantity());
			return inventoryDao.updateInventory(name, ii);
		}
	}

}
