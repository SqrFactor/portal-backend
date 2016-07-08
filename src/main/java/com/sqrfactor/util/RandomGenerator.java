package com.sqrfactor.util;

import java.security.SecureRandom;
import java.math.BigInteger;

/**
 * @author Angad Gill
 *
 */
public final class RandomGenerator {

	private static SecureRandom random = new SecureRandom();

	public static String nextRandom() {
	    return new BigInteger(130, random).toString(32);
	}	
}
