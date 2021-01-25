package com.saucedo.molinoapp.services.almacen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Global;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.Request;

public class ProductorService {
	public List<JProductor> findAll() throws ResponseException {
		if (SessionStatus.isStarted()) {
			JSONArray jsonResponse = null;
			try {
				jsonResponse = Request.get(Global.GET_ALL_PRODUCTORES);
				return this.parseJsonToArrayUsuarios(jsonResponse);
			} catch (Exception e1) {
				throw new ResponseException(Error.ERROR_CONNECTION_API_REST, "");
			}
		}
		return null;
	}
	private List<JProductor> parseJsonToArrayUsuarios(JSONArray rr) {
		List<JProductor> productores = new ArrayList<>();
		for (Object u : rr) {
			JSONObject jp = (JSONObject) u;
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
			productores.add(p);
		}
		return productores;
	}
}
