
package com.business;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import students;

public class BusinessService 
{
    private static SessionFactory sessionFactory;
private static Configuration configuration = new Configuration();

	
	static {
		if(sessionFactory == null)
		try {
			configuration.configure();   //启动hibernate，读配置文件hibernate.cfg.xml
			sessionFactory = configuration.buildSessionFactory();   //创建SessionFactory实例
		} catch (HibernateException he) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			throw he;
		}
}


	public static void rebuildSessionFactory()throws HibernateException
{
		try {
			configuration.configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (HibernateException he) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			throw he;
		}
	}	


	/** 1、查询所有的Monkey对象，在控制台打印 */
	@SuppressWarnings("unchecked")
	public void findAllMonkeys() throws HibernateException
	{
		if (sessionFactory == null) 
		{
			rebuildSessionFactory();
		}
		
		Session session = sessionFactory.openSession();   //创建一个会话
	    List<Monkey> monkeys = new ArrayList<Monkey>();
	    Monkey  monkey = new Monkey();
	    Transaction tx = null;            //声明一个事务
	    try
	    {
	    	tx = session.beginTransaction();    //开始一个事务
	    	Query query = session.createQuery("from Monkey");
	        monkeys = query.list();   // monkeys是结果集
	        tx.commit();              //提交事务
	    }catch (HibernateException he) 
	    {
	        if (tx != null)
	        {
	        	tx.rollback();
	        }
	    	throw he;
	    } finally 
	    {
	       session.close();
	    }
	      
	    for (Iterator<Monkey> iter = monkeys.iterator(); iter.hasNext();) 
	    {
	       monkey=(Monkey) iter.next();
	       System.out.println("ID="+monkey.getId() + ",姓名="+monkey.getName() + ",年龄="+monkey.getAge() + ",性别="+monkey.getGender());
	    }	    
	  }
	

	public static void main(String[] args)  throws HibernateException
	{
		BusinessService b = new BusinessService();
	    b.findAllMonkeys();		
	}
}



