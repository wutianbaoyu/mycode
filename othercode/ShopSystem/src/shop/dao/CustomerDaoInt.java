package shop.dao;

public interface CustomerDaoInt {
	public double getMoneyByCustomerId(int customerId);
	public double getMoneyByCustomerName(String customerName);

	public String getNameByCustomerId(int customerId);
	
	public void updateCustomerMomey(double money,int customerId);
	public void updateCustomerMomey(double money,String customername);
}
