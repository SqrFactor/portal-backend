/**
 * 
 */
package com.sqrfactor.util.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author Angad Gill
 *
 */
public class SessionMap {
	private Map<String, Set<String>> sessionMap = Collections.synchronizedMap(new HashMap<>());	

	public SessionMap() {
		
	}
	
	public void addSessionId(String userId, String sessionId){
		
		Set<String> sessions = sessionMap.get(userId);
		if (sessions == null) {
			sessions = Collections.synchronizedSet(new HashSet<String>());
			sessions.add(sessionId);
			sessionMap.put(userId, sessions);
		} else {
			sessions.add(sessionId);
			sessionMap.replace(userId, sessions);
		}
	}

	public void removeSessionId(String userId, String sessionId) {
		Set<String> sessions = (Set<String>) sessionMap.get(userId);
		if (sessions == null) {
			sessions = Collections.synchronizedSet(new HashSet<String>());
		} else {
			sessions.remove(sessionId);
			sessionMap.replace(userId, sessions);
		}
	}

	public Set<String> listSessions(String userId) {
		Set<String> sessions = (Set<String>) sessionMap.get(userId);
		if (sessions == null) {
			sessions = Collections.synchronizedSet(new HashSet<String>());
		}
		return sessions;
	}
}
