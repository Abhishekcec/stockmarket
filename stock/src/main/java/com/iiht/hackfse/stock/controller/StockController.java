package com.iiht.hackfse.stock.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.hackfse.stock.model.Stock;
import com.iiht.hackfse.stock.service.StockService;
import com.iiht.hackfse.stock.transformer.StockDetails;


@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/market/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	private Logger log = LoggerFactory.getLogger(StockController.class);
	private ObjectMapper mapper = new ObjectMapper();

	
	@PostMapping(value="/add")
	public String saveCompany(@RequestBody Stock stock){
		log.info("stock-App: add stock API");
		try {
			log.info("request body : " + mapper.writeValueAsString(stock));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		stockService.saveStock(stock);
		return "saved successfully";
		
	}
	
	@PostMapping("/get/{companyCode}/{startDate}/{endDate}")
	public StockDetails getStock(@PathVariable String companyCode, @PathVariable @DateTimeFormat String startDate, @PathVariable @DateTimeFormat String endDate){
			log.info("stock-App: search by companycode API");
			log.info("companyCode: " + companyCode);
			LocalDateTime newStartDate = LocalDateTime.parse(startDate);
			LocalDateTime newEndDate = LocalDateTime.parse(endDate); 
			return stockService.getCompanyStockPrice(newStartDate, newEndDate, companyCode);
	}
	
	@PostMapping("/get/{companyCode}")
	public StockDetails getStocksByCompanyCode(@PathVariable String companyCode){
			log.info("stock-App: get by companycode API");
			log.info("companyCode: " + companyCode);
			return stockService.getStocksByCompanyCode(companyCode);
	}
	
	@RequestMapping(value = "/delete/{companyCode}",method=RequestMethod.DELETE)
	public String deleteCompanyDetails(@PathVariable String companyCode) {
		log.info("stock-App: delete by companycode API");
		log.info("companyCode: " + companyCode);
		return stockService.deleteStocksByCompanyCode(companyCode);
	}
	
	
}
