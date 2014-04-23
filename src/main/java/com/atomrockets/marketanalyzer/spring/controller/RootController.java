package com.atomrockets.marketanalyzer.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.services.BacktestService;
import com.atomrockets.marketanalyzer.threads.marketAnalyzerListener;

@Controller
@RequestMapping("/")
public class RootController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(RootController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView rootView() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp
        
        mav.setViewName("index");
        return mav;
    }
}
