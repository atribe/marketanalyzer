package com.ar.marketanalyzer.core.threads.callables;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@Component
public class OhclvUpdateCallable implements Callable<String>{
	
	@Autowired
	SymbolServiceInterface symbolService;
	@Autowired
	SecurityOhlcvService ohlcvService;
	
	private final int id;
	private final RedirectAttributes redir;

	public OhclvUpdateCallable() {
		id = 0;
		redir = null;
	}
	public OhclvUpdateCallable( RedirectAttributes redir, int id) {
		this.id = id;
		this.redir = redir;
	}
	
	@Override
	public String call() throws Exception {
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
