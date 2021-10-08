package com.qrassistance.entitylayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="ESTADO")
@Table(name="ESTADO")
public class Estado {
	@Id
	private int estado;
	@Column(name="nombre")
	private String nombre_estado;
		
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getNombre_estado() {
		return nombre_estado;
	}
	public void setNombre_estado(String nombre_estado) {
		this.nombre_estado = nombre_estado;
	}
	public Estado(int estado, String nombre_estado) {
		this.estado = estado;
		this.nombre_estado = nombre_estado;
	}
	public Estado() {
	}
}
