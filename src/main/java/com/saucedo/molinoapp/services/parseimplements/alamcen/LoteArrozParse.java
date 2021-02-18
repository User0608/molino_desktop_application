package com.saucedo.molinoapp.services.parseimplements.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.almacen.JLoteArroz;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.services.parseimplements.IParse;

public class LoteArrozParse implements IParse<JLoteArroz> {

	@Override
	public List<JLoteArroz> parseJsonToArrayEntity(JSONArray rr) {
		List<JLoteArroz> lotesdearroz = new ArrayList<>();
		for (Object u : rr) {			
			lotesdearroz.add(parseJsonToEntity((JSONObject) u));
		}
		return lotesdearroz;
	}
	@Override
	public JLoteArroz parseJsonToEntity(JSONObject jp) {
		if(jp==null) return null;
		JLoteArroz entity = new JLoteArroz();
		Long id = (Long) jp.get("id");
		Long numeroSacos = (Long) jp.get("numeroSacos");
		
		JSONObject ingreso = (JSONObject) jp.get("ingreso");
		JSONObject productor = (JSONObject) jp.get("productor");
		JSONObject procedencia  = (JSONObject) jp.get("procedencia");
		JSONObject tipoArroz = (JSONObject) jp.get("tipoArroz");
		
		entity.setId(id);
		entity.setNumeroSacos(numeroSacos.intValue());
		if (ingreso!=null)
			entity.setIngreso(FParse.getRegistroIngreso().parseJsonToEntity(ingreso));
		if (productor!=null)
			entity.setProductor(FParse.getProductorParse().parseJsonToEntity(productor));
		if (procedencia!=null)
			entity.setProcedencia(FParse.getProcedenciaParse().parseJsonToEntity(procedencia));
		if (tipoArroz!=null)
			entity.setTipoArroz(FParse.getTipoArrozParse().parseJsonToEntity(tipoArroz));		
		return entity;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject parseJEntityToJSONObject(JLoteArroz entity) {
		JSONObject json = new JSONObject();
		json.put("id",entity.getId());
		json.put("numeroSacos",entity.getNumeroSacos());
		json.put("ingreso",FParse.getRegistroIngreso().parseJEntityToJSONObject(entity.getIngreso()));
		json.put("productor",FParse.getProductorParse().parseJEntityToJSONObject(entity.getProductor()));
		json.put("procedencia",FParse.getProcedenciaParse().parseJEntityToJSONObject(entity.getProcedencia()));
		json.put("tipoArroz",FParse.getTipoArrozParse().parseJEntityToJSONObject(entity.getTipoArroz()));
		return json;
	}

	

}
