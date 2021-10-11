package com.qrassistance.entitylayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="AREA")
@Table(name="AREA")
public class Area {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String cod_area;
	@Column(name="nombre")
	private String nom_area;
	
	public String getCod_area() {
		return cod_area;
	}
	public void setCod_area(String cod_area) {
		this.cod_area = cod_area;
	}
	public String getNom_area() {
		return nom_area;
	}
	public void setNom_area(String nom_area) {
		this.nom_area = nom_area;
	}
	public Area(String cod_area, String nom_area) {
		this.cod_area = cod_area;
		this.nom_area = nom_area;
	}
	public Area(String nom_area) {
		this.nom_area = nom_area;
	}
	public Area() {
	}
}
