package com.estockmarket.authentication.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.estockmarket.authentication.model.Stock;
import com.estockmarket.authentication.model.StockDetails;

@FeignClient("STOCK-APP/api/v1.0/market/stock")
public interface StockClient {
	
	@PostMapping(value="/add")
	public String saveCompany(@RequestBody Stock stock);
	
	@PostMapping("/get/{companyCode}/{startDate}/{endDate}")
	public StockDetails getStock(@PathVariable String companyCode, @PathVariable @DateTimeFormat String startDate, @PathVariable @DateTimeFormat String endDate);
	
	@RequestMapping(value = "/delete/{companyCode}",method=RequestMethod.DELETE)
	public String deleteCompanyDetails(@PathVariable String companyCode);

}
