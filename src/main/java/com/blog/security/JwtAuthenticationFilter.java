package com.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	//will hit this method whenever api req is there
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get token from req
		String requestToken = request.getHeader("Authorization");
		
		System.out.println(requestToken);
		
		String username = null;
		
		String token = null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			
			token = requestToken.substring(7);
			try {
			username = this.jwtTokenHelper.getUsernameFromToken(token);
			}catch(IllegalArgumentException e) {
				System.out.println("unable to get jwt token");
			}catch(ExpressionException e) {
				System.out.println("Jwt token has expired");
			}catch(MalformedJwtException e) {
				System.out.println("Invalid jwt");
			}
		}else {
			System.out.println("Jwt token does not begin with bearer");
		}
		
		//once we get the token, now validate
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.detailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("invalid jwt token");
			}
		}else {
			System.out.println("username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
