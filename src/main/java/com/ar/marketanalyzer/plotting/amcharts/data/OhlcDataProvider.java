package com.ar.marketanalyzer.plotting.amcharts.data;

import java.util.ArrayList;
import java.util.List;

import com.ar.marketanalyzer.plotting.amcharts.data.OhlcData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class OhlcDataProvider implements DataProvider{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1021936029429193130L;

	private List<OhlcData> dataProvider = new ArrayList<OhlcData>();

	@Override
	public List<OhlcData> getDataProvider() {
		return dataProvider;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setDataProvider(List<? extends Object> dataProvider) {
		this.dataProvider = (List<OhlcData>) dataProvider;
	}


}
