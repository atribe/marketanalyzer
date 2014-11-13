package com.ar.marketanalyzer.plotting.amstockcharts.serializers;

import java.io.IOException;

import com.ar.marketanalyzer.plotting.amstockcharts.enums.Color;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ColorSerializer extends JsonSerializer<Color>{

	@Override
	public void serialize(Color amObject, JsonGenerator jsonGenerator,SerializerProvider arg2) throws IOException, JsonProcessingException {
		jsonGenerator.writeString(amObject.toString());
	}
}
