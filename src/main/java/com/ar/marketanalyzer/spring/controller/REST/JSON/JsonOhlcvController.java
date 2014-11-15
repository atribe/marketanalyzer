package com.ar.marketanalyzer.spring.controller.REST.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.models.IndexBacktestingModel;
import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;
import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsDDaysAndChurnDays;
import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.backtest.models.rules.RuleSellDDaysAndChurnDays;
import com.ar.marketanalyzer.backtest.services.AbstractModelService;
import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SymbolService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.plotting.amstockcharts.charts.AmStockChart;
import com.ar.marketanalyzer.plotting.amstockcharts.data.dataprovider.DataProviderDday;
import com.ar.marketanalyzer.plotting.amstockcharts.data.dataprovider.DataProviderOHLCV;

@RestController
@RequestMapping("/json")
public class JsonOhlcvController {
	
	//protected Logger logger = Logger.getLogger(getClass());
	private static final Logger logger = LogManager.getLogger(JsonOhlcvController.class);
	
	@Autowired
	private SymbolService symbolService;
	@Autowired
	private SecurityOhlcvServiceInterface ohlcvService;
	@Autowired
	private AbstractModelService modelService;

	@RequestMapping(value="amchart/{symbol}", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	@ResponseBody
	public AmStockChart getamchartJSON(@PathVariable String symbol) {
		logger.debug("Symbol passed to the amchart JSON controller is: " + symbol);
		
		AmStockChart chart = null;
		List<SecuritiesOhlcv> ohlcvData = null;
		List<RuleResultsDDaysAndChurnDays> resultList = null;
		Symbol sym;
		
		try {
			//Getting the symbol
			sym = symbolService.findBySymbol("^IXIC");
			
			//Looking up the desired range of OHLCV
			LocalDate backToDate = new LocalDate(2012,1,1);
			java.util.Date backTo = (java.util.Date)(backToDate.toDate());
			ohlcvData = ohlcvService.findBySymbolAndDateAfterAsc(sym, new java.sql.Date(backTo.getTime()));
			
			
			//Getting the d days
			IndexBacktestingModel model = (IndexBacktestingModel)modelService.findBySymbolAndModelStatusEager(sym, ModelStatus.CURRENT);
			resultList = model.getDdaysForPlotting();
			
			while(!resultList.get(0).getDate().equals(ohlcvData.get(0).getDate())) {
				resultList.remove(0);
			}
			
		} catch (SecuritiesNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModelNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		chart = new AmStockChart(DataProviderOHLCV.convertSecuritiesOhlcvToDataProviderOHLCV(ohlcvData), DataProviderDday.convertDdayRuleResultToDataProviderDday(resultList));
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
