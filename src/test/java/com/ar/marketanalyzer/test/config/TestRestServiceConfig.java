package com.ar.marketanalyzer.test.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.SymbolService;

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
}
