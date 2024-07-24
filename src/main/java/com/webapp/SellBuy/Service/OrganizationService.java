package com.webapp.SellBuy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.SellBuy.Model.Buyer;
import com.webapp.SellBuy.Model.Farmer;
import com.webapp.SellBuy.Model.Organization;
import com.webapp.SellBuy.Repository.BuyerRepo;
import com.webapp.SellBuy.Repository.FarmerRepo;
import com.webapp.SellBuy.Repository.OrganizationRepo;

@Service
public class OrganizationService {

	@Autowired
	OrganizationRepo organizationrepo;
	

	@Autowired
	FarmerRepo farmerrepo;
	

	@Autowired
	BuyerRepo buyerrepo;
	
	public long getproductid() {
		return organizationrepo.findByName("SELLBUY").productCnt;
	}
	
	public void incproductid() {
		Organization org=organizationrepo.findByName("SELLBUY");
		long cnt=org.productCnt;
		org.productCnt=cnt+1;
		organizationrepo.save(org);
	}
	public long getorderid() {
		return organizationrepo.findByName("SELLBUY").orderCnt;
	}
	
	public void incorderid() {
		Organization org=organizationrepo.findByName("SELLBUY");
		long cnt=org.orderCnt;
		org.orderCnt=cnt+1;
		organizationrepo.save(org);
	}
	
	 public boolean buyerTopup(long id,long topup) {
	    	try {
	    		Buyer buyer=buyerrepo.findByPhone(id);
	    		long bal=buyer.balance;
	    		buyer.balance=bal+topup;
	    		buyerrepo.save(buyer);
	    		return true;
	    	}catch(Exception e) {
	    		return false;
	    	}
	    }
	    
	    public boolean farmerTopup(long id,long topup) {
	    	try {
	    		Farmer farmer=farmerrepo.findByPhone(id);
	    		long bal=farmer.balance;
	    		farmer.balance=bal+topup;
	    		farmerrepo.save(farmer);
	    		return true;
	    	}catch(Exception e) {
	    		return false;
	    	}
	    }
}
