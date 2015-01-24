package com.ar.marketanalyzer.test.controllers.config;

import javax.annotation.Resource;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ar.marketanalyzer.core.securities.repo.SecurityOhlcvRepo;
import com.ar.marketanalyzer.core.securities.repo.SymbolRepo;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.SymbolService;
import com.ar.marketanalyzer.core.securities.services.YahooOhlcvService;

@Configuration
public class TestRestServiceConfig {
	
	@Bean
	public SymbolService symbolServiceMock() {
		return Mockito.mock(SymbolService.class);
	}
	
	@Bean
	public SecurityOhlcvService ohlcvServiceMock() {
		return Mockito.mock(SecurityOhlcvService.class);
	}
	
	@Bean
	public SymbolRepo symbolRepoMock() {
		return Mockito.mock(SymbolRepo.class);
	}
	@Bean
	public SecurityOhlcvRepo secRepoMock() {
		return Mockito.mock(SecurityOhlcvRepo.class);
	}
	
	@Bean
	public YahooOhlcvService yahooOhlcvServiceMock() {
		return Mockito.mock(YahooOhlcvService.class);
	}
}
