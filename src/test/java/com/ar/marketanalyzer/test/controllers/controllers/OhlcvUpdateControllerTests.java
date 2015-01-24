package com.ar.marketanalyzer.test.controllers.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.SymbolService;


public class OhlcvUpdateControllerTests extends AbstractRestControllerTest{

	@Mock
	SymbolService symbolServiceMock;
	
	@Mock
	SecurityOhlcvService ohlcvServiceMock;
	
	@Before
	public void setup() {
		Mockito.reset(symbolServiceMock);
	}
	
	@Test
	public void firstTest() throws Exception {
		Symbol testSymbol = getTestSymbolwithId();
		when(symbolServiceMock.findById(SYMBOL_ID)).thenReturn(testSymbol);
		when(ohlcvServiceMock.updateOhlcvFromYahoo(testSymbol)).thenReturn(5);
		
		mockMvc.perform(get("/REST/ohlcv/update/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.count", is(5)))
		.andExpect(jsonPath("$.total", is(15)));
	}

	@Test
	public void getOhlcvFromYahooTest() throws Exception {
		mockMvc.perform(get("/REST/ohlcv/updatetest/1"))
		.andExpect(status().isOk());
	}
}
