package com.example.beyondto;

public class Player {
	private String nome;
    private int punti;
    private int clan;
    
    public Player(String nome, int punti, int clan) {
            super();
            this.nome = nome;
            this.punti = punti;
            this.clan = clan;
    }
    public String getNome() {
            return nome;
    }
    public int getPunti() {
            return punti;
    }
    public int getPhotoRes() {
            return clan;
    }
}
