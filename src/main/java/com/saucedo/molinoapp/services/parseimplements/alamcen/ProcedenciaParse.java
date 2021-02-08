package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JProcedencia;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class ProcedenciaParse implements IParse<JProcedencia> {

	@Override
	public List<JProcedencia> parseJsonToArrayEntity(JSONArray rr) {
		List<JProcedencia> procedencias = new ArrayList<>();
		for (Object u : rr) {			
			procedencias.add(parseJsonToEntity((JSONObject) u));
		}
		return procedencias;
	}
	@Override
	public JProcedencia parseJsonToEntity(JSONObject jp) {
		JProcedencia p = new JProcedencia();
		Long id = (Long) jp.get("id");
		String lugar =(String)jp.get("lugar");
		p.setId(id);
		p.setLugar(lugar);
		return p;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JProcedencia entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("lugar",entity.getLugar());
		return json;
	}
}
