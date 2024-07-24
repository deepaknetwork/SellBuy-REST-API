package com.webapp.SellBuy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.SellBuy.Model.Buyer;
import com.webapp.SellBuy.Model.Order;
import com.webapp.SellBuy.Model.Product;
import com.webapp.SellBuy.Service.BuyerService;
import com.webapp.SellBuy.Service.FarmerService;
import com.webapp.SellBuy.Service.OrderService;
import com.webapp.SellBuy.Service.ProductService;
import com.webapp.SellBuy.View.FarmerLogin;
import com.webapp.SellBuy.View.FarmerSignup;
import com.webapp.SellBuy.View.ItemCartView;

@RestController
@RequestMapping("buyer")
public class BuyerController {

	@Autowired
	BuyerService buyerservice;
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	OrderService orderservice;
	
	@PostMapping("signup")
	public ResponseEntity<String> signup(@RequestBody FarmerSignup farmersignup) {
		String f=buyerservice.BuyerSignUp(farmersignup);
		if(f.equals("success")) {
			return ResponseEntity.ok("Success");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(f);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody FarmerLogin farmerlogin) {
		String f=buyerservice.BuyerLogin(farmerlogin);
		if(f.equals("success")) {
			return ResponseEntity.ok("Success");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(f);
	}
	
	@GetMapping("{phone}")
	public ResponseEntity<Buyer> getbuyerdetails(@PathVariable long phone) {
		Buyer buyer=buyerservice.getBuyerDetails(phone);
		if(buyer!=null) {
			return ResponseEntity.ok(buyer);
		}return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				
	}
	
	@PatchMapping("{phone}")
	public ResponseEntity patchbuyerdetails(@PathVariable long phone,@RequestBody Buyer buyer) {
		if(buyerservice.patchBuyerDetails(phone, buyer)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("products")
	public List<Product> getAllProducts() {
		return productservice.getAllProduct();
	}
	
	@GetMapping("products/{phone}")
	public List<Product> getFarmerProducts(@PathVariable long phone) {
		return productservice.getFarmerProduct(phone);
	}
	
	@GetMapping("product/{id}")
	public Product getProduct(@PathVariable long id) {
		return productservice.getProduct(id);
	}
	
	@PostMapping("addbag/{phone}")
	public ResponseEntity addtoBag(@PathVariable long phone,@RequestBody ItemCartView item) {
		if(productservice.addToBag(phone,item)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@GetMapping("{phone}/buybag/{index}")
	public ResponseEntity buyBag(@PathVariable long phone,@PathVariable int index) {
		if(productservice.buyBag(phone,index)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@GetMapping("{phone}/deletebag/{index}")
	public ResponseEntity deleteBag(@PathVariable long phone,@PathVariable int index) {
		if(productservice.deleteBag(phone,index)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	

	@GetMapping("order/{phone}")
	public List<Order> order(@PathVariable long phone) {
		return orderservice.getBuyerOrder(phone);
			
	}
	
	@GetMapping("hello")
	public String hello() {
		return "mahesh";
	}
	
	
	
	
	
	
}
