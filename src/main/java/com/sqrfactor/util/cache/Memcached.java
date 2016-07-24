/**
 * 
 */
package com.sqrfactor.util.cache;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

/**
 * @author Angad Gill
 *
 */
class Memcached {

	private MemcachedClient mcc;

	private int EXPIRATION_TIME = 0;// never expire

	public Memcached() {
		init();
	}

	public void init() {
		try {
			mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		} catch (IOException e) {
			System.out.println("Could not connect to memcached server.");
		}
	}

	public void add(String key, Object value) {
		mcc.add(key, EXPIRATION_TIME, value);
	}

	public void replace(String key, Object value) {
		mcc.replace(key, EXPIRATION_TIME, value);
		
	}
	
	public void remove(String key) {
		mcc.delete(key);
	}

	public Object get(String key) {
		return mcc.get(key);
	}

	public void disconnect() {
		mcc.shutdown();
	}
	
}
