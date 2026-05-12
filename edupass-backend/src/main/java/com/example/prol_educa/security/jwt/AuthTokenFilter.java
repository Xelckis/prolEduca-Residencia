package com.example.prol_educa.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.prol_educa.security.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl userDetailsServiceImpl;

  public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsServiceImpl) {
    this.jwtUtils = jwtUtils;
    this.userDetailsServiceImpl = userDetailsServiceImpl;
  }

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
  //     throws ServletException, IOException {
  //   try {

  //     String jwt = parseJwt(request);

  //     if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

  //       String username = jwtUtils.getEmailNameFromJwtToken(jwt);

  //       UserDetails userDetails;

  //       try {
  //         userDetails = userDetailsServiceImpl.loadCustomerByEmail(username);
  //       } catch (UsernameNotFoundException e) {
  //         // Tenta autenticar como Administrador
  //         userDetails = userDetailsServiceImpl.loadAdminByEmail(username);
  //       }
  //       UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
  //           userDetails.getAuthorities());

  //       authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
  //       SecurityContextHolder.getContext().setAuthentication(authentication);
  //     }
  //   } catch (Exception e) {
  //     logger.error("Cannot set user authentication: {}", e);
  //   }

  //   filterChain.doFilter(request, response);
  // }

  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = parseJwt(request);

      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getEmailNameFromJwtToken(jwt);
        UserDetails userDetails = null;

        // Tenta autenticar em sequência: Customer → Admin → Company
        try {
          userDetails = userDetailsServiceImpl.loadCustomerByEmail(username);
        } catch (UsernameNotFoundException e1) {
          try {
            userDetails = userDetailsServiceImpl.loadAdminByEmail(username);
          } catch (UsernameNotFoundException e2) {
            try {
              userDetails = userDetailsServiceImpl.loadCompanyByEmail(username);
            } catch (UsernameNotFoundException e3) {
              // Nenhum usuário encontrado: deixa userDetails como null
            }
          }
        }

        // Se encontrou um userDetails válido, autentica
        if (userDetails != null) {
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
              null, userDetails.getAuthorities());

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e.getMessage());
    }

    filterChain.doFilter(request, response);
  }

  public String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }
}
