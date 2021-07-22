package com.saucedo.molinoapp.services.security;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.security.JUsuario;
import com.saucedo.molino_json_models.security.SessionRequest;
import com.saucedo.molino_json_models.security.SessionResponse;
import com.saucedo.molinoapp.Global;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.Request;
import com.saucedo.molinoapp.services.Service;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class UsuarioService extends Service<JUsuario> {

	public UsuarioService(IParse<JUsuario> parse,Route route) {
		super(parse,route);
	}
	private SessionResponse parseJSonToSessionResponse(JSONObject r) {
		JSONArray array_roles = (JSONArray) r.get("roles");
		SessionResponse response = new SessionResponse();
		response.setToken((String) r.get("token"));
		response.setUsername((String) r.get("username"));
		response.setOwner((String)r.get("owner"));
		response.setStatus(((Long)r.get("status")).intValue());
		for (Object o : array_roles) {
			String rol = (String) o;
			response.addRole(rol);
		}
		return response;
	}

	@SuppressWarnings({ "unchecked" })
	public SessionResponse initSession(SessionRequest r) throws ResponseException {
		if (!SessionStatus.isStarted()) {
			JSONObject jsonResponse = null;
			JSONObject request = new JSONObject();
			request.put("username", r.getUsername());
			request.put("password", r.getPassword());
			try {
				jsonResponse = Request.requestPostToLogin(Global.LOGING_PATH, request);
			} catch (Exception e1) {
				throw new ResponseException(Error.ERROR_SESSION_USER_OR_PASSWORD, "");
			}
			return this.parseJSonToSessionResponse(jsonResponse);
		}
		return null;
	}

	public JResponse deleteByUserName(String username) throws ResponseException {

		JSONObject jsonResponse = null;
		if (SessionStatus.isStarted()) {
			try {
				jsonResponse = Request.delete(super.route.Delete() + username);
			} catch (Exception e1) {
				throw new ResponseException(Error.ERROR_SESSION_USER_OR_PASSWORD, "");
			}
			return Request.parseJSONObjectToResponse(jsonResponse);
		}
		return null;

	}

}
