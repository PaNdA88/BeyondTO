package com.example.beyondto;

public class Player {
    private String name;
    private String punti;
    private String image;

    public Player(String name, String punti, String image) {
        super();
        this.name = name;
        this.punti = punti;
        this.image = image;
    }

    public String getName() {
        return name;        
    }

    public void setName(String nameText) {
        name = nameText;
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
}