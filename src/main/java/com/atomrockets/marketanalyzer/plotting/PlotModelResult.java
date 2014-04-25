package com.atomrockets.marketanalyzer.plotting;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
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
import org.jfree.ui.TextAnchor;
import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
import com.atomrockets.marketanalyzer.beans.StockTransaction;
import com.atomrockets.marketanalyzer.beans.BacktestResult.parametersTypeEnum;
import com.atomrockets.marketanalyzer.services.BacktestService;
import com.atomrockets.marketanalyzer.threads.marketAnalyzerListener;

public class PlotModelResult {

	static Logger log = Logger.getLogger(PlotModelResult.class.getName());
	
	private static double maxPrice;
	private static double minPrice;

	public static JFreeChart createChart(String symbol) {
		if(marketAnalyzerListener.dbInitThreadIsAlive()) {
			return null;
		} else {
			maxPrice=0;
			minPrice=0;
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
		renderer1.setSeriesPaint(0, Color.GREEN);
		for(int i = 1;i < seriesCount; i = i +2)
		{
			renderer1.setSeriesPaint(i, Color.GREEN);
			renderer1.setSeriesPaint(i+1, Color.RED);
		}
		
		//3. Set Domain axis
		DateAxis domainAxis = new DateAxis(xAxisLabel);
		
		//3a. Set domain axis style
		domainAxis.setVerticalTickLabels(true);
		domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
			DateFormat formatter = new SimpleDateFormat("MM-dd-yy");
			DateTickUnit unit = new DateTickUnit(DateTickUnitType.MONTH, 3, formatter);
		domainAxis.setTickUnit(unit);
		//domainAxis.setMinimumDate(new LocalDate("1990-06-01").toDateTimeAtStartOfDay().toDate());
		//domainAxis.setMaximumDate(new LocalDate("2010-12-31").toDateTimeAtStartOfDay().toDate());
		
		//4. Set Range axis
		NumberAxis rangeAxis = new NumberAxis(yAxisLabel);
		
		//4a. Set range axis style
		rangeAxis.setAutoRangeIncludesZero(false);
		rangeAxis.setNumberFormatOverride(new DecimalFormat("$0"));
		rangeAxis.setLowerBound(minPrice*1.05);//min and max axis to be 5% away from the actual min and max
		rangeAxis.setUpperBound(maxPrice*1.05);
		
		//5. Create Plot from dataset, domainAxis, rangeAxis, and renderer
		XYPlot plot1 = new XYPlot(dataset1, domainAxis, rangeAxis, renderer1);
		
		//6. Annotate plot
		plot1 = annotatePlot(plot1,  resultList, transactionList);
		
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
		TimeSeries seriesSell = null;
		//Transaction Iterator
		int i = 0;
		
		//Initialing transactionList variables
		LocalDate buyDate = new LocalDate(transactionList.get(i).getBuyDate());
		LocalDate sellDate = new LocalDate(transactionList.get(i).getSellDate());
		
		//initializing min and max price
		minPrice = resultList.get(0).getClose();
		maxPrice = minPrice;
		
		for(IndexOHLCVCalcs a : resultList ) {
			//Setting the min and max price for the chart
			if(a.getClose() < minPrice) {
				minPrice = a.getClose();
			} else if(a.getClose() > maxPrice) {
				maxPrice = a.getClose();
			}
			
			//data point date with buy and sell date
			if(a.getConvertedDate().isEqual(sellDate)) {				
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
				
				//adding the series to the dataset
				dataset.addSeries(seriesBuy);
				if(seriesSell!=null){
					dataset.addSeries(seriesSell);
				}
				
				//creating a new series
				seriesSell = new TimeSeries("sell " +symbol);
				seriesBuy = new TimeSeries("buy " + symbol);
				
				
				//adding the sell date as the first data point
				seriesSell.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
				
				
				
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
	
	private static XYPlot annotatePlot(XYPlot plot, List<IndexOHLCVCalcs> resultList, List<StockTransaction> transactionList) {
		//Transaction Iterator
		int i = 0;
		
		//Initialing transactionList variables
		LocalDate sellDate = new LocalDate(transactionList.get(i).getSellDate());
		
		// add some annotations...   
        XYTextAnnotation annotation = null;   
        Font font = new Font("SansSerif", Font.PLAIN, 9); 
        double x = 0;
        double y = 0;
        
        for(IndexOHLCVCalcs a : resultList) {
        	/*
        	if(Boolean.TRUE.equals(YID.getDistributionDay())) {
        		x = new Day(YID.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = YID.getLow();
        		annotation = new XYTextAnnotation("D Day", x, y);  
                annotation.setFont(font);   
                annotation.setTextAnchor(TextAnchor.BASELINE_CENTER);   
                plot.addAnnotation(annotation); 
        	}
        	*/
        	//if the date is the sell date
			if(a.getConvertedDate().isEqual(sellDate)) {				
				//Annotation for the word Sold
				x = new Day(a.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = a.getHigh()*1.20;
        		String annotationText = "SOLD";
        		annotation = new XYTextAnnotation(annotationText, x, y);  
                annotation.setFont(font);   
                annotation.setTextAnchor(TextAnchor.CENTER);   
                plot.addAnnotation(annotation);
                
                //annotation for the % return
                x = new Day(a.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = a.getHigh()*1.10;
        		annotationText = "Return: " + (double)Math.round(transactionList.get(i).getPercentReturn() * 1000) / 10 + "%";
        		annotation = new XYTextAnnotation(annotationText, x, y);  
                annotation.setFont(font);   
                annotation.setTextAnchor(TextAnchor.CENTER);   
                plot.addAnnotation(annotation);
				//moving to the next transaction
				i++;
				
				//setting the new buyDate and sellDate
				if(i<transactionList.size()-1) {
					try {
						sellDate = new LocalDate(transactionList.get(i).getSellDate());
					} catch(NullPointerException e) {
			            System.out.print("NullPointerException caught");
			        }
				}
			}
        }
		return plot;
	}
}
