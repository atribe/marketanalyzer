package com.ar.marketanalyzer.test.controllers.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;


public class ControllerTests extends AbstractTestController{

	@Test
	public void firstTest() throws Exception {
		mockMvc.perform(get("/stockmanager"))
		.andExpect(status().isOk());
	}

	@Test
	public void getOhlcvFromYahooTest() throws Exception {
		mockMvc.perform(get("/REST/ohlcv/updatetest/1"))
		.andExpect(status().isOk());
	}
}
