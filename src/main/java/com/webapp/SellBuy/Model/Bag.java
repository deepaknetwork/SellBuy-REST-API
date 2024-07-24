package com.webapp.SellBuy.Model;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;

public class Bag {
	public long farmerId;
	public List<Items> items;
	public Bag() {}
	public Bag(long farmerId) {
		super();
		this.farmerId = farmerId;
		this.items = new ArrayList<>();
	}
}
