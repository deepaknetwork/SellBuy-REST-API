package com.webapp.SellBuy.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webapp.SellBuy.Model.Buyer;
import com.webapp.SellBuy.Model.Farmer;


@Repository
public interface BuyerRepo extends MongoRepository<Buyer,Long> {
	public Buyer findByPhone(long phone);
	
}
