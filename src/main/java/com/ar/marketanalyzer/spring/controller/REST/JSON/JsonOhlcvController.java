package com.ar.marketanalyzer.spring.controller.REST.JSON;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SymbolService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.plotting.amstockcharts.charts.AmStockChart;
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataProviderOHLCV;

@RestController
@RequestMapping("/json")
public class JsonOhlcvController {
	
	//protected Logger logger = Logger.getLogger(getClass());
	private static final Logger logger = LogManager.getLogger(JsonOhlcvController.class);
	
	@Autowired
	private SymbolService symbolService;
	@Autowired
	private SecurityOhlcvServiceInterface ohlcvService;

	@RequestMapping(value="amchart/{symbol}", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	@ResponseBody
	public AmStockChart getamchartJSON(@PathVariable String symbol) {
		logger.debug("Symbol passed to the amchart JSON controller is: " + symbol);
		
		//ProCandlestickChart chart = new ProCandlestickChart();
		AmStockChart chart = null;
		
		Symbol sym;
		
		try {
			//Getting the symbol
			sym = symbolService.findBySymbol("^IXIC");
			
			//Looking up the desired range of OHLCV
			LocalDate backToDate = new LocalDate(2012,1,1);
			java.util.Date backTo = (java.util.Date)(backToDate.toDate());
			List<SecuritiesOhlcv> data = ohlcvService.findBySymbolAndDateAfterAsc(sym, new java.sql.Date(backTo.getTime()));
			
			logger.debug("Newest Data Point: " + data.get(0));
			logger.debug("Oldest Data Point: " + data.get(data.size()-1));
			
			chart = new AmStockChart(DataProviderOHLCV.convertSecuritiesOhlcvToDataProviderOHLCV(data));
		} catch (SecuritiesNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chart;
	}
	
	@RequestMapping(value="symbol/{symbol}", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	@ResponseBody
	public List<SecuritiesOhlcv> getOhclvInJSON(@PathVariable String symbol) {
		logger.debug("Symbol passed to the ohlcv JSON controller is: " + symbol);
		try {
			Symbol stockSymbol = symbolService.findBySymbol("^IXIC");
			return ohlcvService.findBySymbol(stockSymbol);
		} catch (SecuritiesNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
