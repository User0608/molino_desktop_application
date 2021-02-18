package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JAreaSecado;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class AreaSecadoParse implements IParse<JAreaSecado> {

	@Override
	public List<JAreaSecado> parseJsonToArrayEntity(JSONArray rr) {
		List<JAreaSecado> lotesdearroz = new ArrayList<>();
		for (Object u : rr) {			
			lotesdearroz.add(parseJsonToEntity((JSONObject) u));
		}
		return lotesdearroz;
	}
	@Override
	public JAreaSecado parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JAreaSecado entity = new JAreaSecado();
		Long id = (Long) jp.get("id");
		String ubicacion = (String)jp.get("ubicacion");
		Long capasidad = (Long) jp.get("capacidad");
		entity.setId(id);
		entity.setUbicacion(ubicacion);
		entity.setCapacidad(capasidad.intValue());
		JSONArray ubicaciones = (JSONArray) jp.get("ubicaciones");
		entity.setUbicaciones(FParse.getUbicacionParsee().parseJsonToArrayEntity(ubicaciones));
		return entity;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JAreaSecado entity) {
		JSONObject json = new JSONObject();
		json.put("id",entity.getId());
		
		return json;
	}

	

}
