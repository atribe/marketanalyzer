package com.atomrockets.marketanalyzer.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.atomrockets.marketanalyzer.threads.MarketsDBInitRunnable;

@Configuration
@ComponentScan(basePackages = "com.atomrockets.marketanalyzer")
@EnableAsync
@EnableScheduling
public class AppConfig {
	/*
	 * The main AppConfig configuration class doesn’t do anything but hits Spring
	 * on where to look for its components through @ComponentScan annotation.
	 */
	@Bean
	public MarketsDBInitRunnable maBean() {
		MarketsDBInitRunnable maBean = new MarketsDBInitRunnable();
		return maBean;
	}
}
