package com.learning.curd.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learning.curd.security.filter.JwtFilterRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth-> 
			auth.requestMatchers("/home/**", "/user/register", "/api/auth/login")
		.permitAll()
		.anyRequest().authenticated())
		.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class)
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	 
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
