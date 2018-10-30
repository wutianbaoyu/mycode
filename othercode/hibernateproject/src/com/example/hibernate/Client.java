package com.example.hibernate;

import java.util.Date;  

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;  
  
public class Client {  
    public static void main(String[] args) {  
        //��ȡ�����ļ�  
        Configuration cfg = new Configuration().configure();  
          
        SessionFactory factory = cfg.buildSessionFactory();  
          
        Session session = null;  
        try{  
            session = factory.openSession();  
            //��������  
            session.beginTransaction();  
              
            User user = new User();  
            user.setUsername("�û���");  
            user.setPassword("123");  
            user.setCreateTime(new Date());  
            user.setExpireTime(new Date());  
              
            session.save(user);  
            //�ύ����  
            session.getTransaction().commit();  
              
        }catch(Exception e){  
            e.printStackTrace();  
            //�ع�����  
            session.getTransaction().rollback();  
        }finally{  
            if(session != null){  
                if(session.isOpen()){  
                    //�ر�session  
                    session.close();  
                }  
            }  
        }  
    }  
}  