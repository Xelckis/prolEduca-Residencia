package com.example.prol_educa.security.jwt;


import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.prol_educa.security.services.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationMs}")
	private Integer jwtExpirationMs;

	public JwtUtils() {}

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class.getSimpleName());

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
			.setSubject((userPrincipal.getEmail()))
			.claim("roles", userPrincipal.getAuthorities())
			.setIssuedAt(new Date())
			.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
			.signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes(StandardCharsets.UTF_8))
			.compact();
	}

	public Long getIdFromJwt(String token) {
		token = token.replaceAll("Bearer ", "");
		Claims claims = claims(token);

		return claims.get("id", Long.class);
	}

	public String getEmailNameFromJwtToken(String token) {
		Claims claims = claims(token);
		return claims.getSubject();
	}

	public boolean validateJwtToken(String authToken) {

		try {
			Jwts.parser().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

	public Boolean verifyExpiration(String tokenJwt) {
		Claims claims = claims(tokenJwt);
		Instant expirationTime = claims.getExpiration().toInstant();
		return expirationTime.isAfter(Instant.now());
	}

	public LocalDateTime expiresAt(String tokenJwt) {
		Claims claims = claims(tokenJwt);
		Instant expirationTime = claims.getExpiration().toInstant();

		return expirationTime.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public boolean decoderJwtEmail(String tokenJwt, String email) throws Exception {
		Claims jwt_claims = claims(tokenJwt);
		if (jwt_claims.getSubject().contentEquals(email)) {
			return true;
		} else {
			throw new Exception("Não autorizado");
		}
	}

	public Claims claims(String tokenJwt) {
		return Jwts.parser()
				.setSigningKey(jwtSecret.getBytes()) 
				.parseClaimsJws(tokenJwt)
				.getBody();
	}

}
