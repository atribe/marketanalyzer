package com.ar.marketanalyzer.ibd50.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.indexbacktest.beans.YahooOHLCV;

@Entity
@Table(name = "ibd50_stock_ohlcv")
public class StockOhlcv {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="stock_ohlcv_id", nullable=false, unique=true, length=10)
	private int id;
	
	
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="ticker_symbol_id")
	private TickerSymbol ticker;
	
	@Column
	private Date date;
	
	@Column(precision=2)
	private BigDecimal open;
	
	@Column(precision=2)
	private BigDecimal high;
	
	@Column(precision=2)
	private BigDecimal low;
	
	@Column(precision=2)
	private BigDecimal close;
	
	@Column(precision=0)
	private long volume;
	
	@Column(name="adjusted_close", precision=2)
	private BigDecimal adjClose;
	
	/*
	 * Constructors
	 */
	//Empty constructed required to be a Java Bean
	public StockOhlcv() {}
	
	public StockOhlcv(YahooOHLCV y, int symbol_id) {
		//setSymbol(y.getSymbol());
		//setSymbol_id(symbol_id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date parsed=null;
		try {
			parsed = format.parse(y.getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setDate(new java.sql.Date(parsed.getTime()));
		setOpen(new BigDecimal(y.getOpen()));
		setHigh(new BigDecimal(y.getHigh()));
		setLow(new BigDecimal(y.getLow()));
		setClose(new BigDecimal(y.getClose()));
		setVolume(y.getVolume());
		setAdjClose(new BigDecimal(y.getAdjClose()));
	}
	
	public StockOhlcv(String symbol, Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, long volume, BigDecimal adjClose) {
		//setSymbol(symbol);
		setDate(date);
		setOpen(open);
		setHigh(high);
		setLow(low);
		setClose(close);
		setVolume(volume);
		setAdjClose(adjClose);
	}
	//End Constructors
	
	
	/*
	 * Getters and Setters
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TickerSymbol getTicker() {
		return ticker;
	}

	public void setTicker(TickerSymbol ticker) {
		this.ticker = ticker;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDate(String date) {
		this.date = Date.valueOf(date);//Not the most elegant way to do it
	}
	public LocalDate getLocalDate() {
		return new LocalDate(this.date);
	}
		
	public BigDecimal getOpen() {
		return open;
	}
	
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	
	
	public BigDecimal getHigh() {
		return high;
	}
	
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	
	public BigDecimal getLow() {
		return low;
	}
	
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	
	
	public BigDecimal getClose() {
		return close;
	}
	
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	
	
	public long getVolume() {
		return volume;
	}
	
	public void setVolume(long volume) {
		this.volume = volume;
	}
	
	public void setVolume(double volume) {
		volume = Math.round(volume);
	}
	
	
	public BigDecimal getAdjClose() {
		return adjClose;
	}
	
	public void setAdjClose(BigDecimal adjClose) {
		this.adjClose = adjClose;
	}
	//End Getters and Setters

}
