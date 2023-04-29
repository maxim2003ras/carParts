package com.carparts.carparts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/showParts/**").permitAll()
                .requestMatchers("/myCart").authenticated()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/createUser/**").permitAll()
                .requestMatchers("/error/**").permitAll()
                .requestMatchers("/itemInfo/**").permitAll()
                .requestMatchers("/addToCart/**").authenticated()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/addNewItem").hasRole("ADMIN")
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/showResults/**").permitAll()
                .requestMatchers("/deleteFromCart/**").authenticated()
                .requestMatchers("/saveImage/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/myCart").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}