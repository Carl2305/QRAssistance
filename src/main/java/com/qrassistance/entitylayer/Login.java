package com.qrassistance.entitylayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="LOGIN")
@Table(name="LOGIN")
public class Login {
	@Id
	private String usuario;
	@Column(name="contrasenia")
	private String contrasenia;
	@OneToOne
	@JoinColumn(name="FK_empleado", updatable = false, nullable=false)
	private Empleado empleado;
	
	public Login() {
	}

	public Login(String usuario, String contrasenia, Empleado empleado) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.empleado = empleado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
