package com.msscbrewery.msscbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.msscbrewery.msscbreweryclient.web.model.BeerDto;
import com.msscbrewery.msscbreweryclient.web.model.CustomerDto;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = true)
public class BreweryClient {
	public final String BEER_PATH_V1 = "/api/v1/beer/";
	public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	private String apiHost;
	private final RestTemplate restTemplate;
	
	public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public BeerDto getBeerById(UUID uuid) {
		return restTemplate.getForObject(apiHost + BEER_PATH_V1 + uuid, BeerDto.class);
		
	}
	
	public URI saveNewBeer(BeerDto beerDto) {
		return restTemplate.postForLocation(apiHost + BEER_PATH_V1, beerDto);
		
	}
	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}
	
	public void updateBeer(UUID uuid, BeerDto beerDto) {
		restTemplate.put(apiHost + BEER_PATH_V1 + uuid.toString(), beerDto);
		
	}
	
	public void deleteBeer(UUID uuid) {
		restTemplate.delete(apiHost + BEER_PATH_V1 + uuid.toString());
	}
	
	public CustomerDto getCustomerById(UUID uuid) {
		return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + uuid,CustomerDto.class);
	}
	
	public URI saveNewCustomer(CustomerDto customerDto) {
		return restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1, customerDto);
		
	}
	
	public void updateCustomer(UUID uuid, CustomerDto customerDto) {
		restTemplate.put(apiHost + CUSTOMER_PATH_V1 + uuid.toString(), customerDto);
		
	}
	
	public void deleteCustomer(UUID uuid) {
		restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + uuid.toString());
	}
	
	
}
