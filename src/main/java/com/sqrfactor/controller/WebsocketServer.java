package com.sqrfactor.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.sqrfactor.util.cache.SessionMemcached;

/**
 * 
 * @author Angad Gill
 *
 */
@Component
@ServerEndpoint("/websocket")
public class WebsocketServer {

	private static SessionMemcached sessionMemcached = new SessionMemcached();

	private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

	/**
	 * @OnOpen allows us to intercept the creation of a new session. The session
	 *         class allows us to send data to the user. In the method onOpen,
	 *         we'll let the user know that the handshake was successful.
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session.getId() + " has opened a connection");
		try {
			session.getBasicRemote().sendText("Connection Established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		String userId = session.getRequestParameterMap().get("userId").get(0);
		sessionMemcached.addSessionId(userId, session.getId());
		sessions.add(session);
	}

	/**
	 * When a user sends a message to the server, this method will intercept the
	 * message and allow us to react to it. For now the message is read as a
	 * String.
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from " + session.getId() + ": " + message);
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		String userId = session.getRequestParameterMap().get("userId").get(0);

		sendMessageToUser(Long.parseLong(userId), "From Memcache");
	}

	/**
	 * The user closes the connection.
	 * 
	 * Note: you can't send messages to the client from this method
	 */
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " + session.getId() + " has ended");
		String userId = session.getRequestParameterMap().get("userId").get(0);
		sessionMemcached.removeSessionId(userId, session.getId());
		sessions.remove(session);
	}

	public void sendMessageToUser(long userId, String message) {
		for (Session session : sessions) {
			for (String sessionId : sessionMemcached.listSessions(Long.toString(userId))) {
				if (session.getId().equals(sessionId)) {
					try {
						session.getBasicRemote().sendText(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}