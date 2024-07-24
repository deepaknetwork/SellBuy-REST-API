package com.webapp.SellBuy.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.SellBuy.Model.Organization;
import com.webapp.SellBuy.Repository.OrganizationRepo;
import com.webapp.SellBuy.Service.OrganizationService;
import com.webapp.SellBuy.View.FarmerLogin;

@RestController
@RequestMapping("org")
public class OrgController {
	
	@Autowired
	OrganizationRepo org;
	
	@Autowired
	OrganizationService orgservice;
	
	@GetMapping("start")
	public ResponseEntity<String> start() {
		try {
			org.insert(new Organization("SELLBUY",110,1000));
			return ResponseEntity.ok("Started");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Starting failed");
		}
	}
	
	@GetMapping("buyertopup/{id}/{topup}")
	public ResponseEntity customerTopup(@PathVariable long id,@PathVariable long topup) {
			if(orgservice.buyerTopup(id,topup)) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}return ResponseEntity.status(HttpStatus.CONFLICT).build();	
	}
	

	@GetMapping("customertopup/{id}/{topup}")
	public ResponseEntity farmerTopup(@PathVariable long id,@PathVariable long topup) {
			if(orgservice.farmerTopup(id,topup)) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}return ResponseEntity.status(HttpStatus.CONFLICT).build();	
	}
}
