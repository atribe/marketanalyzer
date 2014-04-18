package com.atomrockets.marketanalyzer.beans.interfaces;

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
	
	public double getOpen();
	public void setOpen(double open);

	public double getHigh();
	public void setHigh(double high);
	
	public double getLow();
	public void setLow(double low);

	public double getClose();
	public void setClose(double close);
	
	public long getVolume();
	public void setVolume(long volume);
	public void setVolume(double volume);
	
	public double getAdjClose();
	public void setAdjClose(double adjClose);

	
}