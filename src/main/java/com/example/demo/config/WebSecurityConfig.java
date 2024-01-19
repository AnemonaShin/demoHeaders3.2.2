package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	/**
	 * Metodo que hará de filtro de seguridad para la respuesta final del servicio.
	 * 
	 * Dentro de este se encontrarán las cabeceras:
	 * Cache Control, Strict Transport Security, X-XSS-Protection, 
	 * Content Security Policy, X-Frame-Options, X-Content-Type-Options, 
	 * Referrer-Policy, Expect-CT, Permissions-Policy
	 * 
	 * @param http
	 * @return SecurityFilterChain with http.build()
	 * @throws Exception
	 */
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http  
			.headers(headers -> headers
				//Strict Transport Security
				.httpStrictTransportSecurity(hsts -> hsts
					.includeSubDomains(true)
					.maxAgeInSeconds(31536000)
					)
				//X-XSS-Protection
				.xssProtection(xss -> xss
					.headerValue(HeaderValue.ENABLED_MODE_BLOCK))
				//Content Security Policy
				.contentSecurityPolicy(csp -> csp
					.policyDirectives("upgrade-insecure-requests; base-uri 'self'; frame-ancestors 'self'; form-action 'self'; object-src 'none';"))
				//X-Frame-Options
				.frameOptions(frameOptions -> frameOptions
					.sameOrigin())
				//X-Content-Type-Options posee valor default "no-sniff"
				//Referrer-Policy
				.referrerPolicy(rfp -> rfp
					.policy(ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
				//Expect-CT (Deprecado se usará una custom)
				.addHeaderWriter(new StaticHeadersWriter("Expect-CT","default"))
				//Permissions-Policy
				.permissionsPolicy(pmp -> pmp
					.policy("default"))
			);
		
		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
      		.permitAll())
      			.csrf(AbstractHttpConfigurer::disable);


        return http.build();
	}

}