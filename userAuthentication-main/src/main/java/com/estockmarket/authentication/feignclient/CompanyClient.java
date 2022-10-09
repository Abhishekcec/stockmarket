package com.estockmarket.authentication.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.estockmarket.authentication.model.Company;
import com.estockmarket.authentication.model.CompanyStock;

@FeignClient(url="http://localhost:8084/api/v1.0/market/company",name="COMPANY-APP")
//@FeignClient("company-app/api/v1.0/market/company")
public interface CompanyClient {
	
	@RequestMapping(value = "/register" ,method=RequestMethod.POST)
	public String saveCompany(@RequestBody Company company);
	
	@GetMapping(value = "/getall")
	List<Company> getAllCompanies();
	
	@RequestMapping(value="/info/{companyCode}",method=RequestMethod.POST)
	public CompanyStock getCompanyDetails(@PathVariable String companyCode);
	
	@RequestMapping(value = "/delete/{companyCode}",method=RequestMethod.DELETE)
	public String deleteCompanyDetails(@PathVariable String companyCode);

}
