package com.webapp.SellBuy.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webapp.SellBuy.Model.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product,Long> {
	public Product findByName(String name);
	public List<Product> findAllByFarmerId(long farmerId);
	public Product findById(long id);
}
