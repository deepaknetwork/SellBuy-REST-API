package com.webapp.SellBuy.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webapp.SellBuy.Model.Farmer;

@Repository
public interface FarmerRepo extends MongoRepository<Farmer,Long> {
	public Farmer findByPhone(long phone);
	
}
