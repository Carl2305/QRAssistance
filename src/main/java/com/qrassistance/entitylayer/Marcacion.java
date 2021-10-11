package com.qrassistance.entitylayer;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="MARCACION")
@Table(name="MARCACION")
public class Marcacion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="marcacion")
	private int marcacion;
	@ManyToOne
	@JoinColumn(name="EMPLEADO")
	private Empleado empleado;
	@Column(name="inicio")
	private LocalDateTime inicio;
	@Column(name="fin")
	private LocalDateTime fin;
	@ManyToOne
	@JoinColumn(name="ESTADO")
	private Estado estado;
	
	public Marcacion() {
	}
	
	public Marcacion(int marcacion, Empleado empleado, LocalDateTime inicio, LocalDateTime fin, Estado estado) {
		this.marcacion = marcacion;
		this.empleado = empleado;
		this.inicio = inicio;
		this.fin = fin;
		this.estado = estado;
	}
	
	
	
	public Marcacion(Empleado empleado, LocalDateTime inicio, LocalDateTime fin, Estado estado) {
		this.empleado = empleado;
		this.inicio = inicio;
		this.fin = fin;
		this.estado = estado;
	}

	public int getMarcacion() {
		return marcacion;
	}
	public void setMarcacion(int marcacion) {
		this.marcacion = marcacion;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public LocalDateTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}
	public LocalDateTime getFin() {
		return fin;
	}
	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
