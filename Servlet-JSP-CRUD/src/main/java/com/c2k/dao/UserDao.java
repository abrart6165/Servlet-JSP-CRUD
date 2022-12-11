package com.c2k.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.c2k.model.User;


public class UserDao {
	public boolean insertUser(User u) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		UserDao ud=new UserDao();
		if(ud.checkLogin(u)!=1)
		{session.save(u);
		t.commit();
		session.close();
		factory.close();
		return true;      }
		else {return false;}	
	}

	public static List<User> allUsers() {

		int i = 0;
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		List<User> list = new ArrayList<User>();
		list = session.createQuery("FROM User").list();

		t.commit();
		session.close();

		return list;
	}

	public static boolean updateUser(User u) {

		try {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

			SessionFactory factory = meta.getSessionFactoryBuilder().build();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			session.update(u);

			t.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public int deleteUser(User u) {
		try {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

			SessionFactory factory = meta.getSessionFactoryBuilder().build();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			Query q=  session.createQuery("delete from User where uid="+u.getUid()+"and role=:role1");  
			q.setParameter("role1", "user");
			 int i= q.executeUpdate();
	
			
			//User u = (User) session.load(User.class, uid);
			//session.delete(u);

			t.commit();
			session.close();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public  int checkLogin(User u) {
		int i=0;
		try {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

			SessionFactory factory = meta.getSessionFactoryBuilder().build();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

Query q=  session.createQuery("from User u where u.uname =:uname1 and u.pass =:pass1 and u.role =:role1");
q.setParameter("uname1", u.getUname()); 
q.setParameter("pass1", u.getPass()); 
q.setParameter("role1", u.getRole()); 
User user=(User)q.uniqueResult(); 
t.commit();
session.close();
if(user.getUid()!=0)
{i= 1;}
return i;} 
		catch (Exception e) {
			e.printStackTrace();
			return i;       }
	}
}