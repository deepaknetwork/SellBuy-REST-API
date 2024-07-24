package com.webapp.SellBuy.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webapp.SellBuy.Model.Organization;

@Repository
public interface OrganizationRepo extends MongoRepository<Organization,String>{

	public Organization findByName(String name);
}
