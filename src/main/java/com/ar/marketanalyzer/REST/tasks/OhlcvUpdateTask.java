package com.ar.marketanalyzer.REST.tasks;

import java.io.IOException;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.async.DeferredResult;

import com.ar.marketanalyzer.core.securities.exceptions.SymbolNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

public class OhlcvUpdateTask extends TimerTask{
	
	private final Logger log = LogManager.getLogger(this.getClass());
	
	private DeferredResult<ProcessingStatus> deferredResult;
	private int waitBeforeTaskStarts;
	private int id; //this is the symbol id
	
	@Autowired
	SymbolServiceInterface symbolService;
	@Autowired
	SecurityOhlcvService ohlcvService;
	
	public OhlcvUpdateTask(int id, int waitBeforeTaskStarts, DeferredResult<ProcessingStatus> deferredResult) {
		this.deferredResult = deferredResult;
		this.waitBeforeTaskStarts = waitBeforeTaskStarts;
		this.id=id;
	}

	@Override
	public void run() {
	    if (deferredResult.isSetOrExpired()) {
	    	log.warn("Processing of non-blocking request #{} already expired", id);
	    } else {
	    	boolean deferredStatus = deferredResult.setResult(new ProcessingStatus("Ok", waitBeforeTaskStarts));
	    	
	    	Symbol symbol;
			try {
				log.trace("OHLCV update started for symbol id: " + id);
				symbol = symbolService.findById(id);
				
				ohlcvService.updateOhlcvFromYahoo(symbol);
				
			} catch (SymbolNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	    	
	    	
	    	
	    	log.debug("Processing of non-blocking request #{} done, deferredStatus = {}", id, deferredStatus);
	    }
	}

}
