package com.ar.marketanalyzer.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories({"com.ar.marketanalyzer.ibd50.repositories",
						"com.ar.marketanalyzer.core.securities.repo",
						"com.ar.marketanalyzer.backtest.repo"})
@PropertySource({ "classpath:common.properties" })
public class PersistenceConfig {

	@Value("${entitymanager.packages.to.scan}")
	private String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN;
	@Value("${hibernate.dialect}")
	private String PROPERTY_NAME_HIBERNATE_DIALECT;
	@Value("${hibernate.show_sql}")
	private String PROPERTY_NAME_HIBERNATE_SHOW_SQL;
	@Value("${hibernate.hbm2ddl.auto}")
	private String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
		
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(hibProperties());

		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",	PROPERTY_NAME_HIBERNATE_DIALECT);
		properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
		properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
