package com.example.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="klant")
public class Klant implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String bedrijf;
	private String adres;
	private String postcode;
	private String telefoon;
	private String land;
	
	public Klant() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBedrijf() {
		return bedrijf;
	}
	public void setBedrijf(String bedrijf) {
		this.bedrijf = bedrijf;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTelefoon() {
		return telefoon;
	}
	public void setTelefoon(String telefoon) {
		this.telefoon = telefoon;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	@Override
	public String toString() {
		return "Klant [id=" + id + ", name=" + name + ", bedrijf=" + bedrijf + ", adres=" + adres + ", postcode="
				+ postcode + ", telefoon=" + telefoon + ", land=" + land + "]";
	}
}
