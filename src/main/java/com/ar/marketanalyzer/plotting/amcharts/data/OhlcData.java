package com.ar.marketanalyzer.plotting.amcharts.data;

import java.math.BigDecimal;
import java.sql.Date;

import com.ar.marketanalyzer.plotting.amcharts.serializers.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
public class OhlcData extends Object{

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd")
	private Date date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	
	public OhlcData () {
		
	}
	public OhlcData(Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@JsonSerialize(using=BigDecimalSerializer.class)
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	@JsonSerialize(using=BigDecimalSerializer.class)
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	@JsonSerialize(using=BigDecimalSerializer.class)
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	@JsonSerialize(using=BigDecimalSerializer.class)
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}
}
