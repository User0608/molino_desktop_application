package com.saucedo.molinoapp.services.parseimplements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.personal.JEmpleado;

public class EmpleadoParse implements IParse<JEmpleado> {
	@Override
	public List<JEmpleado> parseJsonToArrayEntity(JSONArray rr) {
		List<JEmpleado> productores = new ArrayList<>();
		for (Object u : rr) {
			JSONObject je = (JSONObject) u;			
			
			productores.add(this.parseJsonToEntity(je));
		}
		return productores;
	}

	@Override
	public JEmpleado parseJsonToEntity(JSONObject je) {
		JEmpleado emp = new JEmpleado();
		Long id = (Long) je.get("id");

		String nombre = (String) je.get("nombre");
		String apellidoPaterno = (String) je.get("apellidoPaterno");
		String apellidoMaterno = (String) je.get("apellidoMaterno");
		String dni = (String) je.get("dni");

		String telefono = (String) je.get("telefon");
		String direccion = (String) je.get("direccion");
		String email = (String) je.get("email");

		double sueldo = (double) je.get("sueldo");
		String fecha = (String) je.get("fechaContrato");
		LocalDate fechaContratecion = null;
		if (fecha != null) {
			fechaContratecion = LocalDate.parse(fecha);
		}
		boolean estado = (boolean) je.get("estado");

		emp.setId(id);
		emp.setDni(dni);
		emp.setNombre(nombre);
		emp.setApellidoPaterno(apellidoPaterno);
		emp.setApellidoMaterno(apellidoMaterno);
		emp.setDireccion(direccion);
		emp.setTelefon(telefono);
		emp.setEmail(email);

		emp.setSueldo(sueldo);
		emp.setFechaContrato(fechaContratecion);
		emp.setEstado(estado);
		
		JSONObject jusuario = (JSONObject) je.get("usuario");
		if(jusuario!=null) {			
			Long uid=(Long) jusuario.get("id");
			
			if(uid!=null) {
				emp.setUsuario(FParse.getUsurioParse().parseJsonToEntity(jusuario));
			}
		}		
		return emp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JEmpleado entity) {
		JSONObject json = new JSONObject();
		json.put("id", entity.getId());
		json.put("dni", entity.getDni());
		json.put("nombre", entity.getNombre());
		json.put("apellidoPaterno", entity.getApellidoPaterno());
		json.put("apellidoMaterno", entity.getApellidoMaterno());
		json.put("direccion", entity.getDireccion());
		json.put("telefon", entity.getTelefon());
		json.put("email", entity.getEmail());

		json.put("sueldo", entity.getSueldo());
		json.put("fechaContrato", entity.getFechaContrato().toString());
		json.put("estado", entity.isEstado());
		if (entity.getUsuario() != null) {
			JSONObject u = FParse.getUsurioParse().parseJEntityToJSONObject(entity.getUsuario());
			json.put("usuario", u);
		}
		return json;
	}

}
