package com.ar.marketanalyzer.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ar.marketanalyzer.core.threads.marketAnalyzerListener;

@Controller
public class BacktestController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BacktestController.class.getName());
	private final String BASEURL = "backtest";

	
	/*
	 * base backtest page controller
	 * Renders a summary of all indices.
	 * Displays charts for each as well as basic return and date range info 
	 */
	@RequestMapping(value = {"/" + BASEURL, "/" + BASEURL + "/"}, method = RequestMethod.GET)
	public String backtestPage(ModelMap m) {
		
		if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");

//        	//Creating a backtest service
//        	BacktestService backtestService = new BacktestService();
//			/*
//	    	 * Setting up the results of the baseline and the current model.
//	    	 * However, it is super clunky and not good or even robust
//	    	 */
//	    	List<BacktestBean> baselineList = new ArrayList<BacktestBean>();
//	    	baselineList.add(backtestService.getBaseline("^IXIC"));
//	    	baselineList.add(backtestService.getBaseline("^GSPC"));
//	    	baselineList.add(backtestService.getBaseline("^SML"));
//	    	m.addAttribute("baselineList", baselineList);
//	    	
//	    	List<BacktestBean> backtests = new ArrayList<BacktestBean>();
//	    	backtests.add(backtestService.getCurrent("^IXIC"));
//	    	backtests.add(backtestService.getCurrent("^GSPC"));
//	    	backtests.add(backtestService.getCurrent("^SML"));
//	    	m.addAttribute("backtests", backtests);
		} else {
        	log.debug("Db Init Thread is running. Skipping D-day info from the DB");
        	//Maybe do something here. Like a boolean so that the whole table is skipped and replaced with something else in the jsp.
        }
		
		return "backtest";
	}
	
	
	@RequestMapping(value="/" + BASEURL + "/{symbol}", method = RequestMethod.GET)
    public String backtestPage(ModelMap m, @PathVariable String symbol) {
        if(symbol.equals("IXIC") || symbol.equals("GSPC") || symbol.equals("SML") ) {
        	symbol = "^" + symbol;
        }
        if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");

        	//Creating a backtest service
//        	BacktestService backtestService = new BacktestService();
//        	/*
//        	 * Setting up the results of the baseline and the current model.
//        	 * However, it is super clunky and not good or even robust
//        	 */
//        	m.addAttribute("baseline", backtestService.getBaseline(symbol));
//        	
//        	m.addAttribute("currentBacktest", backtestService.getCurrent(symbol));
        	
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
        return "backtestIndex";
    }
	
	/*This is the updating method, this will have to change
	@RequestMapping(value = "/backtest/", method = RequestMethod.POST)
    private String processBacktest(ModelMap m, @ModelAttribute BacktestResult bt) {
		BacktestService bs = new BacktestService();
    	
    	bs.updateBacktest(bt);
    	
    	return "redirect:" + bt.getSymbol();
    }
    */
}
