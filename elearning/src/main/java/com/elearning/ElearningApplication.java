package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningApplication.class, args);
	}

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	// 	manager.createUser(User.withUsername("user").password("password").roles("USER").build());
	// 	return manager;
	// }

}
