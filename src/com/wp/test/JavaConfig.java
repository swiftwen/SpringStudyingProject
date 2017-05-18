package com.wp.test;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;



public class JavaConfig {
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory cf = new JedisConnectionFactory();
		cf.setHostName("localhost");
		cf.setPort(6379);
		return cf;
	}
	@Bean
	public RedisTemplate<Integer, String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<Integer, String> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
		
	}
	@Bean
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		ds.setPassword("123456");
		return ds;
	}
	/*@Bean(name="sessionFactory")
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setPackagesToScan(new String[]{"com.wp.bean"});
		//sfb.setMappingResources(new String[]{"/com/wp/bean/Student.hbm.xml"});
		Properties prop = new Properties();
		prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		prop.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
		sfb.setHibernateProperties(prop);
		return sfb;
	}*/
	@Bean
	public HibernateTransactionManager txManager(SessionFactory sessionFactory){
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactory);
		
		return tx;
	}
	/*@Bean
	public BeanPostProcessor persistenceTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}*/
	
	/*@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds){
	    JdbcTemplate template = new JdbcTemplate(ds);
	    return template;
	}*/
	/*@Bean(name="t")
	public Test test(){
		return new Test();
	}
	*/
/*	@Bean(name="aaa")
	@Primary
	public Disc abc(){
		return new Disc("my abc disc","abc");
	}*/
	
	/*@Bean(name="myDisc")
	public Disc getMyDisc(){
		return new Disc("my disc","wp");
	}*/
	
	
}
