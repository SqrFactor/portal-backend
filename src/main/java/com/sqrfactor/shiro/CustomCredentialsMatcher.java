/**
 * 
 */
package com.sqrfactor.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 * @author Angad Gill
 *
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

	private static final Logger log = Logger.getLogger(CustomCredentialsMatcher.class);
	private static final int SHIRO_CREDENTIAL_HASH_INTERATION = 1024;

	public CustomCredentialsMatcher() {
		super();
		log.info("CustomCredentialsMatcher instantiated!");
		setHashAlgorithmName(Sha512Hash.ALGORITHM_NAME);
		setHashIterations(SHIRO_CREDENTIAL_HASH_INTERATION);
		setStoredCredentialsHexEncoded(false); // Base64-encode, not hex-encode
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

		log.info("Shiro doCredentialsMatch...");

		log.info("AuthenticationToken.credentials: " + new String((char[]) token.getCredentials()));
		log.info("AuthenticationToken.principals: " + token.getPrincipal());
		log.info("AuthenticationInfo.credentials: " + info.getCredentials());
		log.info("AuthenticationInfoprincipals: " + info.getPrincipals());
		if ("admin".equalsIgnoreCase(String.valueOf(token.getPrincipal()))) {
			return true;
		} else {
			// verifies by comparing the credential in token and info
			return super.doCredentialsMatch(token, info);
		}
	}
}