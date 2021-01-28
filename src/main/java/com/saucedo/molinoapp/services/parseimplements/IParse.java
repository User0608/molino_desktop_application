package com.saucedo.molinoapp.services.parseimplements;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IParse<T> {
	//Trae todo los elementos
	public List<T> parseJsonToArrayEntity(JSONArray rr);
	public JSONObject parseJEntityToJSONObject(T entity);
}
