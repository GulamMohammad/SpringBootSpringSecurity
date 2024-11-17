package com.becoder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("Usman08@")).roles("USER").build();
		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("Usman2022@")).roles("ADMIN").build();
		
		/*
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("Usman08@").roles("USER").build();
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("Usman2022@").roles("ADMIN").build();
		*/
	/*
		return new InMemoryUserDetailsManager(user,admin);
	}
	 */
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(req -> 
					req.requestMatchers("register","login").permitAll()
					.anyRequest().authenticated())
			//.formLogin(Customizer.withDefaults()) // for defaultPage given by Spring Security
			.httpBasic(Customizer.withDefaults()) // for enable postman
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // for validating token
			;
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	

}
