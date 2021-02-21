package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JUbicacion;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class UbicacionParse implements IParse<JUbicacion> {

	@Override
	public List<JUbicacion> parseJsonToArrayEntity(JSONArray rr) {
		List<JUbicacion> ubicaciones = new ArrayList<>();
		for (Object u : rr) {			
			ubicaciones.add(parseJsonToEntity((JSONObject) u));
		}
		return ubicaciones;
	}
	@Override
	public JUbicacion parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JUbicacion u = new JUbicacion();
		Long id = (Long) jp.get("id");
		String codigo = (String)jp.get("codigo");
		String descripcion = (String)jp.get("descripcion");
		u.setId(id);
		u.setCodigo(codigo);
		u.setDescripcion(descripcion);
		return u;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JUbicacion entity) {
		if(entity==null) return null;
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("codigo", entity.getCodigo());
		json.put("descripcion", entity.getDescripcion());
		return json;
	}

	

}
