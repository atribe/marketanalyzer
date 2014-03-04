package com.atomrockets.marketanalyzer.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class RootController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(AccountController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAllAccounts() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp
        
        mav.addObject("someText", "Listing all accounts!");
        mav.setViewName("index");
        return mav;
    }
}
