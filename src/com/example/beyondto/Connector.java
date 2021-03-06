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
import android.util.Log;

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

	// --------- METODI HOME ACTIVITY ----------------//

	public String[] getUserInfo(String idFacebook) {

		setPath("/getUserInfo.php");
		JSONObject json = new JSONObject();
		try {
			json.put("idFacebook", idFacebook);
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
		String info[] = new String[7];
		try {
			info[0] = result.getString("punti");
			info[1] = result.getString("userName");
			info[2] = result.getString("fazioneUtente");
			info[3] = result.getString("fazioneAvversaria");
			info[4] = result.getString("numeroConquiste");
			info[5] = result.getString("numeroAttacchi");
			info[6] = result.getString("numeroDifese");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info;
	}

	public String[] getClanInfo(String idFacebook) {

		setPath("/clanInfo.php");
		JSONObject json = new JSONObject();
		try {
			json.put("idFacebook", idFacebook);
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
		String info[] = new String[2];
		try {
			info[0] = result.getString("numeroClan");
			info[1] = result.getString("numeroVincite");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info;
	}

	// -------------- METODI MEDAL ACTIVITY -----------------//

	public ArrayList<Player> getRankUsers() {
		setPath("/getRankUsers.php");
		JSONObject json = new JSONObject();
		JSONArray users = new JSONArray();
		ArrayList<Player> listUsers = new ArrayList<Player>();
		try {
			JSONObject result = this.execute(json).get();
			users = result.getJSONArray("users");
			for (int i = 0; i < users.length(); i++) {

				JSONObject u = users.getJSONObject(i);
				Player p = new Player();
				p.setIdFacebook(u.getString("idFacebook"));
				p.setName(u.getString("userName"));
				p.setFazione(u.getString("clan"));
				p.setPunti(u.getString("score"));
				p.setEdifici(u.getString("numWin"));
				listUsers.add(p);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			listUsers = null;
		} catch (ExecutionException e) {
			e.printStackTrace();
			listUsers = null;
		} catch (JSONException e) {
			e.printStackTrace();
			listUsers = null;
		}
		return listUsers;
	}

	// --------- METODI NEWS -------------//
	public ArrayList<Notifica> getAllNotifications() {
		setPath("/getAllNotifications.php");
		JSONObject json = new JSONObject();
		JSONArray notifications = new JSONArray();
		ArrayList<Notifica> listNot = new ArrayList<Notifica>();
		try {
			JSONObject result = this.execute(json).get();
			notifications = result.getJSONArray("notifications");
			for (int i = 0; i < notifications.length(); i++) {

				JSONObject u = notifications.getJSONObject(i);
				Notifica n = new Notifica();
				n.setUserName(u.getString("userNome"));
				n.setUserClan(u.getString("fazione"));
				n.setEdificio(u.getString("nomePlace"));
				n.setCategory(u.getString("azione"));
				n.setOrario(u.getString("orario"));

				if (u.getString("azione").equals("attaccare")) {
					n.setImage("sword");
				} else {
					n.setImage("scudo");
				}

				/*
				 * String azione = u.getString("azione"); Log.e("AZIONE: ",
				 * azione); String place = u.getString("nomePlace");
				 * Log.e("LUOGO: ", place); String img = ""; Notifica n = new
				 * Notifica(azione, place, img);
				 * 
				 * if (azione.equals("attaccare")) { n.setImage("sword");
				 * Log.e("IMG: ", n.getImage()); } else { n.setImage("scudo");
				 * Log.e("IMG: ", n.getImage()); }
				 */

				/*
				 * n.setName(u.getString("userNome"));
				 * n.setAzione(u.getString("azione"));
				 * n.setEdificio(u.getString("nomePlace"));
				 * n.setFazione(u.getString("fazione"));
				 * n.setTempo(u.getString("orario"));
				 * n.setCategory(u.getString("azione"));
				 */
				listNot.add(n);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			listNot = null;
		} catch (ExecutionException e) {
			e.printStackTrace();
			listNot = null;
		} catch (JSONException e) {
			e.printStackTrace();
			listNot = null;
		}

		return listNot;
	}

	// --------- METODI MAP ACTIVITY-------------//

	public ArrayList<Place> getLocations() {
		setPath("/getLocations.php");
		JSONObject json = new JSONObject();
		JSONArray locations = new JSONArray();
		ArrayList<Place> listLocations = new ArrayList<Place>();
		try {
			JSONObject result = this.execute(json).get();

			locations = result.getJSONArray("locations");
			for (int i = 0; i < locations.length(); i++) {

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

	public String[] checkPlaceState(String nomeLuogo) {

		setPath("/checkPlaceState.php");
		JSONObject json = new JSONObject();
		try {
			json.put("nomeLuogo", nomeLuogo);
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
		String[] dati = new String[9];

		try {
			dati[0] = result.getString("proprietaFazione");
			dati[1] = result.getString("latSO");
			dati[2] = result.getString("lngSO");
			dati[3] = result.getString("latNE");
			dati[4] = result.getString("lngNE");
			dati[5] = result.getString("statoLuogo");
			dati[6] = result.getString("orario");
			dati[7] = result.getString("numeroAttaccanti");
			dati[8] = result.getString("numeroDifensori");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return dati;
	}

	public int[] startNewAttack(String idUserFacebook, String nomeLuogo,
			String azione) {

		setPath("/startNewAttack.php");

		JSONObject json = new JSONObject();
		try {
			json.put("idFacebook", idUserFacebook);
			json.put("nomeLuogo", nomeLuogo);
			json.put("azione", azione);

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

		int infoId[] = new int[2];
		try {
			infoId[0] = result.getInt("idLuogo");
			infoId[1] = result.getInt("idScontro");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return infoId;
	}

	public void setScoreAttDif(Double score, String idUser, String namePlace,
			String action, String userClan, int idPlace, int idMatch) {

		setPath("/setScoreAttDif.php");

		JSONObject json = new JSONObject();
		try {
			json.put("score", Double.toString(score));
			json.put("idFacebook", idUser);
			json.put("nomeLuogo", namePlace);
			json.put("azione", action);
			json.put("clanUtente", userClan);
			json.put("idLuogo", Integer.toString(idPlace));
			json.put("idScontro", Integer.toString(idMatch));

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
		;
	}

	public void setWinnersMatch(String namePlace) {

		setPath("/setWinnersMatch.php");

		JSONObject json = new JSONObject();
		try {
			json.put("nomeLuogo", namePlace);

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
	}

	public String[] getQuestion(int idPlace) {

		setPath("/getQuestion.php");
		JSONObject json = new JSONObject();
		try {
			json.put("idLuogo", idPlace);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject result = new JSONObject();

		String question[] = new String[4];
		try {
			result = this.execute(json).get();
			question[0] = result.getString("domanda");
			question[1] = result.getString("risposta1");
			question[2] = result.getString("risposta2");
			question[3] = result.getString("risposta3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return question;
	}

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
				Log.e("RISULT STRING", resultString);
				jsonObjRecv = new JSONObject(resultString);
				if (jsonObjRecv.has("error")) {

					Log.e("Errore comunicazione",
							jsonObjRecv.getString("error"));
				}

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

	/*
	 * JSON reader
	 */
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

	/* PHP path */
	public void setPath(String newPath) {
		path = newPath;
	}

	/* user's clan choice */
	public String setClan(String clan, String tokenFacebook) {
		setPath("/setClan.php");
		JSONObject json = new JSONObject();
		try {
			json.put("tokenFacebook", tokenFacebook);
			json.put("fazioneAppartenenza", clan);

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
		String message = "";
		try {
			message = result.getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return message;
	}

}