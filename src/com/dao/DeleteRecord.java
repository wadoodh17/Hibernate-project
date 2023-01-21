package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Employee;

public class DeleteRecord {

	public static void main(String[] args) {
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Employee employee = session.get(Employee.class, 1258);
		session.delete(employee);
		tx.commit();
		System.out.println(" Data is Deleted...");
		session.close();
	}

}

		
