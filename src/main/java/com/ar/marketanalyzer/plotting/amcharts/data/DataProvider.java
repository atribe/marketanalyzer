package com.ar.marketanalyzer.plotting.amcharts.data;

import java.io.Serializable;
import java.util.List;

public interface DataProvider extends Serializable{
	public List<? extends Object>getDataProvider();
	public void setDataProvider(List<? extends Object> dataProvider);
}
