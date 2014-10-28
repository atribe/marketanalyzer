package com.ar.marketanalyzer.spring.controller.REST.JSON;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.ar.marketanalyzer.plotting.amcharts.charts.ProCandlestickChart;
import com.ar.marketanalyzer.plotting.amcharts.data.OhlcData;

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
	public ProCandlestickChart getamchartJSON(@PathVariable String symbol) {
		logger.debug("Symbol passed to the amchart JSON controller is: " + symbol);
		
		ProCandlestickChart chart = new ProCandlestickChart();
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		OhlcData A = new OhlcData(new Date(cal.getTimeInMillis()), new BigDecimal(13.53), new BigDecimal(15.21), new BigDecimal(11.11), new BigDecimal(14.03));
		OhlcData B = new OhlcData(new Date(cal.getTimeInMillis()-86400000), new BigDecimal(14.10), new BigDecimal(15.51), new BigDecimal(12.11), new BigDecimal(12.53));
		OhlcData C = new OhlcData(new Date(cal.getTimeInMillis()-86400000*2), new BigDecimal(10.53), new BigDecimal(15.21), new BigDecimal(10.11), new BigDecimal(14.03));
		OhlcData D = new OhlcData(new Date(cal.getTimeInMillis()-86400000*3), new BigDecimal(13.53), new BigDecimal(15.21), new BigDecimal(11.11), new BigDecimal(14.03));
		List<OhlcData> dataList = chart.getDataProvider();
		dataList.add(D);
		dataList.add(C);
		dataList.add(B);
		dataList.add(A);
		chart.setDataProvider(dataList);
		logger.debug(chart);
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
