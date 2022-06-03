package com.refresher.demo.Util;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AccessDeniedException {
        String bearerHeader = request.getHeader("Authorization");
        if (bearerHeader != null && !bearerHeader.isBlank() && bearerHeader.startsWith("Bearer ")) {
            String token = bearerHeader.substring(7);
            if (token.isBlank()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token is invalid.");
                return;
            } else {
                try {
                    String username = jwtUtil.verifyToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null)
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } catch (JWTVerificationException err) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token is invalid.");
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
