package com.iiht.companystock.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iiht.companystock.model.Company;

@FeignClient("company-app/api/v1.0/market/company")
public interface CompanyClient {
	
	@RequestMapping(value = "/info/{companyCode}" ,method=RequestMethod.POST)
	public Company getCompanyDetails(@PathVariable String companyCode);

}

