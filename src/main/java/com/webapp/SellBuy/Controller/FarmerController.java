package com.webapp.SellBuy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.SellBuy.Model.Buyer;
import com.webapp.SellBuy.Model.Farmer;
import com.webapp.SellBuy.Model.Order;
import com.webapp.SellBuy.Model.Product;
import com.webapp.SellBuy.Service.FarmerService;
import com.webapp.SellBuy.Service.OrderService;
import com.webapp.SellBuy.Service.ProductService;
import com.webapp.SellBuy.View.FarmerLogin;
import com.webapp.SellBuy.View.FarmerSignup;
import com.webapp.SellBuy.View.ProductView;

@RestController
@RequestMapping("farmer")
public class FarmerController {
	
	
	@Autowired
	FarmerService farmerservice;
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	OrderService orderservice;
	
	@PostMapping("signup")
	public ResponseEntity<String> signup(@RequestBody FarmerSignup farmersignup) {
		String f=farmerservice.FarmerSignUp(farmersignup);
		if(f.equals("success")) {
			return ResponseEntity.ok("Success");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(f);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody FarmerLogin farmerlogin) {
		String f=farmerservice.FarmerLogin(farmerlogin);
		if(f.equals("success")) {
			return ResponseEntity.ok("Success");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(f);
	}
	
	@GetMapping("{phone}")
	public ResponseEntity<Farmer> getbuyerdetails(@PathVariable long phone) {
		Farmer farmer=farmerservice.getFarmerDetails(phone);
		if(farmer!=null) {
			return ResponseEntity.ok(farmer);
		}return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				
	}
	
	@PatchMapping("{phone}")
	public ResponseEntity patchfarmerdetails(@PathVariable long phone,@RequestBody Farmer farmer) {
		if(farmerservice.patchFarmerDetails(phone, farmer)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	@PostMapping("addproduct/{phone}")
	public ResponseEntity addProduct(@PathVariable long phone,@RequestBody ProductView productview) {
		if(productservice.addProduct(phone, productview)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@GetMapping("product")
	public List<Product> getAllProducts() {
		return productservice.getAllProduct();
	}
	
	@GetMapping("product/{phone}")
	public List<Product> getFarmerProducts(@PathVariable long phone) {
		return productservice.getFarmerProduct(phone);
	}
	
	@PatchMapping("product/{id}")
	public ResponseEntity patchproductdetails(@PathVariable long id,@RequestBody Product product) {
		if(productservice.patchProductDetails(id, product)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("product/{id}")
	public ResponseEntity patchbuyerdetails(@PathVariable long id) {
		if(productservice.deleteProduct(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("order/{phone}")
	public List<Order> order(@PathVariable long phone) {
		return orderservice.getFarmerOrder(phone);
			
	}
	

}
