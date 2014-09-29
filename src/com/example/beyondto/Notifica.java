package com.example.beyondto;

public class Notifica {

	private String categoria;
	private String edificio;
	private String image;

	public Notifica(String categoria, String edificio, String image) {
		super();
		this.categoria = categoria;
		this.edificio = edificio;
		this.image = image;
	}

	public String getCategory() {
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
