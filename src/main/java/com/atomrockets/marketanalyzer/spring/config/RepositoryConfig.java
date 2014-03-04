package com.atomrockets.marketanalyzer.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

import com.atomrockets.marketanalyzer.spring.init.PropertiesLoader;

@Configuration
public class RepositoryConfig {

	private static final PropertiesLoader propertiesLoader = new PropertiesLoader();
	private static final String SPRING_PROPERTIES_FILE_NAME = "spring.properties";
	
	@Bean
	public DataSource getDataSource() {
		Properties prop = propertiesLoader.load(SPRING_PROPERTIES_FILE_NAME);
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		
		ds.setDriverClassName(prop.getProperty("db.driver"));
		
		String host = prop.getProperty("db.host");
		String port = prop.getProperty("db.port");
		String DBName = prop.getProperty("db.dbname");
		String dbURL = "jdbc:mysql://"+host+":"+port+"/" + DBName;
		
		ds.setUrl(dbURL);
		
		ds.setUsername(prop.getProperty("db.username"));
		ds.setPassword(prop.getProperty("db.password"));
		
		return ds;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}
	
	@Bean
	@Autowired
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}
	
	@Bean
	public Properties getHibernateProperties() {
		Properties prop = propertiesLoader.load(SPRING_PROPERTIES_FILE_NAME);
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", prop.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", prop.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", prop.getProperty("hibernate.hbm2ddl.auto"));
        
        return properties;
	}
	
	@Bean
	public AnnotationSessionFactoryBean getSessionFactory() {
		Properties prop = propertiesLoader.load(SPRING_PROPERTIES_FILE_NAME);
		
		AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
		asfb.setDataSource(getDataSource());
		asfb.setHibernateProperties(getHibernateProperties());
		asfb.setPackagesToScan(new String[]{prop.getProperty("entitymanager.packages.to.scan")});
		return asfb;
	}
}
