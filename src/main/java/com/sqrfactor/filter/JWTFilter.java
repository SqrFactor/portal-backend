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
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.web.filter.GenericFilterBean;

import com.amazonaws.util.StringUtils;
import com.sqrfactor.utils.AuthUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * @author Angad Gill
 *
 */
public class JWTFilter extends GenericFilterBean {

	private static final String AUTH_ERROR_MSG = "Please make sure your request has an Authorization header",
			EXPIRE_ERROR_MSG = "Token has expired", JWT_ERROR_MSG = "Unable to parse JWT",
			JWT_INVALID_MSG = "Invalid JWT token";

	public static final String AUTH_HEADER_KEY = "Authorization";

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Claims claims = null;

		// Do not validate Authorization param if Path equals below
		if (!request.getPathInfo().equals("/login/authenticate") && !request.getPathInfo().equals("/user/signup")
				&& !request.getPathInfo().equals("/user/verify") && !request.getPathInfo().equals("/user/register")) {

			final String authHeader = request.getHeader(AUTH_HEADER_KEY);

			if (StringUtils.isNullOrEmpty(authHeader) || authHeader.split(" ").length != 2
					|| !authHeader.startsWith("Bearer ")) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, AUTH_ERROR_MSG);
				return;
			}

			try {
				claims = (Claims) AuthUtils.decodeToken(authHeader);
			} catch (SignatureException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_INVALID_MSG);
				return;
			} catch(ExpiredJwtException e){
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, EXPIRE_ERROR_MSG);
				return;
			} 
			catch (Exception e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_ERROR_MSG);
				return;
			} 
			
		} 
		chain.doFilter(request, response);
		
	}

}