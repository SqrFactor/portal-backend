/**
 * 
 */
package com.sqrfactor.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.JdbcUtils;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sqrfactor.model.Login;
import com.sqrfactor.service.LoginService;

/**
 * @author Angad Gill
 *
 */
public class CustomRealm extends JdbcRealm { // JdbcRealm extends
												// AuthorizingRealm and
												// AuthenticatingRealm
	
	@Autowired
	private LoginService loginService;
	
	private static final Logger log = Logger.getLogger(CustomRealm.class);
	protected static final String DEFAULT_AUTHENTICATION_QUERY = "SELECT userName, userPassword, passwordSalt "
			+ "FROM user_login WHERE userName = ?";

	// @Inject protected UserDAO userDAO;
	public CustomRealm() {
		super();
		setName("CustomRealm");
		CustomCredentialsMatcher credentialMatcher = new CustomCredentialsMatcher();
		setCredentialsMatcher(credentialMatcher);
		log.info("CustomRealm instantiated!");
	}

//	protected String dataSourceName; // java:jboss/datasources/appDS -
//										// configured in shiro.ini
//
//	public String getDataSourceName() {
//		return dataSourceName; // java:jboss/datasources/appDS
//	}
//
//	public void setDataSourceName(String dataSourceName) {
//		this.dataSourceName = dataSourceName;
//		this.dataSource = getDataSourceFromJNDI(dataSourceName); // setting
//																	// datasource
//																	// to
//																	// JdbcRealm.datasource
//	}
//
//	private DataSource getDataSourceFromJNDI(String jndiDataSourceName) {
//		try {
//			InitialContext ic = new InitialContext();
//			return (DataSource) ic.lookup(jndiDataSourceName);
//		} catch (NamingException e) {
//			log.error("JNDI error while retrieving " + jndiDataSourceName, e);
//			throw new AuthorizationException(e);
//		}
//	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("Do Shiro doGetAuthenticationInfo...");
		// identify account to log to
		String username = String.valueOf(token.getPrincipal());
		checkNotNull(username, "Null usernames are not allowed by this realm.");
		log.info("---------------------------------------------------------------------");
		log.info("Principal: " + String.valueOf(token.getPrincipal()) + " - Credentials: "
				+ String.valueOf(token.getCredentials()));
		log.info("---------------------------------------------------------------------");
		String hashedPassword = null;
		ByteSource credentialsSalt = null;
		if ("admin".equalsIgnoreCase(username)) {
			hashedPassword = "admin";
		} else {
			
			Login login = loginService.findByUsername(username);
			checkNotNull(login, "No account found for user [" + username + "]");
			hashedPassword = login.getUserPassword();
			credentialsSalt = new SimpleByteSource(Base64.decode(login.getPasswordSalt()));
			
			
//			Login login = null;
//			// user = userDAO.findByUserName(username);
//			Connection conn = null;
//			try {
//				conn = dataSource.getConnection();
//				login = getLoginByUserName(conn, username);
//			} catch (SQLException e) {
//				throw new AuthenticationException("Unable to fetch user by username [" + username + "].", e);
//			} finally {
//				JdbcUtils.closeConnection(conn);
//			}
//			checkNotNull(login, "No account found for user [" + username + "]");
//			hashedPassword = login.getUserPassword();
//			credentialsSalt = new SimpleByteSource(Base64.decode(login.getPasswordSalt()));
		}
		// SimpleAuthenticationInfo implements SaltedAuthenticationInfo
		return new SimpleAuthenticationInfo(token.getPrincipal(), hashedPassword, credentialsSalt, getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	private Login getLoginByUserName(Connection conn, String username) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Login login = null;
		try {
			ps = conn.prepareStatement(DEFAULT_AUTHENTICATION_QUERY);
			ps.setString(1, username);
			rs = ps.executeQuery();
			// Loop over results - although we are only expecting one result,
			// since usernames should be unique
			boolean foundResult = false;
			while (rs.next()) {
				// Check to ensure only one row is processed
				if (foundResult) {
					throw new AuthenticationException(
							"More than one user row found for user [" + username + "]. Usernames must be unique."); // TODO
																													// work
																													// on
																													// this
																													// warning
				}
				login = new Login();
				login.setUserName(rs.getString("userName"));
				login.setUserPassword(rs.getString("userPassword"));
				login.setPasswordSalt(rs.getString("passwordSalt"));
				foundResult = true;
			}
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
		}
		return login;
	}

	private void checkNotNull(Object reference, String message) {
		if (reference == null) {
			throw new AuthenticationException(message);
		}
	}
}
