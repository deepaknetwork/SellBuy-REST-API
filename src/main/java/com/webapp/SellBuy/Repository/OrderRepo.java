package com.webapp.SellBuy.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webapp.SellBuy.Model.Order;

@Repository
public interface OrderRepo extends MongoRepository<Order,Long> {
	public List<Order> findAllByBuyerId(long buyerId);
	public List<Order> findAllByFarmerId(long farmerId);

}
