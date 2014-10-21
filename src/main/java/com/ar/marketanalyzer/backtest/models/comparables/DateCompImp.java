package com.ar.marketanalyzer.backtest.models.comparables;

import java.sql.Date;

public interface DateCompImp extends Comparable<DateCompImp>{
	public Date getDate();
	public void setDate(Date date);
}
