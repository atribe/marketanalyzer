package com.atomrockets.marketanalyzer.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomrockets.marketanalyzer.spring.init.PropertiesLoader;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	
	private static final PropertiesLoader propertiesLoader = new PropertiesLoader();
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.atomrockets.marketanalyzer.spring.persistence.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}
	

	@Bean
	public DataSource restDataSource() {
		Properties prop = propertiesLoader.loadActivePropertiesFile();
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(prop.getProperty("db.driver"));
		
		String host, port;
		
		if(System.getenv("OPENSHIFT_APP_NAME")!=null) {	
			host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");//$OPENSHIFT_MYSQL_DB_HOST is a OpenShift system variable
			port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");//$OPENSHIFT_MYSQL_DB_PORT is also an OpenShift variable
		}else{
			// ************For Local Account************
			host = prop.getProperty("db.host");
			port = prop.getProperty("db.port");
		}
		
		String DBName = prop.getProperty("db.dbname");
		String dbURL = "jdbc:mysql://"+host+":"+port+"/" + DBName;
		dataSource.setUrl(dbURL);
		dataSource.setUsername(prop.getProperty("db.username"));
		dataSource.setPassword(prop.getProperty("db.password"));
		
		return dataSource;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		
		return txManager;
	}
		
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	   
	   
	private Properties hibernateProperties() {
		Properties prop = propertiesLoader.loadActivePropertiesFile();
		
		Properties hibernateProps = new Properties();
		hibernateProps.setProperty("hibernate.hbm2ddl.auto", prop.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProps.setProperty("hibernate.dialect", prop.getProperty("hibernate.dialect"));
		hibernateProps.setProperty("hibernate.globally_quoted_identifiers", "true");

		return hibernateProps;
	}
}
