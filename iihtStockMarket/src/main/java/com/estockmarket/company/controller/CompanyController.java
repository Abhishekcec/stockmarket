package com.estockmarket.company.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estockmarket.company.model.Company;
import com.estockmarket.company.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	private Logger log = LoggerFactory.getLogger(CompanyController.class);
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/register" ,method=RequestMethod.POST)
	public String saveCompany(@RequestBody Company company) {
		log.info("Company-App: register company API");
		try {
			log.info("request body : " + mapper.writeValueAsString(company));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return companyService.saveCompany(company);

	}

	@GetMapping(value = "/getall" )
	List<Company> getAllCompanies() {
		log.info("Company-App: get all company API");
		return companyService.getAllCompanies();
	}

	@RequestMapping(value = "/info/{companyCode}" ,method=RequestMethod.POST)
	public Company getCompanyDetails(@PathVariable String companyCode) {
		log.info("Company-App: get by companycode API");
		log.info("companyCode: " + companyCode);
		return companyService.getCompanyDetails(companyCode);
	}

	@RequestMapping(value = "/delete/{companyCode}",method=RequestMethod.DELETE)
	public String deleteCompanyDetails(@PathVariable String companyCode) {
		log.info("Company-App: delete by companycode API");
		log.info("companyCode: " + companyCode);
		return companyService.deleteCompanyDetails(companyCode);
	}

}
