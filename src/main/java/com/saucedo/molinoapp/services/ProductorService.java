package com.saucedo.molinoapp.services;

import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class ProductorService extends Service<JProductor>{

	public ProductorService(IParse<JProductor> parse, Route route) {
		super(parse, route);		
	}
	public JProductor getProductorByDNI(String dni) throws ResponseException {
		if (this.parse == null&&this.route!=null)
			return null;
		if (SessionStatus.isStarted()) {
			JSONObject jsonResponse = null;
			try {
				jsonResponse = Request.getWithParam(route.Get()+"dni/"+dni);
				if(jsonResponse.isEmpty()) return null;
				if(jsonResponse.get("id")==null) return null;
				return this.parse.parseJsonToEntity(jsonResponse);
			} catch (Exception e1) {
			
				throw new ResponseException(Error.ERROR_CONNECTION_API_REST, "");
			}
		}
		return null;
	}

}
