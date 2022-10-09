package com.iiht.companystock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.companystock.model.CompanyStock;
import com.iiht.companystock.service.impl.CompanyStockServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyStockController {
	
	@Autowired
	private CompanyStockServiceImpl companyStockServiceImpl;
	
	private Logger log = LoggerFactory.getLogger(CompanyStockController.class);
	
	
	@RequestMapping(value="/info/{companyCode}",method=RequestMethod.POST)
	public CompanyStock getCompanyDetails(@PathVariable String companyCode) {
		log.info("Company-stock-App: get by companycode API");
		log.info("companyCode: " + companyCode);
		return companyStockServiceImpl.getCompanyDetails(companyCode);
	}
	
	

}
