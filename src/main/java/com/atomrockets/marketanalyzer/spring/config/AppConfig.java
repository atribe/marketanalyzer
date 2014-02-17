package com.atomrockets.marketanalyzer.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "com.atomrockets.marketanalyzer")
public class AppConfig {
	/*
	 * The main AppConfig configuration class doesn’t do anything but hits Spring
	 * on where to look for its components through @ComponentScan annotation.
	 */
}
