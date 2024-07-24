package com.webapp.SellBuy.Model;

public class Items {

	public long id;
	public String name;
	public long quantity;
	public long price;
	public Items() {}
	public Items(long productId, String name, long quantity,long price) {
		super();
		this.id = productId;
		this.name = name;
		this.quantity = quantity;
		this.price=price;
	}

	@Override
	public String toString() {
		return "Items [productId=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}
	
}
