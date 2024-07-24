package com.webapp.SellBuy.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Buyers")
public class Buyer {

	@Id
	public long phone;
	public String name;
	public String password;
	public String email;
	public String address;
	public List<Bag> bags;
	public long balance;
	public Buyer() {}
	
	public Buyer(long phone, String name, String password, String email, String address,long balance) {
		super();
		this.phone = phone;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.bags=new ArrayList<>();
		this.balance=balance;
	}

	public Buyer(long phone, String name, String password) {
		super();
		this.phone = phone;
		this.name = name;
		this.password = password;
		this.email = "";
		this.address = "";
		this.bags=new ArrayList<>();
		
	}
	@Override
	public String toString() {
		return "Farmer [phone=" + phone + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", address=" + address + "]"+ ", bags=" + bags + "]";
	}
	
}
