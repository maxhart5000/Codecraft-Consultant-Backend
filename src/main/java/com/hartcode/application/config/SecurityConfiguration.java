/*
 * Configuration class for security settings.
 */
package com.hartcode.application.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    /**
     * Configures security filters.
     *
     * @param http HttpSecurity object to configure security settings.
     * @return SecurityFilterChain configured security filter chain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Protect endpoint /api/orders
        http.authorizeHttpRequests(requests ->
                        requests.requestMatchers("/api/orders/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll())
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(Customizer.withDefaults()));

        // Add CORS filters
        http.cors(Customizer.withDefaults());

        // Content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // Non-empty response body for 401 (more friendly)
        Okta.configureResourceServer401ResponseBody(http);

        // We are not using cookies for session tracking, disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
