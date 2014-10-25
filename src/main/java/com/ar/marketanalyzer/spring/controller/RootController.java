package com.ar.marketanalyzer.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.services.interfaces.AbstractModelServiceInterface;

@Controller
@RequestMapping("/")
public class RootController {
	/* Get actual class name to be printed on */
	private static final Logger logger = LogManager.getLogger(RootController.class);
	
	@Autowired
	AbstractModelServiceInterface modelService;
	
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView rootView() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp
        
        List<AbstractModel> modelList = modelService.getAll();
        
        logger.info(modelList);
        
        mav.addObject("modelList", modelList);
        
        mav.setViewName("index");
        return mav;
    }
}
