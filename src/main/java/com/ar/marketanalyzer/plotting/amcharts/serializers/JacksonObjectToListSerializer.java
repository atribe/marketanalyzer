package com.ar.marketanalyzer.plotting.amcharts.serializers;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.ar.marketanalyzer.plotting.amcharts.buildingblock.ValueAxis;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonObjectToListSerializer extends JsonSerializer<ValueAxis>{

	@Override
	public void serialize(ValueAxis valueAxis, JsonGenerator jsonGenerator,SerializerProvider arg2) throws IOException, JsonProcessingException {
		
		jsonGenerator.writeStartArray();
		jsonGenerator.writeStartObject();
		try {
			for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(ValueAxis.class).getPropertyDescriptors()){

			    // propertyEditor.getReadMethod() exposes the getter
			    // btw, this may be null if you have a write-only property
			    //System.out.println(propertyDescriptor.getReadMethod());
				Object value = propertyDescriptor.getReadMethod().invoke(valueAxis);
				if(value != null && !propertyDescriptor.getName().equals("class")) {
					
					jsonGenerator.writeObjectField(propertyDescriptor.getName(), value);
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndArray();
		
		
	}

}
