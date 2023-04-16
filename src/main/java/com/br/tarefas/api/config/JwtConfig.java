package com.br.tarefas.api.config;

import com.br.tarefas.api.constantes.PathEndpoints;
import com.br.tarefas.infrastructure.security.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {

        FilterRegistrationBean<JwtAuthenticationFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new JwtAuthenticationFilter());
        // mapear os endpoints da API que precisam de autenticação
        filter.addUrlPatterns(PathEndpoints.ATUALIZAR_DADOS);
        return filter;
    }
}
