package com.ar.marketanalyzer.controllers.rest.crud;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@RestController
@RequestMapping("/REST/ohlcv")
public class OhlcvRESTController {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
	@Autowired
	SecurityOhlcvService ohlcvService;

	
	@RequestMapping(value="update/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int getOhlcvFromYahoo(ModelAndView model, @PathVariable int id) {
		int ohlcvCount = 0;
		log.trace("Looking for symbol with id:" + id);
		
		Symbol symbol;
		try {
			log.trace("OHLCV update started for symbol id: " + id);
			symbol = symbolService.findById(id);
			
			ohlcvCount = ohlcvService.updateOhlcvFromYahoo(symbol);
			
		} catch (SymbolNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return ohlcvCount;
	}
}