package com.assessment.bytedeskapp.securityconfig;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRETKEY = "462D4A614E645267556B58703273357638792F423F4528482B4D625065536856"; 
	public String extractUserName(String token) {
		return extract(token,Claims::getSubject);
		
	}
    public <T> T extract (String token,Function<Claims,T> Resolver) {
    	final Claims claims= extractAllClaims(token);
    	return Resolver.apply(claims);
    }
    
    
    public String generateToken(UserDetails userdetails) {
		return generateToken(new HashMap<>(),userdetails);
    	
    }
    
    public boolean isTokenValid(String token,UserDetails userdetails) { //to validate if the token given here belongs to userdetails
    	final String username=extractUserName(userdetails.getUsername());
    	return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) { 
		return expiration(token).before(new Date()) ;
	}
	private Date expiration(String token) {
		return extract(token,Claims::getExpiration);// to get the expiration date
	}
	public String generateToken(
    		Map<String,Object> extractClaims,
    		UserDetails userdetails) {
		return Jwts
    			.builder()
    			.setClaims(extractClaims)
    			.setSubject(userdetails.getUsername())
    			.setIssuedAt(new Date(System.currentTimeMillis()))
    			.setExpiration(new Date(System.currentTimeMillis()+1000*60))
    			.signWith(getSigningKey(),SignatureAlgorithm.HS256)
    			.compact();
    	
    }
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()// from io.jsonwetoken dependencies
				.setSigningKey(getSigningKey())// when we generate or decode a token we need Signingkey
				.build()
				.parseClaimsJws(token)
				.getBody();
			
				   
				
		
	}

	private Key getSigningKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRETKEY);
		return Keys.hmacShaKeyFor(keyBytes); 
	}
}
