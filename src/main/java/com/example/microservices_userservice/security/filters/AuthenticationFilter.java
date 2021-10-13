package com.example.microservices_userservice.security.filters;

import com.example.microservices_userservice.dto.LoginRequest;
import com.example.microservices_userservice.dto.Token;
import com.example.microservices_userservice.exception.UserNotFoundException;
import com.example.microservices_userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private String secret;

  private Long expirationDate;

  private ObjectMapper objectMapper = new ObjectMapper();

  private UserService userService;

  public AuthenticationFilter(
      UserService userService, AuthenticationManager authenticationManager, String secret, Long expirationDate) {
    this.userService = userService;
    this.secret = secret;
    this.expirationDate = expirationDate;
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
      LoginRequest cred =
          new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

      return getAuthenticationManager()
          .authenticate(
              new UsernamePasswordAuthenticationToken(
                  cred.getEmail(), cred.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    try {

      String email = ((User) authResult.getPrincipal()).getUsername();
      com.example.microservices_userservice.model.User userDetails =
          userService.getUserByEmail(email);

      Date date = new Date((new Date()).getTime() + expirationDate);

      String token =
          Jwts.builder()
              .setSubject(userDetails.getEmail())
              .setExpiration(date)
              .signWith(SignatureAlgorithm.HS512, secret)
              .compact();
      Token responseToken = new Token(token, userDetails.getEmail(), date);
      response.addHeader("token", token);
      response.addHeader("email", email);
//      response.setContentType(new String("APPLICATION_JSON"));
//      response.getWriter().write(objectMapper.writeValueAsString(responseToken));

    } catch (UserNotFoundException e) {
      log.error(
          String.format(
              "The error in class: %s with message: %s has occurred",
              e.getCause().getClass(), e.getCause().getMessage()));
    }
  }
}
