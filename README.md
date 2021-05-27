# Hibernate

## Dependencies
Hibernate needs certain dependencies. Depending on the version download the stable dependencies from here -
[Hibernate](https://hibernate.org/orm/releases/)

## Creation of Hibernate Session to interact with DB
1. Hibernate 4
``` java
// 1. Create Configuraiton
Configuration configuration = new Configuration().configure().addAnnotatedClass(Employee.class);

//2. Create Standard Service Registry using Configuration
StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder.applySettings(configuration.getProperties()).build();

//3. Create Session Factorty using Standard Serv registry 
SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//4. Create Session using Session factory 
Session session = sessionFactory.openSession();
```

2. Hibernate 5
``` java
// 1. Create Standard Service Registry
StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
// 2. Create Metadata Object using SSR
Metadata metaData = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
// 3.Create Session Factory
SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
// 4. Create Session
Session session = sessionFactory.openSession();
```

## Configuration XML
We need to have the hibernate.cfg.xml to have all configuration related items like DB connection, cache etc
```bash

<hibernate-configuration>

    <session-factory>

        <!-- Database connection settings -->
        <property name="connection.driver_class">org.postgresql.Driver</property>
        <property name="connection.url">jdbc:postgresql://localhost:5432/hibernate</property>
        <property name="connection.username">postgres</property>
        <property name="connection.password">admin</property>

        <!-- JDBC connection pool (use the built-in) -->
        <property name="connection.pool_size">1</property>

        <!-- SQL dialect -->
        <property name="dialect">org.hibernate.dialect.PostgreSQLDialect</property>

        <!-- Echo all executed SQL to stdout -->
        <property name="show_sql">true</property>
        <property name="hbm2ddl.auto">update</property>

        <!-- Second level Caching -->
        <property name="hibernate.cache.use_second_level_cache">true</property>
        <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.internal.EhcacheRegionFactory</property>
        <property name="hibernate.cache.use_query_cache">true</property>
         
    </session-factory>

</hibernate-configuration>

```
 
### Fetch Type:
When we have nested relation and we want to fetch even the nested data/objects  we have to specify the FetchType. Default is Lazy.

```java
@Entity
public class Student {

	@Id
	private int rollNum;
	private String name;
	private String dept;
	
	// If we use mappedBy and provide the corresponding class's variable name then new table will not be created
	// while fetching Student - Hib will fetch only student class info
	// If you need Collection of laptops as well then we need to specify Fetch Type as EAGER
	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
	private List<Laptop> laptop = new ArrayList<Laptop>();
```

### @Embeddable
When we have to create a Entity(Table) and span it across multiple classes to improve readability and accessibility - we can make use of @Embeddable in child classes.
```java
@Entity
@Table(name="Employee")
public class Employee {
        @Id
	private Long empId;
	private EmpName empName;
	private String designation;
```
```java
@Embeddable
public class EmpName {

	private String firstName;
	private String lastName;
	private String surName;
```

### Relation Mapping

1. @OneToOne
2. @ManyToOne
3. @OneToMany
4. @ManyToMany


### Caching 
We have 2 levels of caching, 
1. First Level of Caching
   - works with same session object

2. Second level of Caching
   - Need configuration in hibernate.cfg.xml 
   - Need annotation in Entity class
   - supports user query cache

``` xml
<!-- 1. Second level Caching -->
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.internal.EhcacheRegionFactory</property>
<property name="hibernate.cache.use_query_cache">true</property>
```

``` java
// 2. Add @Cacheable & @Cache annotations
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class EntityName {
```

```java
//3. Enable setCacheable to true for Query Object
Query query1 = session.createQuery("from Employee where empId=1");
query1.setCacheable(true); 
Employee employee1 = (Employee) query1.uniqueResult();
```


### HQL 
```java
// Here Formula1 is an Entity class used to fetch data from DB

// Fetch using Get
Formula1 formula = session.get(Formula1.class, 7);
System.out.println("Get :: " + formula);


//Fetch Entire data 
Query query = session.createQuery("from Formula1");
query.list().stream().forEach(System.out::println);

// Fetch particular row - using uniqueResult() 
Query query1 = session.createQuery("from Formula1 where carnumber=3");
Formula1 car = (Formula1) query1.uniqueResult();
System.out.println("Query Result : uniqueResult :: " + car);

//Fetch with column names in query and where clause
Query query3 = session.createQuery("select carNumber, raceDriver from Formula1 where carNumber > 47");
List<Object[]> teamsInfo = query3.list();

for(Object[] team : teamsInfo) {
	System.out.println(team[0] + " :: " + team[1]);
}

//Fetch with where clause with paramterized
int b = 48;
Query query4 = session.createQuery("from Formula1 where carNumber > : b");
query4.setParameter("b", b);
query4.list().stream().forEach(System.out::println);

//Fetch with SQL Query
SQLQuery query5 = session.createSQLQuery("Select * from formula1 where carNumber >45");
query5.addEntity(Formula1.class);
query5.list().stream().forEach(System.out::println);

//Fetch with SQL Query with column names
SQLQuery query6 = session.createSQLQuery("Select carNumber, raceDriver from formula1 where carNumber >45");
query6.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
List teams = query6.list();

for(Object obj: teams) {
	Map map = (Map) obj;
	System.out.println("SQL ::" + map.get("carnumber") + " :: "+ map.get("racedriver"));
}
```

### Get vs Load
```java
// Fetched the object as soon as we trigger get()
// If not found returns null
Formula1 formula1 = session.get(Formula1.class, 10);
System.out.println("Formula 1 :" + formula1);

// Fetches the proxy object when load() is called
// Query is fired when we make use of data
// Throwa ObjectNotFound exception if the required data is not present
Formula1 formula2 = session.load(Formula1.class, 10);
System.out.println("Formula 2 :" + formula2);

```
