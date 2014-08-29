package com.ar.marketanalyzer.securities.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.ar.marketanalyzer.securities.models.YahooOHLCV;

@Component
public class YahooOhlcvService {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	/*
	 * Spring Data JPA + Hibernate section
	 */
	public List<YahooOHLCV> getYahooOhlcvData(String symbol, LocalDate startDate) throws IOException {
		return getYahooOhlcvData(symbol, startDate, new LocalDate());
	}
	
	public List<YahooOHLCV> getYahooOhlcvData(String symbol, LocalDate startDate, LocalDate endDate) throws IOException {
		
		String url = generateURL(symbol, startDate, endDate);		// Generate URL
		
		BufferedReader reader = callURL(url);
		
		return parseCSVtoObject(reader);
	}

	private String generateURL(String symbol, LocalDate startDate, LocalDate endDate) {
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
	
	private BufferedReader callURL(String url) throws IOException {
		URL ur = new URL(url);
		HttpURLConnection HUC = (HttpURLConnection) ur.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(HUC.getInputStream()));
		
		reader.readLine();											//reads the first line, it's just headers
		
		return reader;
	}

	private List<YahooOHLCV> parseCSVtoObject(BufferedReader reader) {
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