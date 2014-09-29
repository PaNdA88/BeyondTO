package com.example.beyondto;

import java.util.ArrayList;

public class Player {
    private String name, punti, image, idFacebook, fazione, edificiConquistati;

	public String getEdificiConquistati() {
		return edificiConquistati;
	}

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

	public void setEdificiConquistati(String string) {
		// TODO Auto-generated method stub
		
	}
    
    /*public static ArrayList getItems() {
        ArrayList<Player> list = new ArrayList<Player>();
        Player item;
 
        //ciclo for -- per ogni giocatore prendi i punti e ordina 
        item = new Player();
        item.setName("Andre M");
        item.setPunti("7896");
        item.setImage("rinnegati");
        list.add(item);
 
        item = new Player();
        item.setName("Virgi D");
        item.setPunti("7890");
        item.setImage("rinnegati");
        list.add(item);
 
        item = new Player();
        item.setName("Anto V");
        item.setPunti("3445");
        item.setImage("alchimisti");
        list.add(item);
        
        item = new Player();
        item.setName("Dario C");
        item.setPunti("3440");
        item.setImage("alchimisti");
        list.add(item);
        
        item = new Player();
        item.setName("Giovanni M");
        item.setPunti("3433");
        item.setImage("rinnegati");
        list.add(item);
 
        item = new Player();
        item.setName("Massimo M");
        item.setPunti("3430");
        item.setImage("rinnegati");
        list.add(item);
        
        item = new Player();
        item.setName("Tobia G");
        item.setPunti("2341");
        item.setImage("alchimisti");
        list.add(item);
        
        return list;
    }*/
}