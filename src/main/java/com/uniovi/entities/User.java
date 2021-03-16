package com.uniovi.entities;

import java.util.Set; //A collection that contains no duplicate elements

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name ="user")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private String apellido;
	
	@Column(unique = true)
	private String email;
	private String password;
	private double dinero;
	private String role;
	
	@Transient //propiedad que no se almacena e la tabla.
	private String passwordConfirm;

//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private Set<Oferta> ofertas;
	
	public User() {
		super();
	}

	public User(String nombre, String apellido, String email, String password, String passwordConfirm) {
		super();

		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.dinero = 100.0;
		this.passwordConfirm = passwordConfirm;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}