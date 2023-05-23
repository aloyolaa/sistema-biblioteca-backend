package com.republicaargentina.sistemabibliotecabackend.auth;

import com.republicaargentina.sistemabibliotecabackend.auth.filter.JwtAuthenticationFilter;
import com.republicaargentina.sistemabibliotecabackend.auth.filter.JwtValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests()
                //.requestMatchers("/api/v1/**").permitAll()
                /*.requestMatchers("/api/v1/usuarios/**").permitAll()
                .requestMatchers("/api/v1/libros/**").permitAll()
                .requestMatchers("/api/v1/roles/").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/usuarios/{id}").hasAnyRole(ADMIN, USER)
                .requestMatchers(HttpMethod.POST, "/api/v1/usuarios/save").hasRole(ADMIN)
                .requestMatchers("/api/v1/usuarios/**").hasRole(ADMIN)*/
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
