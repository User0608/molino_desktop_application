package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JDetalleTendido;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class DetalleTendidoParse implements IParse<JDetalleTendido> {
	
	@Override
	public List<JDetalleTendido> parseJsonToArrayEntity(JSONArray rr) {
		List<JDetalleTendido> lotesdearroz = new ArrayList<>();
		for (Object u : rr) {			
			lotesdearroz.add(parseJsonToEntity((JSONObject) u));
		}
		return lotesdearroz;
	}
	@Override
	public JDetalleTendido parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JDetalleTendido entity = new JDetalleTendido();
		Long id = (Long) jp.get("id");	
		String ubicacion = (String) jp.get("ubicacion");
		String fecha = (String) jp.get("fecha");
		String hora = (String) jp.get("hora");
		JSONObject recojo = (JSONObject) jp.get("detalleRecojo");
		entity.setId(id);
		entity.setUbicacion(ubicacion);
		entity.setFecha(LocalDate.parse(fecha));
		entity.setHora(LocalTime.parse(hora));
		entity.setDetalleRecojo(FParse.getDetalleRecojoParse().parseJsonToEntity(recojo));		
		return entity;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JDetalleTendido entity) {
		if(entity==null) return null;
		JSONObject json = new JSONObject();
		json.put("id",entity.getId());
		json.put("ubicacion", entity.getUbicacion());
		json.put("fecha",null);
		json.put("hora",null);
		json.put("detalleRecojo",entity.getDetalleRecojo());
		return json;
	}

	

}
