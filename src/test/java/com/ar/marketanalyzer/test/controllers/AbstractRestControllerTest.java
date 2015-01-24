package com.ar.marketanalyzer.test.controllers;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.spring.config.AppConfig;
import com.ar.marketanalyzer.spring.config.WebMvcConfig;
import com.ar.marketanalyzer.test.config.TestRestServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class, AppConfig.class, TestRestServiceConfig.class})
@WebAppConfiguration
public class AbstractRestControllerTest {

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		//We have to reset our mock between tests because the mock objects
		//are managed by the Spring container. If we would not reset them,
		//stubbing and verified behavior would "leak" from one test to another.

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	protected static final String SYMBOL_SYMBOL = "AAPL";
	protected static final String SYMBOL_NAME = "Apple Inc";
	protected static final String SYMBOL_TYPE = "stock";
	protected static final int SYMBOL_ID = 333;
	
	protected Symbol getTestSymbolwithNoId() {
		Symbol symbol = new Symbol(SYMBOL_SYMBOL, SYMBOL_NAME, SYMBOL_TYPE);
		return symbol;
	}
	
	protected Symbol getTestSymbolwithId() {
		Symbol symbol = new Symbol(SYMBOL_SYMBOL, SYMBOL_NAME, SYMBOL_TYPE);
		symbol.setId(SYMBOL_ID);
		return symbol;
	}
}
