package com.atomrockets.marketanalyzer.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atomrockets.marketanalyzer.persistence.model.YahooIndexData;
import com.atomrockets.marketanalyzer.persistence.service.common.AbstractService;
import com.atomrockets.marketanalyzer.persistence.service.impl.YahooIndexDataService;


@Controller
@RequestMapping("/")
public class RootController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(AccountController.class.getName());
	
	@Autowired
	@Qualifier(value = "YahooIndexDataService")
	private AbstractService yahooIndexDataService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAllAccounts() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp
        
        YahooIndexData a = new YahooIndexData("^IXIC", "1-3-83", (float)55.30, (float)57.55, (float)45.34, (float)50.30, 5532343, (float)50.33);
		yahooIndexDataService.create(a);
        
        mav.addObject("someText", "Listing all accounts!");
        mav.setViewName("index");
        return mav;
    }
}
