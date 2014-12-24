package com.ar.marketanalyzer.controllers.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Controller
@RequestMapping("/stocksummary")
public class StockSummaryController {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
    
	@RequestMapping(method = RequestMethod.GET)
    public String stocksummary(Model model) {
		log.trace("Starting the stock summary controller.");
		
		List<Symbol> symbols = symbolService.findAll();
		model.addAttribute("symbols", symbols);
		model.addAttribute("symbol", new Symbol());
		
		log.trace("Ending the stock summary controller.");
        return "choosesymbolsummary";
    }
	@RequestMapping(value = "{sym}", method = RequestMethod.GET)
    public String stocksummaryid(Model model,@RequestParam String symbol,@PathVariable String sym) {
		log.trace("Starting the stock summary controller for symbol "+ sym +".");
        return "symbolsummary";
    }
}
