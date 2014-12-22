package com.ar.marketanalyzer.controllers.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Controller
@RequestMapping("/stockmanager")
public class SymbolServiceController {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
    
	@RequestMapping(method = RequestMethod.GET)
    public String stockmanager(Model model) {
    	List<Symbol> symbols = symbolService.findAllFetchOhlcv();
		for(Symbol symbol: symbols) {
			log.trace(symbol.getSymbol() + " has " +symbol.getOhlcv().size() + " entries in the ohlcv database");
		}
    	model.addAttribute("symbols", symbols);
    	
        log.trace("symbolmanager.jsp has been served");
        return "symbolmanager";
    }
	
	@RequestMapping(value = "/add", method=RequestMethod.GET)
	public String createSymbol(Model model) {
		
		model.addAttribute("symbol", new Symbol());
		
		return "addsymbol";
	}
	
	@RequestMapping(value = "/addprocess", method=RequestMethod.POST)
	public String addSymbol(@ModelAttribute Symbol symbol) {
		
		log.trace("Attempting to delete symbol id " + symbol.getSymbol());
		
		symbolService.createOrFindDuplicate(symbol);

		log.trace("Succeded in deleting symbol id " + symbol.getSymbol());
		
		return "redirect:/stockmanager";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSymbol(@PathVariable int id) {
		
		log.trace("Attempting to delete symbol id " + id);
		
		try {
			symbolService.delete(id);
		} catch (SecuritiesNotFound e) {
			log.trace("Failed to delete symbol id " + id);
			e.printStackTrace();
		}
		log.trace("Succeded in deleting symbol id " + id);
		
		return "redirect:/stockmanager";
	}
}
