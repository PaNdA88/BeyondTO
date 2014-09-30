package com.example.beyondto;

public class Player {
    private String name, punti, image, idFacebook, fazione, edifici;

	public String getFazione() {
		return fazione;
	}

	public void setFazione(String fazione) {
		this.fazione = fazione;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public String getName() {
        return name;        
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPunti() {
        return punti;
    }

    public void setPunti(String punti) {
        this.punti = punti;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	public String getEdifici() {
		return edifici;
	}
	
	public void setEdifici(String edifici) {
		this.edifici = edifici;
	}
	
}