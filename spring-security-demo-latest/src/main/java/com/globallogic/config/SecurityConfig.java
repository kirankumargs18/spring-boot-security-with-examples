package com.globallogic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(auth -> {

			// these lines executed in order
			auth.mvcMatchers("/public").permitAll();

			// locks all requests
			auth.anyRequest().authenticated();

		})
				//this line shows form to login else website will show access denied
				.formLogin(Customizer.withDefaults()) 
				.build();

	}

	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user=User.builder()
				.username("kiran")
				.password("{noop}password")
				.roles("ADMIN")
				.build();
		return new  InMemoryUserDetailsManager(user);
	}
	

}
