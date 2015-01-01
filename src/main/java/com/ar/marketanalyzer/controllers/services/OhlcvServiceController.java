package com.ar.marketanalyzer.controllers.services;

import java.util.Timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.marketanalyzer.REST.tasks.OhlcvUpdateTask;
import com.ar.marketanalyzer.REST.tasks.ProcessingStatus;
import com.ar.marketanalyzer.core.securities.services.SecurityOhlcvService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;

@RestController
@RequestMapping("/ohlcvmanager")
public class OhlcvServiceController {

	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	SymbolServiceInterface symbolService;
	@Autowired
	SecurityOhlcvService ohlcvService;
	
	private Timer timer = new Timer();
	private final int waitBeforeTaskStarts = 500;
	
	@Async
	@RequestMapping(value="update/{id}", method = RequestMethod.GET)
	public DeferredResult<ProcessingStatus> getOhlcvFromYahoo(RedirectAttributes redir, @PathVariable int id) {
		
		log.trace("Looking for symbol with id:" + id);

	    // Create the deferredResult and initiate a callback object, task, with it
	    DeferredResult<ProcessingStatus> deferredResult = new DeferredResult<>();
	    OhlcvUpdateTask task = new OhlcvUpdateTask(id, waitBeforeTaskStarts, deferredResult);

	    log.trace("timer about to start");
	    // Schedule the task for asynch completion in the future
	    timer.schedule(task, waitBeforeTaskStarts);
	    log.trace("timer started");
	    // Return to let go of the precious thread we are holding on to...
	    return deferredResult;
	}
}
