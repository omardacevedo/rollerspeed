package org.example.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Eliminamos la dependencia del filtro por ahora para simplificar
    // private final JwtAuthenticationFilter jwtAuthenticationFilter;
    // public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
    //     this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    // } Move-Item -Path "src\main\java\config\SecurityConfig.java" -Destination "src\main\java\com\ejemplo\config\SecurityConfig.java"

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Configurando SecurityFilterChain - Permitiendo todo");
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}