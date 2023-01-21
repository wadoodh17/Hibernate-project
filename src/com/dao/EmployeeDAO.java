package com.dao;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.model.Employee;



public class EmployeeDAO {
	public static void selectEmployeesWhereNameStartsWithA() {
		try {
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			Query<Employee> query = session.createQuery("From employee e where e.ename LIKE  'A%'");
			ArrayList<Employee> allEmployees = (ArrayList<Employee>) query.getResultList();
			allEmployees.forEach(employee -> {
				System.out.println(employee.getEmpno()+"\t"+employee.getEname()+"\t"+employee.getDeptno()+"\t"+employee.getJob()+"\t"+employee.getSal());
			});
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void selectAll() {
		try {
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			@SuppressWarnings("unchecked")
			Query<Employee> query = session.createQuery("From employee");
			ArrayList<Employee> allEmployees = (ArrayList<Employee>) query.getResultList();
			System.out.println(allEmployees);

			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	public static void main(String[] args) {
		
//		selectAll();
//		selectEmployeesWhereNameStartsWithA();
//		selectEmployeesBySalaryCriteria();
//		findSecondMinimumAndSecondMaximumSalary();
		updateEmployeeSalary();
	}

	private static void findSecondMinimumAndSecondMaximumSalary() {
		
		try {
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			String hql = "SELECT MIN(E.sal) FROM employee E WHERE E.sal > (SELECT MIN(E2.sal) FROM employee E2)";
			Query query = session.createQuery(hql);
			int secondMinSalary =  (int) query.uniqueResult();
			System.out.println("Second Minimum Salary -> "+secondMinSalary);
			
			hql = "SELECT MAX(E.sal) FROM employee E WHERE E.sal < (SELECT MAX(E2.sal) FROM employee E2)";
			query = session.createQuery(hql);
			int secondMaxSalary = (int) query.uniqueResult();
			System.out.println("Second Maximum Salary -> "+secondMaxSalary);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	private static void selectEmployeesBySalaryCriteria() {
		// TODO Auto-generated method stub
		try {
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			Query<Employee> query = session.createQuery("From employee e where e.sal BETWEEN 5000 and 7000");
			ArrayList<Employee> allEmployees = (ArrayList<Employee>) query.getResultList();
			allEmployees.forEach(employee -> {
				System.out.println(employee.getEmpno()+"\t"+employee.getEname()+"\t"+employee.getDeptno()+"\t"+employee.getJob()+"\t"+employee.getSal());
			});
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	@Transactional
	public static void updateEmployeeSalary() {
		try {
			
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			  // Fetch the list of employees with the same salary
			String subquery = "SELECT E2.sal FROM employee E2 GROUP BY E2.sal HAVING COUNT(E2.sal) > 1";
			String hql = "FROM employee E WHERE E.sal IN ("+ subquery +")";
			Query query = session.createQuery(hql);
			List<Employee> employees = query.list();
		    // Update the salary of the employee
		    System.out.println("Before Updating Salary");
		    for (Employee employee : employees) {

		    	System.out.println(employee.getEname()+"\t"+employee.getSal());
		    }
		    
		    for (Employee employee : employees) {
		        employee.setSal(5000);
		        session.update(employee);
		    }
		    tx.commit();
		    
		    System.out.println("After Updating Salary");
		    
		    for (Employee employee : employees) {

		    	System.out.println(employee.getEname()+"\t"+employee.getSal());
		    }
		    
		    
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	  
	}

}
