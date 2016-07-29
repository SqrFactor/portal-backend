/**
 * 
 */
package com.sqrfactor.util.cache;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Angad Gill
 *
 */
public class SessionMemcached {

	private Memcached memcached;

	public SessionMemcached() {
		memcached = new Memcached();
	}
	
	public void addSessionId(String userId, String sessionId){
		Set<String> sessions = (Set<String>) memcached.get(userId);
		if (sessions == null) {
			sessions = Collections.synchronizedSet(new HashSet<String>());
			sessions.add(sessionId);
			memcached.set(userId, sessions);
		} else {
			sessions.add(sessionId);
			memcached.replace(userId, sessions);
		}
	}

	public void removeSessionId(String userId, String sessionId) {
		Set<String> sessions = (Set<String>) memcached.get(userId);
		if (sessions == null) {
			sessions = Collections.synchronizedSet(new HashSet<String>());
		} else {
			sessions.remove(sessionId);
			memcached.replace(userId, sessions);
		}
	}

	public Set<String> listSessions(String userId) {
		Set<String> sessions = (Set<String>) memcached.get(userId);
		if (sessions == null) {
			sessions = Collections.synchronizedSet(new HashSet<String>());
		}
		return sessions;
	}
}
