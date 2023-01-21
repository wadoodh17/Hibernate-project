package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Company;
import com.model.Product;

public class ProductAndCompanyDAO {
	
	public static void main(String[] args) {
		
		Product product1 = new Product();
		product1.setId(101L);
		product1.setName("Pencil");
		
		Product product2 = new Product();
		product2.setId(102L);
		product2.setName("Eraser");
		
		Company company = new Company();
		company.setId(201L);
		company.setName("Apsara");
		
		company.getProducts().add(product1);
		company.getProducts().add(product2);
		
		product1.getCompanies().add(company);
		product2.getCompanies().add(company);
		
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(company);
		session.save(product1);
		session.save(product2);

		tx.commit();
		session.close();
		
	}
}
