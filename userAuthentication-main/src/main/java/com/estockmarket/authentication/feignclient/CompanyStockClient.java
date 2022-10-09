package com.estockmarket.authentication.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.estockmarket.authentication.model.CompanyStock;

@FeignClient("company-stock-app/api/v1.0/market/company")
public interface CompanyStockClient {
	
	@RequestMapping(value="/info/{companyCode}",method=RequestMethod.POST)
	public CompanyStock getCompanyDetails(@PathVariable String companyCode);

}
