package com.saucedo.molinoapp.services;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class Service<T> {
	protected IParse<T> parse;
	protected Route route;

	public Service(IParse<T> parse,Route route) {
		this.parse = parse;
		this.route=route;
	}

	public List<T> findAll() throws ResponseException {
		if (this.parse == null&&this.route!=null)
			return null;
		if (SessionStatus.isStarted()) {
			JSONArray jsonResponse = null;
			try {
				jsonResponse = Request.get(route.Getall());
				return this.parse.parseJsonToArrayEntity(jsonResponse);
			} catch (Exception e1) {
				throw new ResponseException(Error.ERROR_CONNECTION_API_REST, "");
			}
		}
		return null;
	}

	public JResponse insert(T entity) throws ResponseException {
		if (this.parse == null&&this.route!=null)
			return null;
		JSONObject jsonResponse = null;
		if (SessionStatus.isStarted()) {
			try {
				jsonResponse = Request.post(this.route.Post(), this.parse.parseJEntityToJSONObject(entity));
			} catch (Exception e1) {
				throw new ResponseException(Error.ERROR_BASIC, "");
			}
			return Request.parseJSONObjectToResponse(jsonResponse);
		}
		return null;
	}

	public JResponse update(T entity) throws ResponseException {
		if (this.parse == null&&this.route!=null)
			return null;
		JSONObject jsonResponse = null;
		if (SessionStatus.isStarted()) {
			try {
				jsonResponse = Request.put(this.route.Put(), this.parse.parseJEntityToJSONObject(entity));
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				throw new ResponseException(Error.ERROR_BASIC, "");
			}
			return Request.parseJSONObjectToResponse(jsonResponse);
		}
		return null;
	}

	public JResponse delete(Long id) throws ResponseException {
	JSONObject jsonResponse = null;				
	if (SessionStatus.isStarted()) {
		try {
			jsonResponse = Request.delete(this.route.Delete()+id);
		} catch (Exception e1) {
			throw new ResponseException(Error.ERROR_BASIC, "");
		}
		return Request.parseJSONObjectToResponse(jsonResponse);
	}
		return null;
	}
}
