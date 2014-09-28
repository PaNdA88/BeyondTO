package com.example.beyondto;

public class Infoton {
	private static Infoton istanza;

	private String userId;

	public static Infoton getInstance() {
		if (istanza == null) {
			istanza = new Infoton();
		}

		return istanza;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Infoton() {
	}

}
