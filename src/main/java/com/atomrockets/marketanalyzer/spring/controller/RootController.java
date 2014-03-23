package com.atomrockets.marketanalyzer.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atomrockets.marketanalyzer.models.IndexCalcsModel;
import com.atomrockets.marketanalyzer.services.IndexCalcsService;

@Controller
@RequestMapping("/")
public class RootController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(RootController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAllAccounts() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp
        
        //Getting the d-dates from the database
        IndexCalcsService  indexCalcsService = new IndexCalcsService ();
        List<IndexCalcsModel> dDayList = indexCalcsService .getLatestDDays();
        mav.addObject("dDayList", dDayList);
        
        mav.addObject("someText", "Listing all accounts!");
        mav.setViewName("index");
        return mav;
    }
}
