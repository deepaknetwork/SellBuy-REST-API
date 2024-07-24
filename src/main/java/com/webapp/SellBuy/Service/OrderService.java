package com.webapp.SellBuy.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.SellBuy.Model.Order;
import com.webapp.SellBuy.Repository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderrepo;
	
	public List<Order> getBuyerOrder(long phone){
		return orderrepo.findAllByBuyerId(phone);
	}
	
	public List<Order> getFarmerOrder(long phone){
		return orderrepo.findAllByFarmerId(phone);
	}
}
