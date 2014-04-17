package com.atomrockets.marketanalyzer.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
import com.atomrockets.marketanalyzer.services.BacktestService;
import com.atomrockets.marketanalyzer.services.IndexCalcsService;
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
        
        if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");
	        //Getting the d-dates from the database
	        
        	/* D day table stuff for debugging
        	IndexCalcsService  indexCalcsService = new IndexCalcsService();
	        if(indexCalcsService.isM_connectionAlive()) {
	        	List<IndexOHLCVCalcs> dDayList = indexCalcsService.getLatestDDays("^IXIC");
	        	mav.addObject("dDayList", dDayList);
	        }
	        log.debug("");
	        */
        	
        	BacktestService backtestService = new BacktestService();
        	if(backtestService.isM_connectionAlive()) {
        		
        	}
        } else {
        	log.debug("Db Init Thread is running. Skipping D-day info from the DB");
        	//Maybe do something here. Like a boolean so that the whole table is skipped and replaced with something else in the jsp.
        	
        }
        
        mav.addObject("someText", "Listing all accounts!");
        mav.setViewName("index");
        return mav;
    }
}
