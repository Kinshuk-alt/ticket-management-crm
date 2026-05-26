package com.ticket.stms.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean

    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .csrf(
                        csrf -> csrf.disable()
                )

                .userDetailsService(
                        customUserDetailsService
                )

                .authorizeHttpRequests(

                        auth -> auth

                                .requestMatchers(
                                        "/api/users/register",
                                        "/api/users/login"
                                )

                                .permitAll()

                                .requestMatchers(
                                        "/api/dashboard/**"
                                )

                                .hasRole(
                                        "ADMIN"
                                )

                                .requestMatchers(
                                        "/api/tickets/assign/**"
                                )

                                .hasAnyRole(
                                        "ADMIN",
                                        "AGENT"
                                )

                                .requestMatchers(
                                        "/api/comments/**"
                                )

                                .hasAnyRole(
                                        "ADMIN",
                                        "AGENT",
                                        "USER"
                                )

                                .anyRequest()

                                .authenticated()

                )

                .httpBasic(
                        basic -> {}
                )

                .formLogin(
                        form -> form.disable()
                );

        return http.build();

    }

    @Bean

    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

    @Bean

    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {

        return config.getAuthenticationManager();

    }

}