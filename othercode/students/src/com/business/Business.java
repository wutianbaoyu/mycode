
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
			configuration.configure();   //����hibernate���������ļ�hibernate.cfg.xml
			sessionFactory = configuration.buildSessionFactory();   //����SessionFactoryʵ��
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


	/** 1����ѯ���е�Monkey�����ڿ���̨��ӡ */
	@SuppressWarnings("unchecked")
	public void findAllMonkeys() throws HibernateException
	{
		if (sessionFactory == null) 
		{
			rebuildSessionFactory();
		}
		
		Session session = sessionFactory.openSession();   //����һ���Ự
	    List<Monkey> monkeys = new ArrayList<Monkey>();
	    Monkey  monkey = new Monkey();
	    Transaction tx = null;            //����һ������
	    try
	    {
	    	tx = session.beginTransaction();    //��ʼһ������
	    	Query query = session.createQuery("from Monkey");
	        monkeys = query.list();   // monkeys�ǽ����
	        tx.commit();              //�ύ����
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
	       System.out.println("ID="+monkey.getId() + ",����="+monkey.getName() + ",����="+monkey.getAge() + ",�Ա�="+monkey.getGender());
	    }	    
	  }
	

	public static void main(String[] args)  throws HibernateException
	{
		BusinessService b = new BusinessService();
	    b.findAllMonkeys();		
	}
}



