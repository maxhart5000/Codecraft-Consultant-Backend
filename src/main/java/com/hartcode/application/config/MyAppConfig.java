/*
 * Configuration class for customizing application settings.
 */
package com.hartcode.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyAppConfig implements WebMvcConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    /**
     * Adds CORS mappings for the specified allowed origins.
     *
     * @param cors CorsRegistry object for configuring CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry cors) {
        // Set up CORS mapping
        cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins);
    }
}
