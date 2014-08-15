/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.marketanalyzer.indexbacktest.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVData;
import com.ar.marketanalyzer.indexbacktest.beans.YahooOHLCV;
import com.ar.marketanalyzer.securities.models.Symbol;

/**
 * @author Aaron
 */
public class YahooOhlcvDao {
	
	static Logger log = Logger.getLogger(GenericDBSuperclass.class.getName());

	public static List<IndexOHLCVData> getIndexFromYahoo(String url, String index) {
		
		List<YahooOHLCV> rowsFromYahooURL = null;
		try {
			
			rowsFromYahooURL = getAndParseYahooData(index, url);
		
		} catch (FileNotFoundException fe) {
			log.error("the yahoo URL: " + url + " was not valid. It is probably just after midnight and the yahoo servers have not yet updated.", fe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Because the symbol is not downloaded in the CSV I add it, in the following
		 * method, to the row data.
		 */
	    List<IndexOHLCVData> convertedRowsFromYahooURL = addSymbolAndConvertToOHLCVData(rowsFromYahooURL, index);
		
		return convertedRowsFromYahooURL;
	}
	
	private static List<YahooOHLCV> getAndParseYahooData(String symbol, String url) throws IOException {
		URL ur = new URL(url);
		HttpURLConnection HUC = (HttpURLConnection) ur.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(HUC.getInputStream()));
		
		reader.readLine();//reads the first line, it's just headers
		
		//OpenCSV parser
		CSVReader csvReader = new CSVReader(reader, ',', '\"');
		ColumnPositionMappingStrategy<YahooOHLCV> strategy = new ColumnPositionMappingStrategy<YahooOHLCV>();
	    strategy.setType(YahooOHLCV.class);
	    strategy.setColumnMapping(new String[]{"date","open","high","low","close","volume","adjClose"});

	    CsvToBean<YahooOHLCV> csv = new CsvToBean<YahooOHLCV>();
	    List<YahooOHLCV> rowsFromYahooURL = csv.parse(strategy, csvReader);
	    
	    return rowsFromYahooURL;
	}

	/**
	 * *Builds url for a user supplied # of startDaysAgo
	 * @param symbol    market or stock for which data is desired
	 * @param daysAgo  # of startDaysAgo to retrieve data
	 * @return  construed URL
	 */
	public static String getYahooURL(String symbol, int daysAgo) {
		
		LocalDate endDate;
		LocalTime now = new LocalTime(); 
		if(now.getHourOfDay() == 0) {
			endDate = new LocalDate().minusDays(1);
		} else {
			endDate = new LocalDate();
		}
		
		if(daysAgo < 2){
			daysAgo = 2;
		}
		
		LocalDate startDate = endDate.minusDays(daysAgo);

		int a_startMonth, b_startDay, c_startYear;
		int d_endMonth, e_endDay, f_endYear; 
		a_startMonth = startDate.getMonthOfYear()-1;//Yahoo uses zero based month numbering, this gets the beginning dates month
		b_startDay = startDate.getDayOfMonth();//this gets beginning dates day
		c_startYear = startDate.getYear();//this gets beginning dates year
		
		log.debug("Start Month (variable A): " + a_startMonth);
		log.debug("Start Day (variable B): " + b_startDay);
		log.debug("Start Year (variable C): " + c_startYear);

		d_endMonth = endDate.getMonthOfYear()-1;//Yahoo uses zero based month numbering,this gets todays month
		e_endDay = endDate.getDayOfMonth();//this gets todays day of month
		f_endYear = endDate.getYear();//this gets todays year
		
		log.debug("End Month (variable D): " + d_endMonth);
		log.debug("End Day (variable E): " + e_endDay);
		log.debug("End Year (variable f): " + f_endYear);

		String str = "http://ichart.finance.yahoo.com/table.csv?s="
				+ symbol.toUpperCase() + "&a=" + a_startMonth + "&b=" + b_startDay + "&c=" + c_startYear + "&g=d&d=" + d_endMonth + "&e=" + e_endDay
				+ "&f=" + f_endYear + "&ignore=.csv";
		log.info("          Using the following Yahoo URL:");
		log.info("          " + str);
		return str;
	}

	public static int getNumberOfDaysFromNow(LocalDate date){
		LocalDate today = new LocalDate(); //Variable with today's date

		//Check if today is weekend, if so adjust the date back until it isn't a weekend
		while(today.getDayOfWeek() > 5){
			today = today.minusDays(1);
		}
		return Days.daysBetween(date, today).getDays();
	}
	
	private static List<IndexOHLCVData> addSymbolAndConvertToOHLCVData(
			List<YahooOHLCV> rowsFromYahooURL, String index) {
		
		List<IndexOHLCVData> convertedList = new ArrayList<IndexOHLCVData>();
		for(YahooOHLCV rowFromYahooURL:rowsFromYahooURL) {
			rowFromYahooURL.setSymbol(index);
			convertedList.add(new IndexOHLCVData(rowFromYahooURL));
		}
		
		return convertedList;
	}
	
	public static List<StockOhlcv> getStockFromYahoo(Symbol ticker, LocalDate startDate) {
		int daysAgo = getNumberOfDaysFromNow(startDate);
		
		String url = getYahooURL(ticker.getSymbol(), daysAgo);		
		
		List<YahooOHLCV> rawList = null;
		List<StockOhlcv> convertedList = null;
		
		try {
			rawList = getAndParseYahooData(ticker.getSymbol(), url);
		} catch (FileNotFoundException fe) {
			log.error("the yahoo URL: " + url + " was not valid. It is probably just after midnight and the yahoo servers have not yet updated.", fe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		convertedList = YahooOhlcvToStockOhlcvBean(rawList, ticker);
		
		return convertedList;
	}

	private static List<StockOhlcv> YahooOhlcvToStockOhlcvBean(List<YahooOHLCV> rawList, Symbol ticker) {
		
		List<StockOhlcv> convertedList = new ArrayList<StockOhlcv>();
		
		for(YahooOHLCV rowFromYahooURL:rawList) {
			convertedList.add(new StockOhlcv(rowFromYahooURL, ticker));
		}
		
		return convertedList;
	}

	/*
	 * Spring Data JPA + Hibernate section
	 */
	
	public static List<YahooOHLCV> getYahooOhlcvData(String symbol, LocalDate startDate, LocalDate endDate) throws IOException {
		
		String url = generateURL(symbol, startDate, endDate);		// Generate URL
		
		BufferedReader reader = callURL(url);
		
		return parseCSVtoObject(reader);
	}

	private static String generateURL(String symbol, LocalDate startDate, LocalDate endDate) {
		LocalTime now = new LocalTime(); 						// temporary variable with the current time
		if(now.getHourOfDay() == 0) {							// if it is between 12:00 am and 1:00 am
			endDate = new LocalDate().minusDays(1);				// 		then subtract one day from the end date (this is because Yahoo's servers are on pacific time)
		}

		int a_startMonth, b_startDay, c_startYear;
		 
		a_startMonth = startDate.getMonthOfYear()-1;			// Yahoo uses zero based month numbering, this gets the beginning dates month
		b_startDay = startDate.getDayOfMonth();					// this gets beginning dates day
		c_startYear = startDate.getYear();						// this gets beginning dates year
		
		log.debug("Start Month (variable A): " + a_startMonth);
		log.debug("Start Day (variable B): " + b_startDay);
		log.debug("Start Year (variable C): " + c_startYear);
		
		int d_endMonth, e_endDay, f_endYear;
		d_endMonth = endDate.getMonthOfYear()-1;				// Yahoo uses zero based month numbering,this gets todays month
		e_endDay = endDate.getDayOfMonth();						// this gets todays day of month
		f_endYear = endDate.getYear();							// this gets todays year
		
		log.debug("End Month (variable D): " + d_endMonth);
		log.debug("End Day (variable E): " + e_endDay);
		log.debug("End Year (variable f): " + f_endYear);

		String str = "http://ichart.finance.yahoo.com/table.csv?s="	// Generating the yahoo finance URL
			+ symbol.toUpperCase() + "&a=" + a_startMonth + "&b=" + b_startDay + "&c=" + c_startYear 
			+ "&g=d&d=" + d_endMonth + "&e=" + e_endDay	+ "&f=" + f_endYear + "&ignore=.csv";
		
		log.info("Using the following Yahoo URL:");
		log.info(str);
		return str;
	}
	
	private static BufferedReader callURL(String url) throws IOException {
		URL ur = new URL(url);
		HttpURLConnection HUC = (HttpURLConnection) ur.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(HUC.getInputStream()));
		
		reader.readLine();											//reads the first line, it's just headers
		
		return reader;
	}

	private static List<YahooOHLCV> parseCSVtoObject(BufferedReader reader) {
		//OpenCSV parser
		CSVReader csvReader = new CSVReader(reader, ',', '\"');
		ColumnPositionMappingStrategy<YahooOHLCV> strategy = new ColumnPositionMappingStrategy<YahooOHLCV>();
	    strategy.setType(YahooOHLCV.class);
	    strategy.setColumnMapping(new String[]{"date","open","high","low","close","volume","adjClose"});
	
	    CsvToBean<YahooOHLCV> csv = new CsvToBean<YahooOHLCV>();
	    List<YahooOHLCV> rowsFromYahooURL = csv.parse(strategy, csvReader);
	    
	    return rowsFromYahooURL;
	}
}
