package com.ggbs.hibernate.employee.service;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.ggbs.hibernate.employee.model.EmpName;
import com.ggbs.hibernate.employee.model.Employee;

public class EmployeeService {

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void main(String[] args) {

		System.out.println(" ******* Starts Hibernate Example ******* ");


		Employee employee = new Employee();
		employee.setEmpId(2l);
		EmpName empName = new EmpName();
		empName.setFirstName("Sumana");
		empName.setLastName("H");
		empName.setSurName("Bharath");
		employee.setEmpName(empName );
		employee.setDesignation("Kofax");


		StandardServiceRegistry standardServiceRegistry = new
				StandardServiceRegistryBuilder().configure().build();

		Metadata metaData = new
				MetadataSources(standardServiceRegistry).getMetadataBuilder().build();

		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();

		Session session = sessionFactory.openSession();
		//Session session = getHibernate5Session();
		//Session session = getHibernate4Session();

		//Create Transaction 
		Transaction transaction =session.beginTransaction();

		// Persist Data 
		//session.save(employee);
		
		//Fetch data
		Query query1 = session.createQuery("from Employee where empId=1");
		query1.setCacheable(true); 
		Employee employee1 = (Employee) query1.uniqueResult();
		//Employee employee1 = session.get(Employee.class, 1l);
		System.out.println("Employee Fetched :: " + employee1.toString());
		
		Employee employee2 = session.get(Employee.class, 2l);
		System.out.println("Employee Fetched :: " + employee2.toString());
		
		Employee employee3 = session.get(Employee.class, 2l);
		System.out.println("Employee Fetched :: " + employee3.toString());
		
		
		// Commit the transaction 
		transaction.commit();
		session.close();
		
		// Second Session
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		Query query2 = session2.createQuery("from Employee where empId=1");
		query2.setCacheable(true); 
		Employee employee4 = (Employee) query2.uniqueResult();
		//Employee employee4 = session2.get(Employee.class, 2l);
		System.out.println("Employee Fetched with session 2:: " + employee4.toString());
		session2.getTransaction().commit();
		session2.close();
		

		System.out.println("Hibernate 4 - Ends");

	}

	private static Session getHibernate5Session() {
		StandardServiceRegistry standardServiceRegistry = new
				StandardServiceRegistryBuilder().configure().build();

		Metadata metaData = new
				MetadataSources(standardServiceRegistry).getMetadataBuilder().build();

		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();

		Session session = sessionFactory.openSession();
		return session;
	}

	private static Session getHibernate4Session() {
		// Hibernate 4 way of building a Session
		System.out.println("Hibernate 4 - Starts "); 
		
		// 1. Create Configuraiton
		Configuration configuration = new
				Configuration().configure().addAnnotatedClass(Employee.class);

		//2. Create Standard Service Registry using Configuration
		StandardServiceRegistry serviceRegistry = new
				StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();

		//3. Create Session Factorty using Standard Serv registry 
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		//4. Create Session using Session factory 
		Session session = sessionFactory.openSession();
		return session;
	}


}
