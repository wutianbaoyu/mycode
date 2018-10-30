package myshop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * 表格模型
 */
public class ItemTableModel extends AbstractTableModel
{
	protected String[] tableHeader = {"名称","价格","数量"};
	protected List list;
	
	public ItemTableModel(List list)
	{
		this.list = list;
		if(this.list==null){
			this.list= new ArrayList();
		}
	}
	
	public int getRowCount()
	{
		return list.size();
	}

	public int getColumnCount()
	{
		return tableHeader.length;
	}

	//自定义表头靠这里，没有这个就不会显示表头
	public String getColumnName(int column)
	{
		return tableHeader[column];
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
};