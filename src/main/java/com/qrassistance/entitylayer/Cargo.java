package com.qrassistance.entitylayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CARGO")
@Table(name="CARGO")
public class Cargo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String codCargo;
	@Column(name="nombre")
	private String nombre;
		
	public Cargo() {
	}

	public Cargo(String codCargo, String nombre) {
		this.codCargo = codCargo;
		this.nombre = nombre;
	}

	public Cargo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(String codCargo) {
		this.codCargo = codCargo;
	}
	
}
