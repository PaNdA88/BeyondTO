package com.example.beyondto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class Connector extends AsyncTask<JSONObject, Void, JSONObject> {

	String url = "http://www.antonellavannucci.it";
	String path;

	public String doLoginFromFacebook(String userId, String tokenFacebook,
			String dateExpiringToken, String userEmail, String userName) {

		setPath("/LoginFromFacebook.php");
		JSONObject json = new JSONObject();
		try {
			json.put("idFacebook", userId);
			json.put("tokenFacebook", tokenFacebook);
			json.put("dateExpiringToken", dateExpiringToken);
			json.put("userEmail", userEmail);
			json.put("userName", userName);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject result = new JSONObject();
		try {
			result = this.execute(json).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		String success = "0";
		try {
			success = result.getString("success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/*
	 * Get information about places 
	 */
	public ArrayList<Place> getLocations(){
		setPath("/getLocations.php");
		JSONObject json = new JSONObject();
		JSONArray locations = new JSONArray();	
		ArrayList<Place> listLocations = new ArrayList<Place>();
		try {
			JSONObject result = this.execute(json).get();
			
			locations =  result.getJSONArray("locations");
			for(int i = 0; i < locations.length(); i++){
				
				JSONObject loc = locations.getJSONObject(i);
				Place p = new Place();
				p.setNomeLuogo(loc.getString("nomeLuogo"));
				p.setLatNE(Double.parseDouble(loc.getString("latNE")));
				p.setLngNE(Double.parseDouble(loc.getString("lngNE")));
				p.setLatSO(Double.parseDouble(loc.getString("latSO")));
				p.setLngSO(Double.parseDouble(loc.getString("lngSO")));
				p.setDescrizioneLuogo(loc.getString("descrizioneLuogo"));
				p.setProprietaFazione(loc.getString("proprietaFazione"));
				p.setStatoLuogo(loc.getString("statoLuogo"));
				
				listLocations.add(p);			
			}	

		} catch (InterruptedException e) {
			e.printStackTrace();
			listLocations = null;
		} catch (ExecutionException e) {
			e.printStackTrace();
			listLocations = null;
		} catch (JSONException e) {
			e.printStackTrace();
			listLocations = null;
		}	
		return listLocations;
	}
	
	/*public String checkPlaceState(String nomeLuogo){
		
		setPath("/checkPlaceState.php");
		JSONObject json = new JSONObject();
		try {
			json.put("nomeLuogo", nomeLuogo);
		
		} catch (JSONException e) {
			e.printStackTrace();
		}JSONObject result = new JSONObject();
		try {
			result = this.execute(json).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		String message = null;
		try {
			message = result.getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return message;
		
		
	}*/
	
	@Override
	protected JSONObject doInBackground(JSONObject... params) {
		HttpClient httpClient = new DefaultHttpClient();
		JSONObject json = params[0];
		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		HttpConnectionParams.setSoTimeout(myParams, 10000);
		JSONObject jsonObjRecv = new JSONObject();
		try {
			jsonObjRecv.put("Error", "Non sono entrato nel try");
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		try {
			// POST JSON
			HttpPost post = new HttpPost(url + path);
			// form entity
			ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("json", json.toString()));
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e1) {
				try {
					jsonObjRecv.put("Error", "JSON ricevuto scritto male");
				} catch (JSONException e2) {
					e2.printStackTrace();
				}
			}
			// GET JSON
			HttpResponse response = httpClient.execute(post);
			// prendo l'identitˆ della mia risposta
			HttpEntity REntity = response.getEntity();
			if (REntity != null) {
				InputStream instream = REntity.getContent();
				String resultString = convertStreamToString(instream);
				instream.close();
				jsonObjRecv = new JSONObject(resultString);
			} else {
				try {
					jsonObjRecv.put("Error",
							"Il server ha risposto con un JSON vuoto");
				} catch (JSONException e2) {
					e2.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjRecv;
	}
	
	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public void setPath(String newPath) {
		path = newPath;
	}

}
