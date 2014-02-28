package com.atomrockets.marketanalyzer.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/*
 * From: http://kielczewski.eu/2013/11/setting-active-profile-and-property-sources-in-spring-mvc/
 * 
 * This configuration class has a list of property files and static inner 
 * class for each environment. The static inner classes are annotated with 
 * @Profile, that indicates the profile they should be active in. Each of 
 * these classes defines PropertySourcesPlaceholderConfigurer bean that is 
 * loaded with proper list of property files. In this case it�s just one 
 * file, for �dev� profile it will be `example-dev.properties� that contains
 *  one property:
 */
@Configuration
public class PropertySourcesConfig {

    private static final Resource[] DEV_PROPERTIES = new ClassPathResource[]{
            new ClassPathResource("dev.properties"),
    };
    private static final Resource[] PROD_PROPERTIES = new ClassPathResource[]{
            new ClassPathResource("prod.properties"),
    };

    @Profile("dev")
    public static class DevConfig {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            pspc.setLocations(DEV_PROPERTIES);
            return pspc;
        }
    }

    @Profile("prod")
    public static class ProdConfig {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            pspc.setLocations(PROD_PROPERTIES);
            return pspc;
        }
    }
}
