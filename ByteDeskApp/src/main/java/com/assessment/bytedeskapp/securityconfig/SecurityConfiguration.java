package com.assessment.bytedeskapp.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   
	
	public SecurityConfiguration(JwtAuthFilters jwtAuthFilter, AuthenticationProvider authenticationProvider) {
		super();
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
	}
	private final JwtAuthFilters jwtAuthFilter;
	
	private final AuthenticationProvider authenticationProvider;
	
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.cors()
		.and()
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers("/user/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}
	
}
