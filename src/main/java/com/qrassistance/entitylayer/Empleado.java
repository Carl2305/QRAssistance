package com.qrassistance.entitylayer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="EMPLEADO")
@Table(name="EMPLEADO")
public class Empleado {
	@Id
	private int cod_empleado;
	@Column(name="tlf_empleado")
	private int tlf_empleado;
	@ManyToOne
	@JoinColumn(name="EMPLEADO")
	private Empleado empleado;
	@Column(name="nom_empleado")
	private String nom_empleado;
	@Column(name="ape1_empleado")
	private String ape1_empleado;
	@Column(name="ape2_empleado")
	private String ape2_empleado;
	@Column(name="correo_empleado")
	private String correo_empleado;
	@ManyToOne
	@JoinColumn(name="CARGO")
	private Cargo cargo;
	@OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
	private Login login;
	@ManyToOne
	@JoinColumn(name="AREA")
	private Area area;
	
	public Empleado() {
	}

	public Empleado(int cod_empleado, int tlf_empleado, Empleado empleado, String nom_empleado, String ape1_empleado,
			String ape2_empleado, String correo_empleado, Cargo cargo, Login login, Area area) {
		this.cod_empleado = cod_empleado;
		this.tlf_empleado = tlf_empleado;
		this.empleado = empleado;
		this.nom_empleado = nom_empleado;
		this.ape1_empleado = ape1_empleado;
		this.ape2_empleado = ape2_empleado;
		this.correo_empleado = correo_empleado;
		this.cargo = cargo;
		this.login = login;
		this.area = area;
	}

	public int getCod_empleado() {
		return cod_empleado;
	}

	public void setCod_empleado(int cod_empleado) {
		this.cod_empleado = cod_empleado;
	}

	public int getTlf_empleado() {
		return tlf_empleado;
	}

	public void setTlf_empleado(int tlf_empleado) {
		this.tlf_empleado = tlf_empleado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getNom_empleado() {
		return nom_empleado;
	}

	public void setNom_empleado(String nom_empleado) {
		this.nom_empleado = nom_empleado;
	}

	public String getApe1_empleado() {
		return ape1_empleado;
	}

	public void setApe1_empleado(String ape1_empleado) {
		this.ape1_empleado = ape1_empleado;
	}

	public String getApe2_empleado() {
		return ape2_empleado;
	}

	public void setApe2_empleado(String ape2_empleado) {
		this.ape2_empleado = ape2_empleado;
	}

	public String getCorreo_empleado() {
		return correo_empleado;
	}

	public void setCorreo_empleado(String correo_empleado) {
		this.correo_empleado = correo_empleado;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}


}
