package com.example.beyondto;

public class Notifica {

	private String categoria, edificio, image, azione, tempo, fazione;

	/*
	 * public Notifica(String categoria, String edificio, String image) { //
	 * super(); this.categoria = categoria; this.edificio = edificio; this.image
	 * = image; }
	 */

	public String getFazione() {
		return fazione;
	}

	public void setFazione(String fazione) {
		this.fazione = fazione;
	}

	public Notifica() {
	}

	public String getAzione() {
		return azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getCategory() {
		// sarebbe l'attacco
		return categoria;
	}

	public void setName(String Text) {
		categoria = Text;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
