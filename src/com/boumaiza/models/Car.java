package com.boumaiza.models;

public class Car {
	public int id;
	public String matricule;
	public String owner;
	public String type;
	public String dateE;
	public long price;
	
	public Car() {
		
	}
	public Car(int id) {
		this.id = id;
	}
	public Car(int id, String matricule, String owner, String type, String dateE) {
		this(matricule, owner, type, dateE);
		this.id = id;
		this.price = 0;
	}
	
	public Car(String matricule, String owner, String type, String dateE) {
		this.matricule = matricule;
		this.owner = owner;
		this.type = type;
		this.dateE = dateE;
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

	public String getdateE() {
		return dateE;
	}
	public void setDateE(String dateE) {
		this.dateE = dateE;
	}
	
	public long getPrice() {
		return price;
	}
	public void setprice(long pE) {
		this.price = pE;
	}
	
}
