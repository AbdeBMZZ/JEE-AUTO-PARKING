package com.boumaiza.models;

public class Car {
	public int id;
	public String matricule;
	public String owner;
	public String type;
	
	public Car() {
		
	}
	public Car(int id) {
		this.id = id;
	}
	public Car(int id, String matricule, String owner, String type) {
		this(matricule, owner, type);

		this.id = id;
	}
	
	public Car(String matricule, String owner, String type) {
		this.matricule = matricule;
		this.owner = owner;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
