package com.ar.marketanalyzer.spring.controller.REST.JSON;

import java.sql.Date;
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
import com.ar.marketanalyzer.plotting.amstockcharts.data.DataProviderOHLCV;
import com.ar.marketanalyzer.plotting.amstockcharts.settings.ChartConfig;

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
	public ChartConfig getamchartJSON(@PathVariable String symbol) {
		logger.debug("Symbol passed to the amchart JSON controller is: " + symbol);
		
		//ProCandlestickChart chart = new ProCandlestickChart();
		ChartConfig chart = null;
		
		Symbol sym;
		
		try {
			//Getting the symbol
			sym = symbolService.findBySymbol("^IXIC");
			
			//Looking up the desired range of OHLCV
			LocalDate backToDate = new LocalDate(2014,1,1);
			java.util.Date backTo = (java.util.Date)(backToDate.toDate());
			List<SecuritiesOhlcv> data = ohlcvService.findBySymbolAndDateAfter(sym, new java.sql.Date(backTo.getTime()));
			
			logger.debug("Newest Data Point: " + data.get(0));
			logger.debug("Oldest Data Point: " + data.get(data.size()-1));
			
			chart = new ChartConfig(DataProviderOHLCV.convertSecuritiesOhlcvToDataProviderOHLCV(data));
		} catch (SecuritiesNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chart;
	}
	
	@RequestMapping(value="amcharttest/{symbol}", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	@ResponseBody
	public String amChartFromJSONString(@PathVariable String symbol) {
		logger.debug("JSON test string");
	
		String test = "{    type: \"stock\",    pathToImages: \"amcharts/images/\",    dataDateFormat: \"YYYY-MM-DD\",    dataSets: [{        dataProvider: [{            date: \"2011-06-01\",            val: 10        }, {            date: \"2011-06-02\",            val: 11        }, {            date: \"2011-06-03\",            val: 12        }, {            date: \"2011-06-04\",            val: 11        }, {            date: \"2011-06-05\",            val: 10        }, {            date: \"2011-06-06\",            val: 11        }, {            date: \"2011-06-07\",            val: 13        }, {            date: \"2011-06-08\",            val: 14        }, {            date: \"2011-06-09\",            val: 17        }, {            date: \"2011-06-10\",            val: 13        }],        fieldMappings: [{            fromField: \"val\",            toField: \"value\"        }],        categoryField: \"date\"    }],    panels: [{        legend: {},        stockGraphs: [{            id: \"graph1\",            valueField: \"value\",            type: \"column\",            title: \"MyGraph\",            fillAlphas: 1        }]    }],    panelsSettings: {        startDuration: 1    },    categoryAxesSettings: {        dashLength: 5    },    valueAxesSettings: {        dashLength: 5    },    chartScrollbarSettings: {        graph: \"graph1\",        graphType: \"line\"    },    chartCursorSettings: {        valueBalloonsEnabled: true    },    periodSelector: {        periods: [{            period: \"DD\",            count: 1,            label: \"1 day\"        }, {            period: \"DD\",            selected: true,            count: 5,            label: \"5 days\"        }, {            period: \"MM\",            count: 1,            label: \"1 month\"        }, {            period: \"YYYY\",            count: 1,            label: \"1 year\"        }, {            period: \"YTD\",            label: \"YTD\"        }, {            period: \"MAX\",            label: \"MAX\"        }]    }}";
	
		return test;
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
