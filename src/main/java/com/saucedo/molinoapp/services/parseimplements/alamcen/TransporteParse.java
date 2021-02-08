package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.saucedo.molino_json_models.almacen.JCamionProductor;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class TransporteParse implements IParse<JCamionProductor> {

	@Override
	public List<JCamionProductor> parseJsonToArrayEntity(JSONArray rr) {
		List<JCamionProductor> camiones = new ArrayList<>();
		for (Object u : rr) {			
			camiones.add(parseJsonToEntity((JSONObject) u));
		}
		return camiones;
	}
	@Override
	public JCamionProductor parseJsonToEntity(JSONObject jp) {
		JCamionProductor p = new JCamionProductor();
		Long id = (Long) jp.get("id");
		String placa = (String) jp.get("placa");
		String chofer = (String) jp.get("chofer");
		String descripcion = (String) jp.get("descripcion");
		p.setId(id);
		p.setPlaca(placa);
		p.setChofer(chofer);
		p.setDescripcion(descripcion);
		return p;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JCamionProductor entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("placa", entity.getPlaca());
		json.put("chofer",entity.getChofer());
		json.put("descripcion",entity.getDescripcion());
		return json;
	}

	

}
