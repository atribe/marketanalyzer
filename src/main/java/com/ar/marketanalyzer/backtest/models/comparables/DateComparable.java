package com.ar.marketanalyzer.backtest.models.comparables;

import java.util.Comparator;

public class DateComparable implements Comparator<DateCompImp> {

	@Override
	public int compare(DateCompImp d1, DateCompImp d2) {
		return d1.getDate().compareTo(d2.getDate());
	}

}
