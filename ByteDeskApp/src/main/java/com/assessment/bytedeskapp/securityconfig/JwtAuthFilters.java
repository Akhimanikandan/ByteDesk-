package com.assessment.bytedeskapp.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.assessment.bytedeskapp.auth.AuthenticationService;
import com.assessment.bytedeskapp.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthFilters extends OncePerRequestFilter {

	private final AuthenticationService authenticationservice;

	public JwtAuthFilters(AuthenticationService authenticationservice, JwtService jwtService,
			UserDetailsService userDetailService, UserRepository userRepo) {
		super();
		this.jwtService = jwtService;
		this.userDetailService = userDetailService;
		this.userRepo = userRepo;
		this.authenticationservice = authenticationservice;
	}

	private final JwtService jwtService;

	private final UserRepository userRepo;
	private final UserDetailsService userDetailService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userName;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);

		userName = jwtService.extractUserName(jwt);
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userdetails = this.userDetailService.loadUserByUsername(userName);
			if (jwtService.isTokenValid(jwt, userdetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails,
						null, userdetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);
	}

}
