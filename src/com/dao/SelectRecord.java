package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Employee;

public class SelectRecord {

	public static void main(String[] args) {
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Employee employeeobj = session.get(Employee.class, 1257);
		System.out.println("show employee");
		System.out.println("....................");
		System.out.println("employee no = "+employeeobj.getEmpno());
		System.out.println("employee name is "+employeeobj.getEname());
		System.out.println("job is "+employeeobj.getJob());
		System.out.println("Salary is "+employeeobj.getSal());
		System.out.println("department no is "+employeeobj.getDeptno());
		session.close();

	}

}
