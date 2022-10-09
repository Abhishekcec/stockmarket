package com.estockmarket.authentication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estockmarket.authentication.model.CompanyStock;
import com.estockmarket.authentication.service.impl.CompanyStockServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.estockmarket.authentication.feignclient.CompanyClient;
import com.estockmarket.authentication.feignclient.CompanyStockClient;
import com.estockmarket.authentication.feignclient.StockClient;
import com.estockmarket.authentication.model.Company;
import com.estockmarket.authentication.service.CompanyService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {
	
	@Autowired
	private CompanyStockServiceImpl companyStockServiceImpl;
	
	private Logger log = LoggerFactory.getLogger(CompanyController.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private CompanyClient client;
	
	@Autowired
	private CompanyStockClient companyStockClient;
	
	@Autowired
	private StockClient stockClient;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "/register" ,method=RequestMethod.POST)
	public String saveCompany(@RequestBody Company company) {
		
		log.info("Authentication: register company API");
		try {
			log.info("request body : " + mapper.writeValueAsString(company));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return client.saveCompany(company);

	}
	
	@RequestMapping(value="/info/{companyCode}",method=RequestMethod.POST)
	public CompanyStock getCompanyDetails(@PathVariable String companyCode) {
		log.info("Authentication: get company API");
		log.info("companyCode : " + companyCode);
		return companyStockClient.getCompanyDetails(companyCode);
	}
	
	@GetMapping(value = "/getall")
	List<Company> getAllCompanies() {
		
		log.info("Authentication: get all companies");
		return client.getAllCompanies();
	}
	
	@RequestMapping(value = "/delete/{companyCode}",method=RequestMethod.DELETE)
	public String deleteCompanyDetails(@PathVariable String companyCode) {
		log.info("Authentication: delete company API");
		log.info("companyCode : " + companyCode);
		return client.deleteCompanyDetails(companyCode) + " and " + stockClient.deleteCompanyDetails(companyCode);
	}

}
