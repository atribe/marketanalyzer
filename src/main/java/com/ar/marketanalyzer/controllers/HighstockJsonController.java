package com.ar.marketanalyzer.controllers;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.enums.ModelStatus;
import com.ar.marketanalyzer.backtest.models.models.IndexBacktestingModel;
import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsDDaysAndChurnDays;
import com.ar.marketanalyzer.backtest.services.AbstractModelService;
import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.SymbolService;
import com.ar.marketanalyzer.core.securities.services.interfaces.SecurityOhlcvServiceInterface;
import com.ar.marketanalyzer.plotting.highcharts.charts.HighStockOHLCV;
import com.ar.marketanalyzer.plotting.highcharts.data.HighstockOHLC;
import com.ar.marketanalyzer.plotting.highcharts.data.HighstockSingleValueData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json")
public class HighstockJsonController {

    private static final Logger logger = LogManager.getLogger(HighstockJsonController.class);

    @Autowired
    private SymbolService symbolService;
    @Autowired
    private SecurityOhlcvServiceInterface ohlcvService;
    @Autowired
    private AbstractModelService modelService;

    @RequestMapping(value = "highstockohlcv/{symbol}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public HighStockOHLCV getamchartJSON(@PathVariable String symbol) {
        logger.debug("Symbol passed to the highchart JSON controller is: " + symbol);

        //ProCandlestickChart chart = new ProCandlestickChart();
        HighStockOHLCV chart = null;
        List<RuleResultsDDaysAndChurnDays> resultList = null;
        Symbol sym;

        try {
            //Getting the symbol
            sym = symbolService.findBySymbol("^IXIC");

            //List<SecuritiesOhlcv> data = ohlcvService.findBySymbolAndDateAfterAsc(sym, new java.sql.Date(backTo.getTime()));
            List<SecuritiesOhlcv> data = ohlcvService.findBySymbolAsc(sym);
            chart = new HighStockOHLCV(HighstockOHLC.convertSecOHLCVtoOHLC(data),
                                       HighstockSingleValueData.convertSecOHLCVtoSingleValue(data));

            //Getting the d days
            IndexBacktestingModel model = (IndexBacktestingModel) modelService.findBySymbolAndModelStatusEager(sym, ModelStatus.CURRENT);
            resultList = model.getDdaysForPlotting();

            chart.addSeries(HighstockSingleValueData.convertDdayToSingleValue(resultList));
        } catch (SecuritiesNotFound | ModelNotFound e) {
            // TODO Auto-generated catch block
            logger.error("Symbol "
                                 + symbol
                                 + " was not found in the database. The database is probably still loading the data, or you chose a bad symbol to plot.");
        }

        return chart;
    }
}
