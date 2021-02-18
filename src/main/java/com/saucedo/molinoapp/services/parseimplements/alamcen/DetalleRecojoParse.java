package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JDetalleRecojo;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class DetalleRecojoParse implements IParse<JDetalleRecojo> {

	@Override
	public List<JDetalleRecojo> parseJsonToArrayEntity(JSONArray rr) {
		List<JDetalleRecojo> lotesdearroz = new ArrayList<>();
		for (Object u : rr) {			
			lotesdearroz.add(parseJsonToEntity((JSONObject) u));
		}
		return lotesdearroz;
	}
	@Override
	public JDetalleRecojo parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JDetalleRecojo entity = new JDetalleRecojo();
		Long id = (Long) jp.get("id");
		String fecha = (String) jp.get("fecha");
		String hora = (String) jp.get("hora");
		Long numeroSacos = (Long) jp.get("numeroSacos");
		Double humedad = (Double) jp.get("humedad");
		entity.setId(id);
		entity.setFecha(LocalDate.parse(fecha));
		entity.setHora(LocalTime.parse(hora));
		entity.setNumeroSacos(numeroSacos.intValue());
		entity.setHumedad(humedad);	
		return entity;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JDetalleRecojo entity) {
		JSONObject json = new JSONObject();
		json.put("id",entity.getId());
		json.put("fecha",entity.getFecha());
		json.put("hora",entity.getHora());
		json.put("numeroSacos",45);
		json.put("humedad",entity.getHumedad());
		return json;
	}

	

}
