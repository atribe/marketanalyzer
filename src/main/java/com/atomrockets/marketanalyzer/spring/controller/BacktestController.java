package com.atomrockets.marketanalyzer.spring.controller;

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
        	
        	/*
        	 * Setting up the results of the baseline and the current model.
        	 * However, it is super clunky and not good or even robust
        	 */
    		BacktestResult b1 = backtestService.getBaseline("^IXIC");
    		double initialInvestment = 10000;
    		mav.addObject("result1", b1);
    		mav.addObject("initialInvestment1", initialInvestment);
    		mav.addObject("finalValue1", initialInvestment*(1+b1.getTotalPercentReturn()));
    		
    		BacktestResult b2 = backtestService.getBaseline("^GSPC");
    		initialInvestment = 10000;
    		mav.addObject("result2", b2);
    		mav.addObject("initialInvestment2", initialInvestment);
    		mav.addObject("finalValue2", initialInvestment*(1+b2.getTotalPercentReturn()));
    		
    		BacktestResult b3 = backtestService.getBaseline("^SML");
    		initialInvestment = 10000;
    		mav.addObject("result3", b3);
    		mav.addObject("initialInvestment3", initialInvestment);
    		mav.addObject("finalValue3", initialInvestment*(1+b3.getTotalPercentReturn()));
    		
    		BacktestResult c1 = backtestService.getCurrent("^IXIC");
    		initialInvestment = 10000;
    		mav.addObject("current1", c1);
    		mav.addObject("initialInvestmentc1", initialInvestment);
    		mav.addObject("finalValuec1", initialInvestment*(1+c1.getTotalPercentReturn()));
    		
    		BacktestResult c2 = backtestService.getCurrent("^GSPC");
    		initialInvestment = 10000;
    		mav.addObject("current2", c2);
    		mav.addObject("initialInvestmentc2", initialInvestment);
    		mav.addObject("finalValuec2", initialInvestment*(1+c2.getTotalPercentReturn()));
    		
    		BacktestResult c3 = backtestService.getCurrent("^SML");
    		initialInvestment = 10000;
    		mav.addObject("current3", c3);
    		mav.addObject("initialInvestmentc3", initialInvestment);
    		mav.addObject("finalValuec3", initialInvestment*(1+c3.getTotalPercentReturn()));
    		
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
    	mav.setViewName("backtestResults");
    	mav.addObject("backtestModel", bt);
    	
    	return mav;
    }
}
