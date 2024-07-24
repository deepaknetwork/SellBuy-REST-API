package com.webapp.SellBuy.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Organization")
public class Organization {
	@Id
	public String name;
	public long productCnt;
	public long orderCnt;

	public Organization() {}
	public Organization(String name,long productCnt,long orderCnt) {
		super();
		this.name = name;
		this.productCnt = productCnt;
		this.orderCnt = orderCnt;
	}
}
