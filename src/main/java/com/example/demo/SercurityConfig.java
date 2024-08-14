package com.example.demo;

import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SercurityConfig {

    @Autowired
    AccountService accountService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            PasswordEncoder pass = passwordEncoder();
            return accountService.findById(username)
                    .map(acc -> {
                        String[] roles = acc.getAuthorities().stream()
                                .map(authority -> authority.getRole().getId())
                                .toArray(String[]::new);

                        UserDetails user = User.builder()
                                .username(acc.getUsername())
                                .password(pass.encode(acc.getPassword()))
                                .roles(roles)
                                .build();
                        return user;
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/order/**").authenticated()
                                .requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
                                .requestMatchers("/rest/authorities").hasRole("DIRE")
                                .requestMatchers("/rest/orders/**").permitAll()
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/security/login/form")
                                .loginProcessingUrl("/security/login")
                                .defaultSuccessUrl("/security/login/success", false)
                                .failureUrl("/security/login/error")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/security/logoff")
                                .logoutSuccessUrl("/security/logoff/success")
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedPage("/login/unauthorized")
                );

        return http.build();
    }
}
