package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Employee;

public class InsertRecord {

	public static void main(String[] args) {
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Employee employeeobj = new Employee();
		employeeobj.setEmpno(1201);
		employeeobj.setEname("Arul");
		employeeobj.setDeptno(120);
		employeeobj.setJob("Security");
		employeeobj.setSal(6500);
		session.save(employeeobj);
		tx.commit();
		System.out.println("Record Successfully Inserted....");
		session.close();

	}

}
