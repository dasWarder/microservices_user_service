package com.example.microservices_userservice.security;

import com.example.microservices_userservice.security.filters.AuthenticationFilter;
import com.example.microservices_userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${uri.gateway_ip}")
  private String gatewayIp;

  @Value("${token.secret}")
  private String secret;

  @Value("${uri.login}")
  private String loginPath;

  @Value("${token.expirationTime}")
  private Long expirationDate;

  private final UserService userService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/**")
        .hasIpAddress(gatewayIp)
        .and()
        .addFilter(getAuthenticationFilter())
        .headers()
        .frameOptions();
  }

  private Filter getAuthenticationFilter() throws Exception {

    AuthenticationFilter filter =
        new AuthenticationFilter(userService, authenticationManager(), secret, expirationDate);
    filter.setFilterProcessesUrl(loginPath);

    return filter;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }
}
