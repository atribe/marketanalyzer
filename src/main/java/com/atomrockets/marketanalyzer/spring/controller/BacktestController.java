package com.atomrockets.marketanalyzer.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.services.BacktestService;
import com.atomrockets.marketanalyzer.threads.marketAnalyzerListener;

@Controller
public class BacktestController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BacktestController.class.getName());

	@RequestMapping(value="/backtest", method = RequestMethod.GET)
    public ModelAndView backtestPage(){
		
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp
        mav.setViewName("backtest");
        
        if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");

        	//Creating a backtest service
        	BacktestService backtestService = new BacktestService();
        	
        	BacktestResult b = backtestService.getCurrent("^IXIC");
        	mav.addObject("backtestObject", b);
        	/*
        	 * Setting up the results of the baseline and the current model.
        	 * However, it is super clunky and not good or even robust
        	 */
        	List<BacktestResult> baselineList = new ArrayList<BacktestResult>();
        	baselineList.add(backtestService.getBaseline("^IXIC"));
        	baselineList.add(backtestService.getBaseline("^GSPC"));
        	baselineList.add(backtestService.getBaseline("^SML"));
        	mav.addObject("baselineList", baselineList);
        	
        	List<BacktestResult> currentBacktestList = new ArrayList<BacktestResult>();
        	currentBacktestList.add(backtestService.getCurrent("^IXIC"));
        	currentBacktestList.add(backtestService.getCurrent("^GSPC"));
        	currentBacktestList.add(backtestService.getCurrent("^SML"));
        	mav.addObject("currentBacktestList", currentBacktestList);
        	
    		/*
    		 * Filling the fields for the next run
    		 */
    		//Getting the list of indexs
            List<String> indexList = backtestService.getIndexList();
            mav.addObject("indexList", indexList);
        } else {
        	log.debug("Db Init Thread is running. Skipping D-day info from the DB");
        	//Maybe do something here. Like a boolean so that the whole table is skipped and replaced with something else in the jsp.
        	
        }
    
        
        /*
         * 1. Select Index via Radio Button
         * 2. View Current Parameters and previous results
         * 3. Changed Parameters if desired
         * 4. Run Model
         * 5. View Results
         */
        return mav;
    }
    
    @RequestMapping(value="/backtestResults")
    private ModelAndView processBacktest(@ModelAttribute BacktestResult bt) {
    	ModelAndView mav = new ModelAndView();
    	
    	BacktestService bs = new BacktestService();
    	
    	bs.runIndexModel(bt);
    	
    	mav.setViewName("backtestResults");
    	mav.addObject("backtestModel", bt);
    	
    	return mav;
    }
}
