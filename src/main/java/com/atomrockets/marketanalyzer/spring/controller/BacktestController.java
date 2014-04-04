package com.atomrockets.marketanalyzer.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atomrockets.marketanalyzer.models.BacktestModel;
import com.atomrockets.marketanalyzer.services.BacktestService;

@Controller
public class BacktestController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BacktestController.class.getName());

	@RequestMapping(value="/backtest", method = RequestMethod.GET)
    public ModelAndView backtestPage(){
		
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp
        mav.setViewName("backtest");
        
        //Creating a BacktestService instance
        BacktestService BtS = new BacktestService();
        //Getting the list of indexs
        List<String> indexList = BtS.getIndexList();
        mav.addObject("indexList", indexList);
        
        BacktestModel b = new BacktestModel();
        b.setdDayWindow(55);
        //adding a backtestModel object to the mav
        mav.addObject("backtestModel", b);
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
    private ModelAndView processBacktest(@ModelAttribute BacktestModel bt) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("backtestResults");
    	mav.addObject("backtestModel", bt);
    	
    	return mav;
    }
}
