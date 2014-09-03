package com.ar.marketanalyzer.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ar.marketanalyzer.core.threads.IndexBacktestInitRunnable;

@Configuration
@ComponentScan(basePackages = "com.ar.marketanalyzer")
@EnableAsync
@EnableScheduling
public class AppConfig {
	/*
	 * The main AppConfig configuration class doesn’t do anything but hits Spring
	 * on where to look for its components through @ComponentScan annotation.
	 */
	@Bean
	public IndexBacktestInitRunnable maBean() {
		IndexBacktestInitRunnable maBean = new IndexBacktestInitRunnable();
		return maBean;
	}
}
