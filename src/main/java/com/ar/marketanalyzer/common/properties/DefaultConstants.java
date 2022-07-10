package com.ar.marketanalyzer.common.properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultConstants {
    @Value( "${default.ModelMonths:330}" )
    public static int DEFAULT_MONTHS_OF_DATA;
}
