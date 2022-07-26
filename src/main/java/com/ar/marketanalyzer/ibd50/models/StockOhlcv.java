package com.ar.marketanalyzer.ibd50.models;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.models.YahooOHLCV;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ibd50_stock_ohlcv")
public class StockOhlcv{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, unique=true)
	private Integer id;
	
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	private Symbol ticker;
	
	@Column
	private LocalDateTime date;
	
	@Column(precision=12, scale=2)
	private BigDecimal open;
	
	@Column(precision=12, scale=2)
	private BigDecimal high;
	
	@Column(precision=12, scale=2)
	private BigDecimal low;
	
	@Column(precision=12, scale=2)
	private BigDecimal close;
	
	@Column(precision=0)
	private long volume;
	
	@Column(name="adjusted_close", precision=12, scale=2)
	private BigDecimal adjClose;
	
	/*
	 * Constructors
	 */
	//Empty constructed required to be a Java Bean
	public StockOhlcv() {}
	
	public StockOhlcv(YahooOHLCV y, Symbol ticker) {
		this.ticker = ticker;												//Setting the ticker to the passed ticker

		setDate(y.getDate());

		setOpen(BigDecimal.valueOf(y.getOpen()));
		setHigh(BigDecimal.valueOf(y.getHigh()));
		setLow(BigDecimal.valueOf(y.getLow()));
		setClose(BigDecimal.valueOf(y.getClose()));
		setVolume(y.getVolume());
		setAdjClose(BigDecimal.valueOf(y.getAdjClose()));
	}
	
	public StockOhlcv(String symbol, LocalDateTime date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, long volume, BigDecimal adjClose) {
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Symbol getTicker() {
		return ticker;
	}

	public void setTicker(Symbol ticker) {
		this.ticker = ticker;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public void setDate(String date) {
		this.date = LocalDateTime.parse(date);//Not the most elegant way to do it
	}
	public LocalDateTime getLocalDate() {
		return this.date;
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
		this.volume = Math.round(volume);
	}
	
	
	public BigDecimal getAdjClose() {
		return adjClose;
	}
	
	public void setAdjClose(BigDecimal adjClose) {
		this.adjClose = adjClose;
	}
	//End Getters and Setters

}
