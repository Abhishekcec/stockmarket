package com.iiht.companystock.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.iiht.companystock.feignclient.CompanyClient;
import com.iiht.companystock.feignclient.StockClient;
import com.iiht.companystock.model.Company;
import com.iiht.companystock.model.CompanyStock;
import com.iiht.companystock.model.StockDetails;
import com.iiht.companystock.service.CompanyStockService;

@Service
public class CompanyStockServiceImpl implements CompanyStockService{

	 
	 @Autowired
	 private WebClient.Builder webClientBuilder;
	 
	 @Autowired
	 private CompanyClient companyClient;
	 
	 @Autowired
	 private StockClient stockClient;
	 
	 
	@Override 
	public CompanyStock getCompanyDetails(String companyCode) {
		System.out.print(companyCode);
		Company company = companyClient.getCompanyDetails(companyCode);
//		Company company=webClientBuilder.build().post().uri("http://COMPANY-APP/api/v1.0/market/company/info/"+companyCode)
//				.retrieve().bodyToMono(Company.class).block();
		
		StockDetails stockDetailsDTO=new StockDetails();
	    if(company.getCompanyCode()!=null)
	    	stockDetailsDTO = stockClient.getStocksByCompanyCode(companyCode);
//		 stockDetailsDTO = webClientBuilder
//			    .build()
//			    .post()
//			    .uri("http://STOCK-APP/api/v1.0/market/stock/get/"+companyCode)
//			    .retrieve()
//			    .bodyToMono(StockDetails.class).block();
		
		return new CompanyStock(company ,stockDetailsDTO);
	}
	
}
