package com.ar.marketanalyzer.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Controller
@RequestMapping("/")
public class RootController {
	/* Get actual class name to be printed on */
	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String rootView(Model model) throws Exception {

        log.trace("index.jsp has been served");
        return "index";
    }
    
    @RequestMapping(value = "/stocksummary", method = RequestMethod.GET)
    public String stocksummary(Model model) {
    	model.addAttribute("symbols", symbolService.findAll());
    	
        log.trace("symbolmanager.jsp has been served");
        return "symbolsummary";
    }
    
    @RequestMapping(value = "/modelsummary", method = RequestMethod.GET)
    public String modelsummary(Model model) {
    	model.addAllAttributes(symbolService.findAll());
    	
        log.trace("symbolmanager.jsp has been served");
        return "modelsummary";
    }
    
    @RequestMapping(value = "/modelmanager", method = RequestMethod.GET)
    public String modelmanager(Model model) {
    	model.addAllAttributes(symbolService.findAll());
    	
        log.trace("symbolmanager.jsp has been served");
        return "modelmanager";
    }
}
