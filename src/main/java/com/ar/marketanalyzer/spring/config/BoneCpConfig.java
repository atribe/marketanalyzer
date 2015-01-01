package com.ar.marketanalyzer.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
public class BoneCpConfig {

    @Value("${bonecp.url}")
    private String jdbcUrl;
    
    @Value("${bonecp.dbname}")
    private String jdbcDbname;
    
    @Value("${bonecp.prod.host}")
    private String prodHost;

    @Value("${bonecp.dev.username}")
    private String jdbcDevUsername;

    @Value("${bonecp.dev.password}")
    private String jdbcDevPassword;
    
    @Value("${bonecp.prod.username}")
    private String jdbcProdUsername;

    @Value("${bonecp.prod.password}")
    private String jdbcProdPassword;

    @Value("${bonecp.driverClass}")
    private String driverClass;

    @Value("${bonecp.idleMaxAgeInMinutes}")
    private Integer idleMaxAgeInMinutes;

    @Value("${bonecp.idleConnectionTestPeriodInMinutes}")
    private Integer idleConnectionTestPeriodInMinutes;

    @Value("${bonecp.maxConnectionsPerPartition}")
    private Integer maxConnectionsPerPartition;

    @Value("${bonecp.minConnectionsPerPartition}")
    private Integer minConnectionsPerPartition;

    @Value("${bonecp.partitionCount}")
    private Integer partitionCount;

    @Value("${bonecp.acquireIncrement}")
    private Integer acquireIncrement;

    @Value("${bonecp.statementsCacheSize}")
    private Integer statementsCacheSize;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(driverClass);
        // ************For Open Shift Account************	  
     	if(System.getenv("OPENSHIFT_APP_NAME")!=null) {	
     		String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
     		String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");

     		String url = "jdbc:mysql://"+host+":"+port+"/"+jdbcDbname;
     		dataSource.setJdbcUrl(url);
	        dataSource.setUsername(jdbcProdUsername);
	        dataSource.setPassword(jdbcProdPassword);
     	} else {
     		dataSource.setJdbcUrl(jdbcUrl+jdbcDbname);
	        dataSource.setUsername(jdbcDevUsername);
	        dataSource.setPassword(jdbcDevPassword);
     	}
        dataSource.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
        dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
        dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
        dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
        dataSource.setPartitionCount(partitionCount);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setStatementsCacheSize(statementsCacheSize);
        return dataSource;
    }

}
