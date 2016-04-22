/**
 * 
 */
package com.sqrfactor.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * @author Angad Gill
 *
 */
public class JWTFilter extends GenericFilterBean {
	
	private String secretKey = "SQRFACTOR@Bhive";

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		
		//Do not validate Authorization param if Path equals below
		if(!request.getPathInfo().equals("/login/authenticate")){
			final String authHeader = request.getHeader("Authorization");
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header.");
			}

			final String token = authHeader.substring(7); // The part after "Bearer
															// "
			try {
				final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
			} catch (final SignatureException e) {
				throw new ServletException("Invalid token.");
			}
		}

		chain.doFilter(req, res);
	}

}