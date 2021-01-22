package com.saucedo.molinoapp.views.login;

import java.util.Set;

public interface ISession {
	public void startSession(String token,String userName,Set<String> roles);
}
