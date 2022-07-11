/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.marketanalyzer.core.securities.services;

import com.ar.marketanalyzer.core.securities.models.YahooOHLCV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Aaron/Allan
 */
@Slf4j
@Service
public class YahooOhlcvService {

    /*
     * Spring Data JPA + Hibernate section
     */

    public List<YahooOHLCV> getYahooOhlcvData(String symbol, LocalDateTime startDate) throws IOException {
        return getYahooOhlcvData(symbol, startDate, LocalDateTime.now());
    }

    public List<YahooOHLCV> getYahooOhlcvData(String symbol, LocalDateTime startDate, LocalDateTime endDate) throws IOException {

        String url = generateURL(symbol, startDate, endDate);        // Generate URL

        if (url == null) {
            return null;
        }

        BufferedReader reader = callURL(url);

        return parseCSVtoObject(reader);
    }

    private String generateURL(String symbol, LocalDateTime startDate, LocalDateTime endDate) {
        LocalTime now = LocalTime.now();                        // temporary variable with the current time
        if (now.getHour() == 0) {                            // if it is between 12:00 am and 1:00 am
            // 		then subtract one day from the end date (this is because Yahoo's servers are on pacific time)
            endDate = LocalDateTime.from(LocalDateTime.now().minus(1, ChronoUnit.DAYS));
        }

        if (startDate.isAfter(endDate)) {
            return null;
        }

        int a_startMonth, b_startDay, c_startYear;

        a_startMonth = startDate.getMonthValue()
                - 1;            // Yahoo uses zero based month numbering, this gets the beginning dates month
        b_startDay = startDate.getDayOfMonth();                    // this gets beginning dates day
        c_startYear = startDate.getYear();                        // this gets beginning dates year

        log.trace("Start Month (variable A): " + a_startMonth);
        log.trace("Start Day (variable B): " + b_startDay);
        log.trace("Start Year (variable C): " + c_startYear);

        int d_endMonth, e_endDay, f_endYear;
        d_endMonth = endDate.getMonthValue() - 1;                // Yahoo uses zero based month numbering,this gets todays month
        e_endDay = endDate.getDayOfMonth();                        // this gets todays day of month
        f_endYear = endDate.getYear();                            // this gets todays year

        log.trace("End Month (variable D): " + d_endMonth);
        log.trace("End Day (variable E): " + e_endDay);
        log.trace("End Year (variable f): " + f_endYear);

        String str = "http://ichart.finance.yahoo.com/table.csv?s="    // Generating the yahoo finance URL
                + symbol.toUpperCase() + "&a=" + a_startMonth + "&b=" + b_startDay + "&c=" + c_startYear
                + "&g=d&d=" + d_endMonth + "&e=" + e_endDay + "&f=" + f_endYear + "&ignore=.csv";

        log.debug("Using the following Yahoo URL:");
        log.debug(str);
        return str;
    }

    private BufferedReader callURL(String url) throws IOException {
        URL ur = new URL(url);
        HttpURLConnection HUC = (HttpURLConnection) ur.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(HUC.getInputStream()));

        reader.readLine();                                            //reads the first line, it's just headers

        return reader;
    }

    private List<YahooOHLCV> parseCSVtoObject(BufferedReader reader) {
        CsvToBean<YahooOHLCV> csvToBean = new CsvToBeanBuilder<YahooOHLCV>(reader).build();

        return csvToBean.parse();
    }
}
