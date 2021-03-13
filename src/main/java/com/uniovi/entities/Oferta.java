package com.uniovi.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Oferta {
	private double precio;

//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
//	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
