package myshop.service;

import java.util.List;

import myshop.ItemInfo;
import myshop.dao.IInventoryDao;
import myshop.dao.InventoryDaoImpl;

public class InventoryServiceImpl implements IInventoryService {
//�˿Ϳ���ʵ����
	private IInventoryDao inventoryDao =new InventoryDaoImpl();
//�������ݿ�ӿڵ�ʵ����
	//��ȡ�˿Ϳ��
	@Override
	public List<ItemInfo> getInventory(String name) {
		List<ItemInfo> result = inventoryDao.getInventoryByName(name);
		return result;
	}

	//���¹˿Ϳ��
	@Override
	public boolean updateInventoryItem(String name, ItemInfo item) {//�˿͵�����
		List<ItemInfo> find = inventoryDao.getInventoryByName(name);
		ItemInfo ii = null;
		for(ItemInfo tmp : find){//��find�б���������֮��һ������ֵ��tmp
			if(tmp.getName().equals(item.getName()) && tmp.getPrice()==item.getPrice()){
				ii = tmp;
				break;
			}	
		}	
		if(ii==null){
			return inventoryDao.addInventory(name, item);//�������Ŀ����
		}else{
			ii.setQuantity(ii.getQuantity()+item.getQuantity());
			return inventoryDao.updateInventory(name, ii);
		}
	}

}
