package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JIngresoSecado;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class IngresoSecadoParse implements IParse<JIngresoSecado> {

	@Override
	public List<JIngresoSecado> parseJsonToArrayEntity(JSONArray rr) {
		List<JIngresoSecado> ingresosSecado = new ArrayList<>();
		for (Object u : rr) {			
			ingresosSecado.add(parseJsonToEntity((JSONObject) u));
		}
		return ingresosSecado;
	}
	@Override
	public JIngresoSecado parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JIngresoSecado entity = new JIngresoSecado();
		Long id  = (Long)jp.get("id");
		Long numSacos = (Long)jp.get("numeroSacos");
		String fecha = (String)jp.get("fecha");
		String hora =(String) jp.get("hora");
		Double humedad = (Double)jp.get("humedad");
		JSONObject empleado = (JSONObject)jp.get("empleado");		
		entity.setId(id);
		entity.setNumeroSacos(numSacos.intValue());
		entity.setFecha(LocalDate.parse(fecha));
		entity.setHora(LocalTime.parse(hora));
		entity.setHumedad(humedad);
		entity.setEmpleado(FParse.getEmpleadoParse().parseJsonToEntity(empleado));
		return entity;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JIngresoSecado entity) {
		if(entity==null) return null;
		JSONObject json = new JSONObject();
		json.put("id",entity.getId());
		json.put("fecha",null);
		json.put("hora",null);
		json.put("numeroSacos",entity.getNumeroSacos());
		json.put("humedad", entity.getHumedad());
		json.put("empleado",FParse.getEmpleadoParse().parseJEntityToJSONObject(entity.getEmpleado()));
		return json;
	}

	

}
