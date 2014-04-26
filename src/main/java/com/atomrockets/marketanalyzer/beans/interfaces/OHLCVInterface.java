package com.atomrockets.marketanalyzer.beans.interfaces;

import java.math.BigDecimal;
import java.sql.Date;

public interface OHLCVInterface {	
	@Override
	public String toString();
	
	public long getId();
	public void setId(int id);
	public void setId(long id);

	public String getSymbol();
	public void setSymbol(String symbol);
	
	public Date getDate();
	public void setDate(Date date);
	
	public BigDecimal getOpen();
	public void setOpen(BigDecimal open);

	public BigDecimal getHigh();
	public void setHigh(BigDecimal high);
	
	public BigDecimal getLow();
	public void setLow(BigDecimal low);

	public BigDecimal getClose();
	public void setClose(BigDecimal close);
	
	public long getVolume();
	public void setVolume(long volume);
	public void setVolume(double volume);
	
	public BigDecimal getAdjClose();
	public void setAdjClose(BigDecimal adjClose);

	
}