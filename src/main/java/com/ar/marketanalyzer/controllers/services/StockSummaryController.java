package com.ar.marketanalyzer.controllers.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
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
	@RequestMapping(params = {"symbol"}, method = RequestMethod.GET)
    public String stocksummaryid(Model model,@RequestParam String symbol) {
		log.trace("Starting the stock summary controller for symbol "+ symbol +".");
		
		try {
			Symbol sym = symbolService.findBySymbol(symbol);
		
			model.addAttribute("symbol", sym);
			
		} catch (SymbolNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.trace("Ending the stock summary controller for symbol "+ symbol +".");
        return "symbolsummary";
    }
}
