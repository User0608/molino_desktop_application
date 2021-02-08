package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JRegistroIngreso;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class RegistroIngresoParse implements IParse<JRegistroIngreso> {

	@Override
	public List<JRegistroIngreso> parseJsonToArrayEntity(JSONArray rr) {
		List<JRegistroIngreso> productores = new ArrayList<>();
		for (Object u : rr) {			
			productores.add(parseJsonToEntity((JSONObject) u));
		}
		return productores;
	}
	@Override
	public JRegistroIngreso parseJsonToEntity(JSONObject jp) {
		JRegistroIngreso entity = new JRegistroIngreso();
		Long id = (Long) jp.get("id");
		Long numeroSacos = (Long) jp.get("numeroSacos");
		double kilosPorSaco = (double) jp.get("kilosPorSaco");
		double totalKilos = (double) jp.get("totalKilos");
		String fecha = (String) jp.get("fecha");
		String hora =(String) jp.get("hora");
		JSONObject jtransporte = (JSONObject) jp.get("transporte");
		JSONObject jempleado = (JSONObject) jp.get("empleado");
				
		entity.setId(id);
		entity.setNumeroSacos(numeroSacos.intValue());
		entity.setKilosPorSaco(kilosPorSaco);
		entity.setTotalKilos(totalKilos);
		entity.setFecha(LocalDate.parse(fecha));
		entity.setHora(LocalTime.parse(hora));		
		if (jtransporte!=null)
			entity.setTransporte(FParse.getTransporteParse().parseJsonToEntity(jtransporte));
		if (jempleado!=null)
			entity.setEmpleado(FParse.getEmpleadoParse().parseJsonToEntity(jempleado));
		return entity;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JRegistroIngreso entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("numeroSacos",entity.getNumeroSacos());
		json.put("kilosPorSaco",entity.getKilosPorSaco());
		json.put("totalKilos",entity.getTotalKilos());
		json.put("fecha",null);
		json.put("hora",null);
		json.put("transporte",FParse.getTransporteParse().parseJEntityToJSONObject(entity.getTransporte()));
		json.put("empleado",FParse.getEmpleadoParse().parseJEntityToJSONObject(entity.getEmpleado()));
		return json;
	}

	

}
