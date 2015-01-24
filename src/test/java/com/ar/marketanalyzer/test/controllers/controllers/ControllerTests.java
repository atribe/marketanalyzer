package com.ar.marketanalyzer.test.controllers.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class ControllerTests extends AbstractTestController{

	@Test
	public void firstTest() throws Exception {
		mockMvc.perform(get("/stockmanager"))
		.andExpect(status().isOk());
	}

	@Test
	public void getOhlcvFromYahooTest() throws Exception {
		mockMvc.perform(get("/REST/ohlcv/update/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.count", is(0)))
		.andExpect(jsonPath("$.total", is(15)));
	}
}
