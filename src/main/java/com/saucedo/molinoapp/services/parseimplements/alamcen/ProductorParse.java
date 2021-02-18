package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class ProductorParse implements IParse<JProductor> {

	@Override
	public List<JProductor> parseJsonToArrayEntity(JSONArray rr) {
		List<JProductor> productores = new ArrayList<>();
		for (Object u : rr) {			
			productores.add(parseJsonToEntity((JSONObject) u));
		}
		return productores;
	}
	@Override
	public JProductor parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JProductor p = new JProductor();
		Long id = (Long) jp.get("id");
		String dni = (String) jp.get("dni");
		String nombre =(String) jp.get("nombre");
		String apellidoPaterno =(String) jp.get("apellidoPaterno");
		String apellidoMaterno=(String) jp.get("apellidoMaterno");
		String direccion=(String) jp.get("direccion");
		String telefono=(String) jp.get("telefon");
		String email = (String) jp.get("email");
		p.setId(id);
		p.setDni(dni);
		p.setNombre(nombre);
		p.setApellidoPaterno(apellidoPaterno);
		p.setApellidoMaterno(apellidoMaterno);
		p.setDireccion(direccion);
		p.setTelefon(telefono);
		p.setEmail(email);	
		return p;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JProductor entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("dni", entity.getDni());
		json.put("nombre",entity.getNombre());
		json.put("apellidoPaterno",entity.getApellidoPaterno());
		json.put("apellidoMaterno",entity.getApellidoMaterno());
		json.put("direccion", entity.getDireccion());
		json.put("telefon", entity.getTelefon());
		json.put("email", entity.getEmail());
		return json;
	}

	

}
