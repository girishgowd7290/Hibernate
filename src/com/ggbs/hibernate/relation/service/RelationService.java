package com.ggbs.hibernate.relation.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.ggbs.hibernate.relation.model.Laptop;
import com.ggbs.hibernate.relation.model.Student;

public class RelationService {

	public static void main(String[] args) {

		Laptop laptop1 = new Laptop();
		laptop1.setLaptopId(1);
		laptop1.setLaptopMake("Dell");


		Student student1 = new Student();
		student1.setRollNum(101);
		student1.setName("Girish");
		student1.setDept("EEE");
		student1.getLaptop().add(laptop1);

		
		laptop1.setStudent(student1);
		
		Session session = getHibernate5Session();
		Transaction transaction = session.beginTransaction();

		session.save(student1);
		session.save(laptop1);
		
		transaction.commit();

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

}
