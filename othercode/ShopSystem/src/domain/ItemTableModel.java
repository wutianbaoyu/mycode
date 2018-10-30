package domain;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import shop.ItemInfo;

public class ItemTableModel extends AbstractTableModel
{
	protected List list;

	
	public ItemTableModel(List list)
	{
		this.list = list;
	}
	
	public int getRowCount()
	{
		return list.size();
	}

	public int getColumnCount()
	{
		return 3;
	}

	public String getColumnName(int column)
	{
		switch(column)
		{
			case 0:
				return "商品名"; // Name
			case 1:
				return "商品价格"; // Price
			case 2:
				return "商品数"; // Quantity
			default:
				return "";
		}
	}

	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	public Object getValueAt(int row, int column)
	{
		Object value = null;
		ItemInfo ii = (ItemInfo)list.get(row);
		if(column == 0)
		{
			value = ii.getName();
		}
		else if(column == 1)
		{
			value = Double.valueOf(ii.getPrice());
		}
		else if(column == 2)
		{
			value = Integer.valueOf(ii.getQuantity());
		}
		return value;
	}
	
    public void setValueAt(Object aValue, int row, int column) {
    	ItemInfo ii = (ItemInfo)list.get(row);
    	if(column == 0)
		{
			if(aValue instanceof String){
				ii.setName((String)aValue);
			}
		}
		else if(column == 1)
		{
			if(aValue instanceof Double){
				ii.setPrice((double)aValue);
			}
		}
		else if(column == 2)
		{
			if(aValue instanceof Integer){
				ii.setQuantity((int)aValue);
			}
		}
    }
}
