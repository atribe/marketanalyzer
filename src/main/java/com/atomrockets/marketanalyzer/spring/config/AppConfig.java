package com.atomrockets.marketanalyzer.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.atomrockets.marketanalyzer.beans.MyAsyncBean;

@Configuration
@ComponentScan(basePackages = "com.atomrockets.marketanalyzer")
@EnableScheduling
@EnableAsync
public class AppConfig {
	/*
	 * The main AppConfig configuration class doesn’t do anything but hits Spring
	 * on where to look for its components through @ComponentScan annotation.
	 */
	@Bean
    public MyAsyncBean asyncBean() {
        return new MyAsyncBean();
    }
}
