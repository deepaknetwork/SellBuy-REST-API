package com.webapp.SellBuy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.SellBuy.Model.Buyer;
import com.webapp.SellBuy.Model.Farmer;
import com.webapp.SellBuy.Repository.BuyerRepo;
import com.webapp.SellBuy.Repository.FarmerRepo;
import com.webapp.SellBuy.View.FarmerLogin;
import com.webapp.SellBuy.View.FarmerSignup;

@Service
public class BuyerService {

	@Autowired
	BuyerRepo buyerrepo;
	
	public String BuyerSignUp(FarmerSignup farmersignup) {
		try {
			Buyer buyer=new Buyer(farmersignup.Phone(),farmersignup.Name(),farmersignup.Password());
			buyerrepo.insert(buyer);
			return "success";
		}catch(Exception e) {
			return e.getMessage().toString();
		}	
	}
	
	public String BuyerLogin(FarmerLogin farmerlogin) {
		try {	
		   Buyer far=buyerrepo.findByPhone(farmerlogin.Phone());
		   if(far!=null) {
			   if(far.password.equals(farmerlogin.Password())) {
				   return "success";
			   }
			  return "Wrong Password";
		  }
		   return "Buyer not present";
		}catch(Exception e) {
			return e.getMessage().toString();
		}	
	}
	
	public Buyer getBuyerDetails(long phone) {
		return buyerrepo.findByPhone(phone);
	}
	
	public boolean patchBuyerDetails(long phone,Buyer buyer) {
		try{
			Buyer tempbuyer=buyerrepo.findByPhone(phone);
			if(buyer.name!=null) {
				tempbuyer.name=buyer.name;
			}
			if(buyer.password!=null) {
				tempbuyer.password=buyer.password;
			}
			if(buyer.email!=null) {
				tempbuyer.email=buyer.email;
			}
			if(buyer.address!=null) {
				tempbuyer.address=buyer.address;
			}
			buyerrepo.save(tempbuyer);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
