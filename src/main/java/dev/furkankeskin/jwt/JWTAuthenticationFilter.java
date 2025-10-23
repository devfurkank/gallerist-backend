package dev.furkankeskin.jwt;

import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        System.out.println("JWT Filter - Authorization header: " + (header != null ? header.substring(0, Math.min(30, header.length())) + "..." : "NULL"));
        
        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println("JWT Filter - No valid Bearer token found, skipping authentication");
            filterChain.doFilter(request, response);
            return;
        }
        String token;
        String username;

        token = header.substring(7);
        System.out.println("JWT Filter - Token extracted (first 20 chars): " + token.substring(0, Math.min(20, token.length())) + "...");
        try {
            username = jwtService.getUsernameByToken(token);
            System.out.println("JWT Filter - Username from token: " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println("JWT Filter - UserDetails loaded: " + (userDetails != null));
                if (userDetails != null && jwtService.isTokenValid(token)) {
                    System.out.println("JWT Filter - Token is valid, authenticating user");
                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(userDetails);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    System.out.println("JWT Filter - Authentication successful for user: " + username);
                } else {
                    System.out.println("JWT Filter - Token is invalid or expired");
                }
            }
        }catch (ExpiredJwtException ex){
            throw new BaseException(new ErrorMessage(token, MessageType.TOKEN_IS_EXPIRED));
        }
        catch (Exception e) {
            throw new BaseException(new ErrorMessage(token, MessageType.GENERAL_EXCEPTION));
        }
        filterChain.doFilter(request, response);
    }
}
