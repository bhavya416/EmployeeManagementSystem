package com.ems.ems.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String key;
	
	public JwtService() {
		try {
			KeyGenerator keyGen =KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk =keyGen.generateKey();
			key=Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String generateJwtToken(String username) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts.builder()
				.claims().add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+60*60*1000))
				.and()
				.signWith(getKey())
				.compact();
				
	}

	private SecretKey getKey() {
		byte[] secretKey =Decoders.BASE64.decode(key);
		return Keys.hmacShaKeyFor(secretKey);
	}
	// Extracts the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extracts expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic method to extract claims from JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Parses the token and retrieves all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
        		.verifyWith(getKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();

    }

    // Checks if the token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return (extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
