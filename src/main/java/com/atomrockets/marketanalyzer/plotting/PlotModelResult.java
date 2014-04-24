package com.atomrockets.marketanalyzer.plotting;

import java.awt.Color;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
import com.atomrockets.marketanalyzer.beans.StockTransaction;
import com.atomrockets.marketanalyzer.beans.BacktestResult.parametersTypeEnum;
import com.atomrockets.marketanalyzer.services.BacktestService;
import com.atomrockets.marketanalyzer.threads.marketAnalyzerListener;

public class PlotModelResult {

	static Logger log = Logger.getLogger(PlotModelResult.class.getName());

	public static JFreeChart createChart(String symbol) {
		if(marketAnalyzerListener.dbInitThreadIsAlive()) {
			return null;
		} else {
			return reallyCreateChart(symbol);
		}
	}

	/*
	 * This is a template for future plots
	 */
	private static JFreeChart reallyCreateChart(String symbol) {
		
		//0. Get data from database
    	BacktestService backtestService = new BacktestService();
		List<IndexOHLCVCalcs> resultList = backtestService.getModelResults(symbol, parametersTypeEnum.CURRENT);
		BacktestResult backtestModel = backtestService.getCurrent(symbol);
		List<StockTransaction> transactionList = backtestService.getCurrentTransactions(symbol);
		
		//0a. Set the variables for the chart
		String chartTitle = "Result Plot for " + symbol;
		String xAxisLabel = "Date";
		String yAxisLabel = "Price";
				
		//1. Create the datasets
		XYDataset dataset1 = createPrimaryDataset(resultList, backtestModel, transactionList);
		int seriesCount = dataset1.getSeriesCount();
		//2. Setup Renderer for the first dataset
		XYItemRenderer renderer1 = new XYLineAndShapeRenderer(true, false);//true for lines, false for no shapes
		
		//2a. Series level styles applied to the renderer
		/*
		for(int i = 0;i < seriesCount; i = i +2)
		{
			renderer1.setSeriesPaint(i, Color.GREEN);
			renderer1.setSeriesPaint(i+1, Color.RED);
		}
		*/
		
		//3. Set Domain axis
		DateAxis domainAxis = new DateAxis(xAxisLabel);
		
		//3a. Set domain axis style
		domainAxis.setVerticalTickLabels(true);
		domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
			DateFormat formatter = new SimpleDateFormat("MM-dd-yy");
			DateTickUnit unit = new DateTickUnit(DateTickUnitType.MONTH, 3, formatter);
		domainAxis.setTickUnit(unit);
		domainAxis.setMinimumDate(new LocalDate("1990-06-01").toDateTimeAtStartOfDay().toDate());
		domainAxis.setMaximumDate(new LocalDate("2010-12-31").toDateTimeAtStartOfDay().toDate());
		
		//4. Set Range axis
		NumberAxis rangeAxis = new NumberAxis(yAxisLabel);
		
		//4a. Set range axis style
		rangeAxis.setAutoRangeIncludesZero(false);
		rangeAxis.setNumberFormatOverride(new DecimalFormat("$0"));
		rangeAxis.setLowerBound(1000);
		rangeAxis.setUpperBound(5000);
		
		//5. Create Plot from dataset, domainAxis, rangeAxis, and renderer
		XYPlot plot1 = new XYPlot(dataset1, domainAxis, rangeAxis, renderer1);
		
		//6. Annotate plot
		plot1 = annotatePlot(plot1,  resultList);
		
		//3. Creat the chart
		JFreeChart chart = new JFreeChart(
				chartTitle, //chart title
		        JFreeChart.DEFAULT_TITLE_FONT, //title font
		        plot1, //plot
		        false); //createLegend
		// OR
		/*
		JFreeChart chart = new JFreeChart(
				chartTitle, //chart title
		        plot1); //plot
		*/
		// OR
		/*
		JFreeChart chart = new JFreeChart(
				plot1); //plot
		*/
				
		return chart;
	}

	private static XYDataset createPrimaryDataset(List<IndexOHLCVCalcs> resultList, BacktestResult backtestModel, List<StockTransaction> transactionList) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		String symbol = backtestModel.getSymbol();
		
		TimeSeries seriesBuy = new TimeSeries(symbol);
		TimeSeries seriesSell = new TimeSeries(symbol);
		//Transaction Iterator
		int i = 0;
		
		//Initialing transactionList variables
		LocalDate buyDate = new LocalDate(transactionList.get(i).getBuyDate());
		LocalDate sellDate = new LocalDate(transactionList.get(i).getSellDate());
		
		for(IndexOHLCVCalcs a : resultList ) {
			
			if(a.getConvertedDate().isEqual(sellDate)) { 
				//if the date is the sell date, sell and move to the next transaction
				seriesSell.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
				
				//moving to the next transaction
				i++;
				//setting the new buyDate and sellDate
				if(i<transactionList.size()-1) {
					try {
						buyDate = new LocalDate(transactionList.get(i).getBuyDate());
						sellDate = new LocalDate(transactionList.get(i).getSellDate());
					} catch(NullPointerException e) {
			            System.out.print("NullPointerException caught");
			        }
				}
				
				dataset.addSeries(seriesBuy);
				dataset.addSeries(seriesSell);
				
				seriesBuy = new TimeSeries(symbol);
				seriesSell = new TimeSeries(symbol);
			} else if(a.getConvertedDate().isEqual(buyDate) || a.getConvertedDate().isAfter(buyDate)) { 
				//if the date of the OHCLV data is after a buy date then add it to the buy list
				seriesBuy.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
			} else { 
				//if the date is before the buy date of the current transaction, then sell
				seriesSell.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
			}
		}
		
		//dataset.addSeries(seriesBuy);
		//dataset.addSeries(seriesSell);
		return dataset;
	}
	
	private static XYPlot annotatePlot(XYPlot plot1,
			List<IndexOHLCVCalcs> resultList) {
		// TODO Auto-generated method stub
		return plot1;
	}
}
