package com.saucedo.molinoapp;

import java.util.Set;

public class SessionStatus {
	private String token;
	private String username;
	private Set<String> roles;
	private static final Object object = new Object();
	private static SessionStatus instance;

	private SessionStatus() {
	}	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
	
	@Override
	public String toString() {
		return "SessionStatus [token=" + token + ", username=" + username + "]";
	}
	public static SessionStatus getInst() {
		if (SessionStatus.instance != null) {
			return SessionStatus.instance;
		}
		synchronized (object) {
			if (SessionStatus.instance == null) {
				SessionStatus.instance = new SessionStatus();
			}

			return SessionStatus.instance;
		}
	}
	public static void destroySession() {
		SessionStatus.instance=null;
	}
}
