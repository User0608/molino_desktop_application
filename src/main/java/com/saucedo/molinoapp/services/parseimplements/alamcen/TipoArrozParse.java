package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.saucedo.molino_json_models.almacen.JTipoArroz;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class TipoArrozParse implements IParse<JTipoArroz> {

	@Override
	public List<JTipoArroz> parseJsonToArrayEntity(JSONArray rr) {
		List<JTipoArroz> tiposarroz = new ArrayList<>();
		for (Object u : rr) {			
			tiposarroz.add(parseJsonToEntity((JSONObject) u));
		}
		return tiposarroz;
	}
	@Override
	public JTipoArroz parseJsonToEntity(JSONObject jp) {
		JTipoArroz p = new JTipoArroz();
		Long id = (Long) jp.get("id");
		String nombre =(String)jp.get("nombre");
		String descripcion =(String) jp.get("descripcion");
		p.setId(id);
		p.setNombre(nombre);
		p.setDescripcion(descripcion);
		return p;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JTipoArroz entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("nombre", entity.getNombre());
		json.put("descripcion",entity.getDescripcion());
		return json;
	}
}
