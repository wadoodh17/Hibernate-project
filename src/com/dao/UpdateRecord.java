package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Employee;

public class UpdateRecord {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Employee employee = session.get(Employee.class, 1257);
		employee.setSal(30000);
		session.update(employee);
		tx.commit();
		System.out.println("salary is updated...");
		session.close();
	}

}
