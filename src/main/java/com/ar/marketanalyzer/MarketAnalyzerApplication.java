package com.ar.marketanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MarketAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketAnalyzerApplication.class, args);
	}

}
