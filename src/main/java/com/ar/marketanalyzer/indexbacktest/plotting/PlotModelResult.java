package com.ar.marketanalyzer.indexbacktest.plotting;

import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.TextAnchor;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.indexbacktest.beans.BacktestBean;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVCalcs;
import com.ar.marketanalyzer.indexbacktest.beans.StockTransaction;
import com.ar.marketanalyzer.indexbacktest.beans.BacktestBean.parametersTypeEnum;
import com.ar.marketanalyzer.indexbacktest.services.BacktestService;
import com.ar.marketanalyzer.threads.MarketAnalyzerListener;

public class PlotModelResult {

	static Logger log = Logger.getLogger(PlotModelResult.class.getName());
	
	private static BigDecimal maxPrice;
	private static BigDecimal minPrice;

	public static JFreeChart createChart(String symbol) {
		if(MarketAnalyzerListener.dbInitThreadIsAlive()) {
			return null;
		} else {
			maxPrice=new BigDecimal(0);
			minPrice=new BigDecimal(0);
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
		BacktestBean backtestModel = backtestService.getCurrent(symbol);
		List<StockTransaction> transactionList = backtestService.getCurrentTransactions(symbol);
		
		//0a. Set the variables for the chart
		String chartTitle = "Result Plot for " + symbol;
		String xAxisLabel = "Date";
		String yAxisLabel = "Price";
				
		//1. Create the datasets
		XYDataset dataset1 = createPrimaryDataset(resultList, backtestModel, transactionList);
		int seriesCount = dataset1.getSeriesCount();
		//2. Setup Renderer for the first dataset
		XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(true, false);//true for lines, false for no shapes
		
		//2a. Series level styles applied to the renderer
		renderer1.setSeriesPaint(0, Color.GREEN);
		for(int i = 1;i < seriesCount; i = i +2)
		{
			renderer1.setSeriesPaint(i, Color.GREEN);
			renderer1.setSeriesPaint(i+1, Color.RED);
		}
		renderer1.setSeriesPaint(seriesCount-1, Color.RED);
		
		/*
		 * Setting markers at the end of the symbols
		//Setting shape and color of the buy date marker
		renderer1.setSeriesShape(seriesCount-1, ShapeUtilities.createUpTriangle(4));
		renderer1.setSeriesPaint(seriesCount-1, Color.CYAN);
		renderer1.setSeriesLinesVisible(seriesCount-1, false);
		renderer1.setSeriesShapesVisible(seriesCount-1, true);
		//Setting shape and color of the sell date marker
		renderer1.setSeriesShape(seriesCount-2, ShapeUtilities.createDownTriangle(4));
		renderer1.setSeriesPaint(seriesCount-2, Color.DARK_GRAY);
		renderer1.setSeriesLinesVisible(seriesCount-2, false);
		renderer1.setSeriesShapesVisible(seriesCount-2, true);
		*/
		//3. Set Domain axis
		DateAxis domainAxis = new DateAxis(xAxisLabel);
		
		//3a. Set domain axis style
		domainAxis.setVerticalTickLabels(true);
		domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
			DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
			DateTickUnit unit = new DateTickUnit(DateTickUnitType.MONTH, 3, formatter);
		domainAxis.setTickUnit(unit);
		//domainAxis.setMinimumDate(new LocalDate("1990-06-01").toDateTimeAtStartOfDay().toDate());
		//domainAxis.setMaximumDate(new LocalDate("2010-12-31").toDateTimeAtStartOfDay().toDate());
		
		//4. Set Range axis
		NumberAxis rangeAxis = new NumberAxis(yAxisLabel);
		
		//4a. Set range axis style
		rangeAxis.setAutoRangeIncludesZero(false);
		rangeAxis.setNumberFormatOverride(new DecimalFormat("$0"));
		rangeAxis.setLowerBound(minPrice.doubleValue()*1.05);//min and max axis to be 5% away from the actual min and max
		rangeAxis.setUpperBound(maxPrice.doubleValue()*1.05);
		
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

	private static XYDataset createPrimaryDataset(List<IndexOHLCVCalcs> resultList, BacktestBean backtestModel, List<StockTransaction> transactionList) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		String symbol = backtestModel.getSymbol();
		
		TimeSeries sellDates = new TimeSeries("Sell Dates");
		TimeSeries buyDates = new TimeSeries("Buy Dates");
		TimeSeries seriesBuy = new TimeSeries(symbol);
		TimeSeries seriesSell = null;
		//Transaction Iterator
		int i = 0;
		
		//Initialing transactionList variables
		LocalDate buyDate = new LocalDate(transactionList.get(i).getBuyDate());
		LocalDate sellDate = new LocalDate(transactionList.get(i).getSellDate());
		boolean noMoreTransactions = false;
		
		//initializing min and max price
		minPrice = resultList.get(0).getClose();
		maxPrice = minPrice;
		
		//Loop through all the data between the first buy date and last sell date
		//for(IndexOHLCVCalcs a : resultList ) {
		int iterator;
		for(iterator = 0; iterator < resultList.size(); iterator++) {
			
			IndexOHLCVCalcs a = resultList.get(iterator);
			
			//Setting the min and max price for the chart
			if(a.getClose().compareTo(minPrice) < 0) {
				minPrice = a.getClose();
			} else if(a.getClose().compareTo(maxPrice) > 0) {
				maxPrice = a.getClose();
			}
			
			/*
			 * If there are no more transactions (aka you've sold and aren't going to buy again for the duration of the time period)
			 */
			if(noMoreTransactions) { 
				seriesSell.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
			}
			/*
			 * If today is a sell day
			 */
			else if(a.getConvertedDate().isEqual(sellDate)) { 
				/*
				 * for markers on the sellDates
				*/
				sellDates.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
				
				//moving to the next transaction in the transactionList
				i++;
				
				//setting the new buyDate and sellDate from the new transaction
				if(i<transactionList.size()) {
					try {
						buyDate = new LocalDate(transactionList.get(i).getBuyDate());
						sellDate = new LocalDate(transactionList.get(i).getSellDate());
					} catch(NullPointerException e) {
			            System.out.print("NullPointerException caught");
			            log.debug("null pointer exception caught");
			        }
				} else {
					noMoreTransactions = true;
				}
				
				//adding the buy series to the dataset
				dataset.addSeries(seriesBuy);
				
				//making sure the sell series isn't empty
				if(seriesSell!=null){
					//adding the sell series to the dataset
					dataset.addSeries(seriesSell);
				}
				
				//creating a new series
				seriesSell = new TimeSeries("sell " +symbol);
				seriesBuy = new TimeSeries("buy " + symbol);
				
				//adding the sell date as the first data point
				seriesSell.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
			}
			else if(a.getConvertedDate().isEqual(buyDate) || a.getConvertedDate().isAfter(buyDate)) { //if today is the buy day or later (but before the sell day) 
				//if the date of the OHCLV data is after a buy date then add it to the buy list
				seriesBuy.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
				
				/*
				Setting markers at the buy date
				*/
				if(a.getConvertedDate().isEqual(buyDate)){
					buyDates.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
				}
				
			} else { //in the sell period
				//if the date is before the buy date of the current transaction, then sell
				seriesSell.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getClose());
			}
			
			if(a.getConvertedDate().isAfter(new LocalDate("2012-12-05"))) {
				System.out.print(a.getConvertedDate().toString());
			}
		}
		
		/*
		//adding the buy and sell dates series to the dateset
		dataset.addSeries(sellDates);
		dataset.addSeries(buyDates);
		*/
		
		if(seriesSell != null) {
			dataset.addSeries(seriesSell);
		}
		
		return dataset;
	}
	
	private static XYPlot annotatePlot(XYPlot plot, List<IndexOHLCVCalcs> resultList, List<StockTransaction> transactionList) {
		double textVerticalSpacing = calcTextVerticalSpacing(plot);
		
		//Transaction Iterator
		int i = 0;
		
		//Initialing transactionList variables
		LocalDate sellDate = new LocalDate(transactionList.get(i).getSellDate());
		
		// add some annotations...   
        XYTextAnnotation annotation = null;   
        Font font = new Font("SansSerif", Font.PLAIN, 9);
        Font soldFont = new Font("SansSerif", Font.PLAIN, 18); 
        double x = 0;
        double y = 0;
        
        for(IndexOHLCVCalcs a : resultList) {
        	//if the date is the sell date
			if(a.getConvertedDate().isEqual(sellDate)) {				
				//Annotation for the word Sold
				x = new Day(a.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = a.getHigh().doubleValue()+textVerticalSpacing;
        		String annotationText = "SOLD";
        		annotation = new XYTextAnnotation(annotationText, x, y);  
                annotation.setFont(soldFont);   
                annotation.setTextAnchor(TextAnchor.CENTER);   
                plot.addAnnotation(annotation);
                
                //annotation for the % return
                x = new Day(a.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = a.getHigh().doubleValue();
        		annotationText = "Return: " + (double)Math.round(transactionList.get(i).getPercentReturn() * 1000) / 20 + "%";
        		annotation = new XYTextAnnotation(annotationText, x, y);  
                annotation.setFont(soldFont);   
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
			} else if (Boolean.TRUE.equals(a.getFollowThruDay())) {
                //annotation for Follow Thru Days
                x = new Day(a.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = a.getHigh().doubleValue();
        		String annotationText = "F Day";
        		annotation = new XYTextAnnotation(annotationText, x, y);  
                annotation.setFont(font);   
                annotation.setTextAnchor(TextAnchor.CENTER);   
                plot.addAnnotation(annotation);
			}
        }
		return plot;
	}

	private static double calcTextVerticalSpacing(XYPlot plot) {
		ValueAxis verticalAxis = plot.getRangeAxis();
		
		double upperBound = verticalAxis.getUpperBound();
		double lowerBound = verticalAxis.getLowerBound();
		
		double range = upperBound - lowerBound;
		double offsetFactor = .02;
		
		return range*offsetFactor;
	}
}
