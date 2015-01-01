package com.ar.marketanalyzer.controllers.services;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;
import com.ar.marketanalyzer.core.threads.callables.OhclvUpdateCallable;

@Controller
@RequestMapping("/ohlcvmanager")
public class OhlcvServiceController {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
	@Autowired
	SecurityOhlcvService ohlcvService;
	
	@RequestMapping(value="update/{id}", method = RequestMethod.GET)
	public Callable<String> getOhlcvFromYahoo(RedirectAttributes redir, @PathVariable int id) {
		
		log.trace("Looking for symbol with id:" + id);

		return new OhclvUpdateCallable(redir, id);
		
	}
}
