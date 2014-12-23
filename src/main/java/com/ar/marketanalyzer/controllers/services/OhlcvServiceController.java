package com.ar.marketanalyzer.controllers.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Controller
@RequestMapping("/ohlcvmanager")
public class OhlcvServiceController {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
	@Autowired
	SecurityOhlcvService ohlcvService;
	
	@RequestMapping(value="update/{id}", method = RequestMethod.GET)
	public String getOhlcvFromYahoo(RedirectAttributes redir, @PathVariable int id) {
		
		log.trace("Looking for symbol with id:" + id);
		Symbol symbol = null;
		try {
			symbol = symbolService.findById(id);
			
			ohlcvService.updateOhlcvFromYahoo(symbol);
			
			redir.addFlashAttribute("message", "OHLCV updaed for symbol: " + symbol.getSymbol());
			redir.addFlashAttribute("messagestatus", "success");
			
		} catch (SymbolNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			redir.addFlashAttribute("message", symbol.getSymbol() + " is not a valid symbol, so you should probably delete it.");
			redir.addFlashAttribute("messagestatus", "fail");
		}
		
		
		
		return "redirect:/stockmanager";
	}
}
