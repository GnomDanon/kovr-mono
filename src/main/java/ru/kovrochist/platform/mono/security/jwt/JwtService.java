package ru.kovrochist.platform.mono.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kovrochist.platform.mono.dto.auth.JwtAuthenticationDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtService {

	private static final Logger LOGGER = LogManager.getLogger(JwtService.class);

	@Value("7de841c4eeb71fe1fbac5c65b84a1cef9bf576a5cce5bce609f74fcd98b31243")
	private String jwtSecret;

	public JwtAuthenticationDto generateAuthToken(String phone) {
		JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
		jwtDto.setAccessToken(generateJwtToken(phone));
		jwtDto.setRefreshToken(generateRefreshToken(phone));
		return jwtDto;
	}

	public JwtAuthenticationDto refreshBaseToken(String phone, String refreshToken) {
		JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
		jwtDto.setAccessToken(generateJwtToken(phone));
		jwtDto.setRefreshToken(refreshToken);
		return jwtDto;
	}

	public String getPhoneFromToken(String token) {
		Claims claims = Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.getSubject();
	}

	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser()
					.verifyWith(getSignInKey())
					.build()
					.parseSignedClaims(token)
					.getPayload();
			return true;
		} catch (ExpiredJwtException exception) {
			LOGGER.error("Expired JwtException", exception);
		} catch (UnsupportedJwtException exception) {
			LOGGER.error("Unsupported JwtException", exception);
		} catch (MalformedJwtException exception) {
			LOGGER.error("Malformed JwtException", exception);
		} catch (SecurityException exception) {
			LOGGER.error("Security JwtException", exception);
		} catch (Exception exception) {
			LOGGER.error("Invalid token", exception);
		}

		return false;
	}

	private String generateJwtToken(String phone) {
		Date date = Date.from(LocalDateTime.now().plusHours(8).atZone(ZoneId.systemDefault()).toInstant());
		return Jwts.builder()
				.subject(phone)
				.expiration(date)
				.signWith(getSignInKey())
				.compact();
	}

	private String generateRefreshToken(String phone) {
		Date date = Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant());
		return Jwts.builder()
				.subject(phone)
				.expiration(date)
				.signWith(getSignInKey())
				.compact();
	}

	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
