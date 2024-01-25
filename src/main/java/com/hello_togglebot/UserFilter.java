package com.hello_togglebot;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.devcycle.sdk.server.common.model.DevCycleUser;

@Component
public class UserFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Define a DevCycle User object for the current request
    DevCycleUser user = DevCycleUser.builder()
        .userId("example_user_id")
        .country("US")
        .build();

    request.setAttribute("user", user);
    filterChain.doFilter(request, response);
  }
}