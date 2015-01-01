package com.ar.marketanalyzer.spring.config;

import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan(basePackages = "com.ar.marketanalyzer")
@EnableAsync
@EnableScheduling
@PropertySource("classpath:common.properties")
public class AppConfig {
	/*
	 * The main AppConfig configuration class doesn�t do anything but hits Spring
	 * on where to look for its components through @ComponentScan annotation.
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
}
