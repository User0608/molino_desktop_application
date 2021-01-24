package com.saucedo.molinoapp.services;

import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.security.SessionRequest;

public class SessionService implements IJson{
	private SessionRequest request;
	
	
	public SessionService(SessionRequest request) {
		this.request = request;
	}

	public SessionRequest getRequest() {
		return request;
	}

	public void setRequest(SessionRequest request) {
		this.request = request;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONObject() {		
		JSONObject r = new JSONObject();
		r.put("username",this.request.getUsername());
		r.put("password",this.request.getPassword());
		return r;
	}

}
