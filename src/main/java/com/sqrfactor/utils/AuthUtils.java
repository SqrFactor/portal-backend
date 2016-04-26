/**
 * 
 */
package com.sqrfactor.utils;

import org.joda.time.DateTime;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * @author Angad Gill
 *
 */
public class AuthUtils {

	private static final String TOKEN_SECRET = "SQRFACTOR@Bhive";
	private static final String ISSUER = "https://www.sqrfactor.in";

	public static Claims decodeToken(String input) throws SignatureException, ExpiredJwtException {
		Claims claims = null;
		try {
			final String token = input.substring(7); // The part after "Bearer"
			claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
		} catch (final SignatureException e) {
			throw e;
		} catch(final ExpiredJwtException e){
			throw e;
		}
		return claims;
	}

	public static String createToken(String username, long userId) {
		String token = Jwts.builder().setSubject(username)
				// Return Role in future
				.claim("userId", userId).setIssuedAt(DateTime.now().toDate()).setIssuer(ISSUER)
				.setExpiration(DateTime.now().plusDays(10).toDate()).signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
				.compact();
		return token;
	}
}