package com.comsense.csvupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;

@Configuration
public class AppSecurityConfig {

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    
    	 http
         .csrf(csrf -> csrf.disable())  
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/api/csv/upload","/api/csv/fetchData",
            		 		  "/swagger-ui/api/csv/upload",
            		 		 "/swagger-ui/**",
            		 		  "/v3/api-docs/**",
                     		  "/swagger-ui.html").permitAll()  // Allow public access
             .anyRequest().authenticated()  // Secure other endpoints
         );
     return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Suraj")
                .password("Suraj@123")
                .roles("Developer")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}