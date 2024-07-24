package com.webapp.SellBuy.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Orders")
public class Order {
	
	@Id
	public long id;
	public long buyerId;
	public long farmerId;
	public List<Items> items;
	public long ammount;
	public Date orderdate;
	public String status;
	public Date delivereddate;
	public Order() {}
	public Order(long id, long buyerId, long farmerId, List<Items> items, long ammount, Date orderdate) {
		super();
		this.id = id;
		this.buyerId = buyerId;
		this.farmerId = farmerId;
		this.items = items;
		this.ammount = ammount;
		this.orderdate = orderdate;
		this.status = "Ready to Ship";
		
	}
	
	
	
	
	
	
}
