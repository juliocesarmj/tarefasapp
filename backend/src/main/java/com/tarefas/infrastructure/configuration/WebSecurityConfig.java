package com.tarefas.infrastructure.configuration;

import com.tarefas.domain.infrastructure.security.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.addFilterAfter(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers(HttpMethod.POST, "/v1/usuario/novo").permitAll()
				.antMatchers(HttpMethod.POST, "/v1/usuario/auth").permitAll()
				.antMatchers(HttpMethod.POST, "/v1/tarefas").permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll().anyRequest().authenticated();

		return http.build();
	}
}
