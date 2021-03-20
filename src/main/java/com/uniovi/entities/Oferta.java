package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="oferta")
public class Oferta {
	@Id
	@GeneratedValue
	private Long id;
	private double precio;
	private String descripcion;
	private String detalle;
	private Date fecha;
	private boolean comprada;
	private String emailComprador;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;



	public double getPrecio() {
		return precio;
	}

	public String getEmailComprador() {
		return emailComprador;
	}

	public void setEmailComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}

	public Oferta() {
		super();
		this.fecha = new Date();
	}

	public Oferta(Long id, double precio, String descripcion, String detalle, User user) {
		super();
		this.id = id;
		this.precio = precio;
		this.descripcion = descripcion;
		this.detalle = detalle;
		this.fecha = new Date();
		this.user = user;
		this.comprada = false;
	}

	public boolean isComprada() {
		return comprada;
	}

	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
