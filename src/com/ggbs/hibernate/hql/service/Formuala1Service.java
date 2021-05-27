package com.ggbs.hibernate.hql.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.ggbs.hibernate.hql.model.Formula1;


public class Formuala1Service {

	public static void main(String[] args) {

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		System.out.println("*******************************************************");
		// Fetch using Get
		Formula1 formula = session.get(Formula1.class, 7);
		System.out.println("Get :: " + formula);
		System.out.println("*******************************************************");

		//Fetch Entrire List
		Query query = session.createQuery("from Formula1");
		query.list().stream().forEach(System.out::println);
		System.out.println("*******************************************************");

		// Fetch particular row
		Query query1 = session.createQuery("from Formula1 where carnumber=3");
		Formula1 car = (Formula1) query1.uniqueResult();
		System.out.println("Query Result : uniqueResult :: " + car);
		System.out.println("*******************************************************");

		//Fetch with where clause
		Query query2 = session.createQuery("from Formula1 where carNumber > 47");
		query2.list().stream().forEach(System.out::println);
		System.out.println("******************************************************");

		//Fetch with full query and where clause
		Query query3 = session.createQuery("select carNumber, raceDriver from Formula1 where carNumber > 47");
		List<Object[]> teamsInfo = query3.list();

		for(Object[] team : teamsInfo) {
			System.out.println(team[0] + " :: " + team[1]);
		}
		System.out.println("******************************************************");

		//Fetch with where clause
		int b = 48;
		Query query4 = session.createQuery("from Formula1 where carNumber > : b");
		query4.setParameter("b", b);
		query4.list().stream().forEach(System.out::println);
		System.out.println("******************************************************");

		//Fetch with SQL Query
		SQLQuery query5 = session.createSQLQuery("Select * from formula1 where carNumber >45");
		query5.addEntity(Formula1.class);
		query5.list().stream().forEach(System.out::println);
		System.out.println("******************************************************");

		//Fetch with SQL Query
		SQLQuery query6 = session.createSQLQuery("Select carNumber, raceDriver from formula1 where carNumber >45");
		query6.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List teams = query6.list();

		for(Object obj: teams) {
			Map map = (Map) obj;
			System.out.println("SQL ::" + map.get("carnumber") + " :: "+ map.get("racedriver"));
		}
		System.out.println("******************************************************");

		// get vs load
		Formula1 formula1 = session.get(Formula1.class, 10);
		System.out.println("Formula 1 :" + formula1);

		Formula1 formula2 = session.load(Formula1.class, 10);
		System.out.println("Formula 2 :" + formula2);

		session.getTransaction().commit();
		session.close();

	}
}
