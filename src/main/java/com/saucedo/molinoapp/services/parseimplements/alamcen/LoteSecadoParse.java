package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JLoteSecado;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class LoteSecadoParse implements IParse<JLoteSecado> {

	@Override
	public List<JLoteSecado> parseJsonToArrayEntity(JSONArray rr) {
		List<JLoteSecado> lotesdearroz = new ArrayList<>();
		for (Object u : rr) {			
			lotesdearroz.add(parseJsonToEntity((JSONObject) u));
		}
		return lotesdearroz;
	}
	@Override
	public JLoteSecado parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JLoteSecado entity = new JLoteSecado();
		Long id = (Long) jp.get("id");
		String fecha = (String) jp.get("fecha");
		Long sacos = (Long)jp.get("totalSacos");
		JSONObject loteArroz = (JSONObject)jp.get("lotearroz");
		JSONObject ubicacion = (JSONObject)jp.get("ubicacion");
		JSONObject ingreso = (JSONObject)jp.get("ingreso");
		JSONObject tendido = (JSONObject)jp.get("tendido");
		
		entity.setId(id);
		entity.setFecha(LocalDate.parse(fecha));
		entity.setTotalSacos(sacos.intValue());
		entity.setLotearroz(FParse.getLoteArrozParse().parseJsonToEntity(loteArroz));
		entity.setUbicacion(FParse.getUbicacionParsee().parseJsonToEntity(ubicacion));
		entity.setIngreso(FParse.getIngresoSecadoParse().parseJsonToEntity(ingreso));
		entity.setTendido(FParse.getDetalleTendidoParse().parseJsonToEntity(tendido));		
		return entity;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JLoteSecado entity) {
		JSONObject json = new JSONObject();
		json.put("id",entity.getId());
		json.put("fecha",entity.getFecha());
		json.put("totalSacos",entity.getTotalSacos());
		json.put("lotearroz",FParse.getLoteArrozParse().parseJEntityToJSONObject(entity.getLotearroz()));
		json.put("ubicacion",FParse.getUbicacionParsee().parseJEntityToJSONObject(entity.getUbicacion()));
		json.put("ingreso", FParse.getIngresoSecadoParse().parseJEntityToJSONObject(entity.getIngreso()));	
		json.put("tendido",FParse.getDetalleTendidoParse().parseJEntityToJSONObject(entity.getTendido()));
		return json;
	}

	

}
