package com.saucedo.molinoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.saucedo.molino_json_models.RProductor;
import com.saucedo.molino_json_models.reportes.RArroz;
import com.saucedo.molino_json_models.reportes.RIngresoArroz;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Global;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.exceptions.ResponseException;

public class ReportService {
	public static final String YEARS_PATH = "/reporte/years";
	public static final String PRODUCTOR_PATH = "/reporte/productores/";

	public static final String ARROZ_PATH = "/reporte/arroz/";
	public static final String ARROZ_INGRESO_PATH = "/reporte/arroz_ingreso/";
	
	public List<String> findAllIngresoYears() throws ResponseException {
		if (SessionStatus.isStarted()) {
			JSONArray jsonResponse = null;
			try {
				jsonResponse = Request.get(Global.SERVER_PATH + YEARS_PATH);
				List<String> listdata = new ArrayList<String>();
				if (jsonResponse != null) {
					for (Object o:jsonResponse) {
						listdata.add((String)o);
					}
				}
				return listdata;
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				throw new ResponseException(Error.ERROR_CONNECTION_API_REST, "");
			}
		}
		return null;
	}
	public List<RIngresoArroz> findAllArrozIngreso(String year) throws ResponseException {
		
		if (SessionStatus.isStarted()) {
			JSONArray jsonResponse = null;
			try {
				jsonResponse = Request.get(Global.SERVER_PATH +ARROZ_INGRESO_PATH+ year);
				List<RIngresoArroz> productores =new ArrayList<RIngresoArroz>();
				if (jsonResponse != null) {
					for (Object o:jsonResponse) {
						JSONObject jo = (JSONObject)o;
						String d1 = (String)jo.get("mes");
						String d2 = (String)jo.get("sacos");					
						productores.add(new RIngresoArroz(d1,d2));
					}
				}
				return productores;
			} catch (Exception e1) {

				throw new ResponseException(Error.ERROR_CONNECTION_API_REST, "");
			}
		}
		return null;
	}

	public List<RProductor> findAllProductores(String year) throws ResponseException {
	
		if (SessionStatus.isStarted()) {
			JSONArray jsonResponse = null;
			try {
				jsonResponse = Request.get(Global.SERVER_PATH +PRODUCTOR_PATH+ year);
				List<RProductor> productores =new ArrayList<RProductor>();
				if (jsonResponse != null) {
					for (Object o:jsonResponse) {
						JSONObject jo = (JSONObject)o;
						String dni = (String)jo.get("dni");
						String nombre = (String)jo.get("nombre");
						String ap = (String)jo.get("apellidoPaterno");
						String am = (String)jo.get("apellidoMaterno");
						String tel = (String)jo.get("telefon");
						Long sacos = (Long)jo.get("sacos");
						productores.add(new RProductor(dni,nombre,ap,am,tel,sacos));
					}
				}
				return productores;
			} catch (Exception e1) {

				throw new ResponseException(Error.ERROR_CONNECTION_API_REST, "");
			}
		}
		return null;
	}
	public List<RArroz> findAllArroz(String year) throws ResponseException {
		
		if (SessionStatus.isStarted()) {
			JSONArray jsonResponse = null;
			try {
				jsonResponse = Request.get(Global.SERVER_PATH +ARROZ_PATH+ year);
				List<RArroz> arrozs =new ArrayList<RArroz>();
				if (jsonResponse != null) {
					for (Object o:jsonResponse) {
						JSONObject jo = (JSONObject)o;
						String procedencia = (String)jo.get("procedencia");
						String tipoarroz = (String)jo.get("tipoArroz");
						String sacos = (String)jo.get("sacos");
						arrozs.add(new RArroz(procedencia,tipoarroz,sacos));						
					}
				}
				return arrozs;
			} catch (Exception e1) {

				throw new ResponseException(Error.ERROR_CONNECTION_API_REST, "");
			}
		}
		return null;
	}

}
