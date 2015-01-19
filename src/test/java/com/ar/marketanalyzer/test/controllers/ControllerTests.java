package com.ar.marketanalyzer.test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ar.marketanalyzer.spring.config.AppConfig;
import com.ar.marketanalyzer.spring.config.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class, AppConfig.class})
@WebAppConfiguration
public class ControllerTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		//We have to reset our mock between tests because the mock objects
		//are managed by the Spring container. If we would not reset them,
		//stubbing and verified behavior would "leak" from one test to another.
		//Mockito.reset(todoServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

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
