package com.saucedo.molinoapp.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molinoapp.SessionStatus;

public class Request {
	public static final String POST ="POST";
	public static final String PUT ="PUT";
	public static final String GET ="GET";
	public static final String DELETE ="DELETE";
	public static JSONObject requestPostToLogin(String sURL, JSONObject json) throws Exception {
		URL url = new URL(sURL);
		byte[] postDataBytes = json.toJSONString().getBytes("UTF-8");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestMethod(POST);
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
		JSONParser parser = new JSONParser();
		JSONObject jsonResult = (JSONObject) parser.parse(response);		
		return jsonResult;
	}
	public static JSONArray get(String sURL) throws Exception {
		URL url = new URL(sURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();		
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", SessionStatus.getInst().getToken()); 
		conn.setRequestMethod(GET);
		conn.setDoOutput(true);
	
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();		
		JSONParser parser = new JSONParser();
		JSONArray jsonResult = (JSONArray) parser.parse(response);		
		return jsonResult;
	}
	
	public static JSONObject post(String sURL, JSONObject json) throws Exception {		
		return post(sURL, json,POST);
	}
	public static JSONObject put(String sURL, JSONObject json) throws Exception {
		return post(sURL, json,PUT);
	}
	private static JSONObject post(String sURL, JSONObject json,String method) throws Exception {
		URL url = new URL(sURL);
		byte[] postDataBytes = json.toJSONString().getBytes("UTF-8");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", SessionStatus.getInst().getToken()); 
		conn.setRequestMethod(method);
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
		JSONParser parser = new JSONParser();
		JSONObject jsonResult = (JSONObject) parser.parse(response);		
		return jsonResult;
	}
	public static JSONObject delete(String sURL) throws Exception {
		URL url = new URL(sURL);		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", SessionStatus.getInst().getToken()); 
		conn.setRequestMethod(DELETE);
		conn.setDoOutput(true);
		conn.getOutputStream().write(new byte[0]);
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
		JSONParser parser = new JSONParser();
		JSONObject jsonResult = (JSONObject) parser.parse(response);		
		return jsonResult;
	}
	
	
	//Utilidad externa
	public static JResponse parseJSONObjectToResponse(JSONObject r) {
		JResponse response = new JResponse();
		if(r!=null) response.setResponse((String) r.get("response"));
		return response;
	}
}
