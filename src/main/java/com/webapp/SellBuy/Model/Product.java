package com.webapp.SellBuy.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Products")
public class Product {
	
	public long id;
	
	public long farmerId;
	
	public String name;
	
	public long quantity;
	
	public long price;
	
	public boolean isHybrid;
public Product() {}
	public Product(long productId, long farmerId, String name, long quantity, long price, boolean isHybrid) {
		super();
		this.id = productId;
		this.farmerId = farmerId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.isHybrid = isHybrid;
	}
	
	
	
	
}
