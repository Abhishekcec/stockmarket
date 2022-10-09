package com.estockmarket.authentication.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.estockmarket.authentication.feignclient.StockClient;
import com.estockmarket.authentication.model.Stock;
import com.estockmarket.authentication.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.estockmarket.authentication.model.StockDetails;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/market/stock")
public class StockController {

	@Autowired
	private StockService stockService;
	
	private Logger log = LoggerFactory.getLogger(StockController.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private StockClient stockClient;

	@PostMapping(value = "/add")
	public String saveCompany(@RequestBody Stock stock) {
		log.info("Authentication: add stock API");
		try {
			log.info("request body : " + mapper.writeValueAsString(stock));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return stockClient.saveCompany(stock);
	}
	
	@PostMapping("/get/{companyCode}/{startDate}/{endDate}")
	public StockDetails getStock(@PathVariable String companyCode, @PathVariable @DateTimeFormat String startDate, @PathVariable @DateTimeFormat String endDate){
		log.info("Authentication: search by companycode API");
			log.info("companyCode : " + companyCode);
		return stockClient.getStock(companyCode, startDate, endDate);
	}

}
