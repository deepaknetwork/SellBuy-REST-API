package com.webapp.SellBuy.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.SellBuy.Model.Buyer;
import com.webapp.SellBuy.Model.Farmer;
import com.webapp.SellBuy.Model.Product;
import com.webapp.SellBuy.Repository.FarmerRepo;
import com.webapp.SellBuy.View.FarmerSignup;
import com.webapp.SellBuy.View.ProductView;
import com.webapp.SellBuy.View.FarmerLogin;

@Service
public class FarmerService {

	@Autowired
	FarmerRepo farmerrepo;
	
	public String FarmerSignUp(FarmerSignup farmersignup) {
		try {
			
			   System.out.println(farmersignup);
			Farmer farmer=new Farmer(farmersignup.Phone(),farmersignup.Name(),farmersignup.Password());
			farmerrepo.insert(farmer);
			return "success";
		}catch(Exception e) {
			return e.getMessage().toString();
		}	
	}
	
	public String FarmerLogin(FarmerLogin farmerlogin) {
		try {
			
		   Farmer far=farmerrepo.findByPhone(farmerlogin.Phone());
		   if(far!=null) {
			   if(far.password.equals(farmerlogin.Password())) {
				   return "success";
			   }
			  return "Wrong Password";
		  }
		   return "Farmer not present";
		}catch(Exception e) {
			return e.getMessage().toString();
		}	
	}
	
	public Farmer getFarmerDetails(long phone) {
		return farmerrepo.findByPhone(phone);
	}
	
	public boolean patchFarmerDetails(long phone,Farmer farmer) {
		try{
			Farmer tempfarmer=farmerrepo.findByPhone(phone);
			if(farmer.name!=null) {
				tempfarmer.name=farmer.name;
			}
			if(farmer.password!=null) {
				tempfarmer.password=farmer.password;
			}
			if(farmer.email!=null) {
				tempfarmer.email=farmer.email;
			}
			if(farmer.address!=null) {
				tempfarmer.address=farmer.address;
			}
			farmerrepo.save(tempfarmer);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
