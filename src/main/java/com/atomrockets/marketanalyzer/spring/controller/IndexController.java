package com.atomrockets.marketanalyzer.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
}
