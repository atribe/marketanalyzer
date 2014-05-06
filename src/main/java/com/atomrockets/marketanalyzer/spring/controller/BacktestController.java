package com.atomrockets.marketanalyzer.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.services.BacktestService;
import com.atomrockets.marketanalyzer.threads.marketAnalyzerListener;

@Controller
public class BacktestController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BacktestController.class.getName());
	private final String viewName = "backtest";

	@RequestMapping(value="/backtest/{symbol}", method = RequestMethod.GET)
    public String backtestPage(ModelMap m, @PathVariable String symbol) {
        if(symbol == "IXIC" || symbol == "GSPC" || symbol == "SML") {
        	symbol = "^" + symbol;
        }
        if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");

        	//Creating a backtest service
        	BacktestService backtestService = new BacktestService();
        	
        	BacktestResult b = backtestService.getCurrent(symbol);
        	m.addAttribute("backtestObject", b);
        	/*
        	 * Setting up the results of the baseline and the current model.
        	 * However, it is super clunky and not good or even robust
        	 */
        	List<BacktestResult> baselineList = new ArrayList<BacktestResult>();
        	baselineList.add(backtestService.getBaseline("^IXIC"));
        	baselineList.add(backtestService.getBaseline("^GSPC"));
        	baselineList.add(backtestService.getBaseline("^SML"));
        	m.addAttribute("baselineList", baselineList);
        	
        	List<BacktestResult> currentBacktestList = new ArrayList<BacktestResult>();
        	currentBacktestList.add(backtestService.getCurrent("^IXIC"));
        	currentBacktestList.add(backtestService.getCurrent("^GSPC"));
        	currentBacktestList.add(backtestService.getCurrent("^SML"));
        	m.addAttribute("currentBacktestList", currentBacktestList);
        	
    		/*
    		 * Filling the fields for the next run
    		 */
    		//Getting the list of indexs
            List<String> indexList = backtestService.getIndexList();
            m.addAttribute("indexList", indexList);
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
        return viewName;
    }
    
	@RequestMapping(value = "/backtest", method = RequestMethod.GET)
	public String backtestPage(ModelMap m) {
		
		String defaultSymbol = "^IXIC";
		return "redirect:" + viewName + "/" + defaultSymbol;
	}
	@RequestMapping(value = "/backtest/", method = RequestMethod.GET)
	public String backtestPageWithForwardSlash(ModelMap m) {
		
		String defaultSymbol = "^IXIC";
		return "redirect:" + defaultSymbol;
	}
	
	@RequestMapping(value = "/backtest/", method = RequestMethod.POST)
    private String processBacktest(ModelMap m, @ModelAttribute BacktestResult bt) {
		BacktestService bs = new BacktestService();
    	
    	bs.updateBacktest(bt);
    	
    	return "redirect:" + bt.getSymbol();
    }
}
