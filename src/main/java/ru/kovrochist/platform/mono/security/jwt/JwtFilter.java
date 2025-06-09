package ru.kovrochist.platform.mono.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kovrochist.platform.mono.security.user.CommonUserDetails;
import ru.kovrochist.platform.mono.security.user.User;
import ru.kovrochist.platform.mono.service.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private static final String BEARER = "Bearer ";

	private final JwtService jwtService;
	private final UserService userService;

	private final User USER;

	private static final Logger LOGGER = LogManager.getLogger(JwtFilter.class);

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
									@NonNull HttpServletResponse response,
									@NonNull FilterChain filterChain) throws ServletException, IOException {
		LOGGER.info(String.format("Method: %s, Request: %s", request.getMethod(), request.getRequestURI()));

		String token = getTokenFromRequest(request);
		if (token != null && jwtService.validateJwtToken(token)) {
			setCommonUserDetailsToSecurityContextHolder(token);
		}
		filterChain.doFilter(request, response);
	}

	private void setCommonUserDetailsToSecurityContextHolder(String token) {
		String phone = jwtService.getPhoneFromToken(token);
		CommonUserDetails commonUserDetails = userService.loadUserByUsername(phone);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(commonUserDetails,
				null, commonUserDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		saveUserInfo(commonUserDetails);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (bearerToken != null && bearerToken.startsWith(BEARER))
			return bearerToken.substring(BEARER.length());
		return null;
	}

	private void saveUserInfo(CommonUserDetails userDetails) {
		USER.setId(userDetails.getId());
		USER.setRole(userDetails.getRole());
	}
}
