package com.webapp.SellBuy.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.SellBuy.Model.Bag;
import com.webapp.SellBuy.Model.Buyer;
import com.webapp.SellBuy.Model.Farmer;
import com.webapp.SellBuy.Model.Items;
import com.webapp.SellBuy.Model.Order;
import com.webapp.SellBuy.Model.Product;
import com.webapp.SellBuy.Repository.BuyerRepo;
import com.webapp.SellBuy.Repository.FarmerRepo;
import com.webapp.SellBuy.Repository.OrderRepo;
import com.webapp.SellBuy.Repository.ProductRepo;
import com.webapp.SellBuy.View.ItemCartView;
import com.webapp.SellBuy.View.ProductView;

@Service
public class ProductService {
	
	@Autowired
	OrganizationService orgser;
	
	@Autowired
	ProductRepo productrepo;
	
	@Autowired
	BuyerRepo buyerrepo;
	
	@Autowired
	FarmerRepo farmerrepo;
	
	@Autowired
	OrderRepo orderrepo;
	
	
	public boolean addProduct(long phone,ProductView productview) {
		List<Product> product=productrepo.findAllByFarmerId(phone);
		boolean notpresent=true;
		for(Product pro:product) {
			if(pro.name.equals(productview.name())) {notpresent=false;}
		}
		if(notpresent) {
			productrepo.insert(new Product(orgser.getproductid(),phone,productview.name(),productview.quantity(),productview.price(),productview.isHybrid()));
			orgser.incproductid();
			return true;
		}return false;	
	}
	
	public List<Product> getFarmerProduct(long phone) {
		return productrepo.findAllByFarmerId(phone);
	}
	
    public List<Product> getAllProduct() {
    	return productrepo.findAll();
	}

    public boolean patchProductDetails(long id,Product product) {
		try{
			Product tempproduct=productrepo.findById(id);
			if(product.quantity!=0) {
				tempproduct.quantity=product.quantity;
			}
			if(product.price!=0) {
				tempproduct.price=product.price;
			}
			productrepo.save(tempproduct);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
    
    public boolean deleteProduct(long id) {
    	try {
    		productrepo.deleteById(id);
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }
    
    public Product getProduct(long id) {
    		return productrepo.findById(id);
    }
    
    public boolean addToBag(long id,ItemCartView item) {
    	try {
    		Buyer tempbuyer=buyerrepo.findByPhone(id);
    		Product selectedItem=getProduct(item.id());
    		int bagIndex=-1;
    		int inc=0;
    		for(Bag bag:tempbuyer.bags) {
    			if(bag.farmerId==selectedItem.farmerId) {
    				bagIndex=inc;
      		  }inc++;
    		}
    		Farmer farmer = farmerrepo.findByPhone(selectedItem.farmerId);
    		if(bagIndex==-1) {
    			Bag newBag=new Bag(selectedItem.farmerId);
    			newBag.items.add(new Items(item.id(),selectedItem.name,item.quantity(),item.quantity()*selectedItem.price));
    			tempbuyer.bags.add(newBag);
    			buyerrepo.save(tempbuyer);
    		}else {
    			List<Bag> bags=new ArrayList<>(tempbuyer.bags);
    			Bag oldBag=bags.get(bagIndex);
    			int itemIndex=-1;
        		int incc=0;
        		for(Items itm:oldBag.items) {
        			if(itm.id==item.id()) {
        				itemIndex=incc;
          		  }incc++;
        		}
        		if(itemIndex==-1) {
        			oldBag.items.add(new Items(item.id(),selectedItem.name,item.quantity(),item.quantity()*selectedItem.price));
        			tempbuyer.bags.set(bagIndex, oldBag);
        			buyerrepo.save(tempbuyer);
        		}else {
        			Items oldItem=oldBag.items.get(itemIndex);
        			oldItem.quantity=oldItem.quantity+item.quantity();
        			oldItem.price=oldItem.price+(item.quantity()*selectedItem.price);
        			oldBag.items.set(itemIndex, oldItem);
        			tempbuyer.bags.set(bagIndex, oldBag);
        			buyerrepo.save(tempbuyer);
        		}
    		}
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }
    public boolean buyBag(long phone,int index) {
    	try {
    		Buyer buyer=buyerrepo.findByPhone(phone);
    		List<Bag> bag=buyer.bags;
    		Bag bagorder=bag.get(index);
    		Farmer farmer =farmerrepo.findByPhone(bagorder.farmerId);
    		long ammount=0;
    		for(Items itm:bagorder.items) {
    			Product product =productrepo.findById(itm.id);
    			ammount =ammount+(itm.price);
    			product.quantity=product.quantity-itm.quantity;
    			productrepo.save(product);
    		}
    		orderrepo.insert(new Order(orgser.getorderid(),phone,bagorder.farmerId,bagorder.items,ammount,new Date()));
    		orgser.incorderid();
    		farmer.balance=farmer.balance+ammount;
    		farmerrepo.save(farmer);
    		
    		buyer.bags.remove(index);
    		buyer.balance=buyer.balance-ammount;
    		buyerrepo.save(buyer);
    		return true;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }
    
    public boolean deleteBag(long phone,int index) {
    	try {
    		
    		Buyer buyer=buyerrepo.findByPhone(phone);
    		List<Bag> bag=buyer.bags;
    		bag.remove(index);
    		buyerrepo.save(buyer);
    		return true;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }
   
	
	
}
