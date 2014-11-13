package com.ar.marketanalyzer.plotting.amstockcharts.serializers;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BigDecimalSerializer extends JsonSerializer<Object>{

	@Override
	public void serialize(Object amObject, JsonGenerator jsonGenerator,SerializerProvider arg2) throws IOException, JsonProcessingException {

		//Formatting for the BigDecimals
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(false);
		jsonGenerator.writeString(df.format(amObject));
		
	}
}
