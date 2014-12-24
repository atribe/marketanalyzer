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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Controller
@RequestMapping("/stockmanager")
public class StockManagerController {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
    
	/*
	 * Stock Manager Controllers
	 */
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
	public String addSymbol(Model model) {
		
		model.addAttribute("symbol", new Symbol());
		
		return "addsymbol";
	}
	
	@RequestMapping(value = "/addprocess", method=RequestMethod.POST)
	public String addSymbolProcess(@ModelAttribute Symbol symbol, RedirectAttributes redir) {
		
		log.trace("Attempting to delete symbol id " + symbol.getSymbol());
		
		symbolService.createOrFindDuplicate(symbol);

		log.trace("Succeded in deleting symbol id " + symbol.getSymbol());
		
		redir.addFlashAttribute("message", "Symbol " + symbol.getSymbol() + " added successfully.");
		redir.addFlashAttribute("messagestatus", "success");
		
		return "redirect:/stockmanager";
	}
	
	@RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	public String editSymbol(@PathVariable int id, Model model) {
		
		log.trace("Attempting to edit symbol id " + id);
		
		try {
			Symbol symbol = symbolService.findById(id);
			
			model.addAttribute("symbol", symbol);
		} catch (SymbolNotFound e) {
			model.addAttribute("message", "Symbol " + id + " does not exist in the DB.");
			model.addAttribute("messagestatus", "fail");
		}
		
		log.trace("Succeded in edit symbol id " + id);
		
		return "editsymbol";
	}
	
	@RequestMapping(value = "/editprocess", method=RequestMethod.POST)
	public String editSymbolProcess(@ModelAttribute Symbol symbol, RedirectAttributes redir) {
		
		log.trace("Attempting to delete symbol id " + symbol.getSymbol());
		
		symbolService.update(symbol);

		log.trace("Succeded in deleting symbol id " + symbol.getSymbol());
		
		redir.addFlashAttribute("message", "Symbol " + symbol.getSymbol() + " added successfully.");
		redir.addFlashAttribute("messagestatus", "success");
		
		return "redirect:/stockmanager";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSymbol(@PathVariable int id, RedirectAttributes redir) {
		
		log.trace("Attempting to delete symbol id " + id);
		Symbol symbol = null;
		try {
			symbol = symbolService.delete(id);
		} catch (SymbolNotFound e) {
			log.trace("Failed to delete symbol id " + id);
			e.printStackTrace();
		}
		log.trace("Succeded in deleting symbol id " + id);
		
		redir.addFlashAttribute("message", "Symbol " + symbol.getSymbol() + " deleted successfully.");
		redir.addFlashAttribute("messagestatus", "success");
		
		return "redirect:/stockmanager";
	}
}
