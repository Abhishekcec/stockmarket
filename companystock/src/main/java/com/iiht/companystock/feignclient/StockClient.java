package com.iiht.companystock.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iiht.companystock.model.StockDetails;

@FeignClient("STOCK-APP/api/v1.0/market/stock")
public interface StockClient {
	
	@PostMapping("/get/{companyCode}")
	public StockDetails getStocksByCompanyCode(@PathVariable String companyCode);

}
