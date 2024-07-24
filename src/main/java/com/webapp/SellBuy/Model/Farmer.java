package com.webapp.SellBuy.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Farmers")
public class Farmer {

	@Id
	public long phone;
	public String name;
	public String password;
	public String email;
	public String address;
	public long balance;
	public Farmer() {}
	public Farmer(long phone, String name, String password, String email, String address,long balance) {
		super();
		this.phone = phone;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.balance=balance;
	}
	@Override
	public String toString() {
		return "Farmer [phone=" + phone + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", address=" + address + "]";
	}
	public Farmer(long phone, String name, String password) {
		super();
		this.phone = phone;
		this.name = name;
		this.password = password;
		this.email = "";
		this.address = "";
		
	}
	
	
	
}
