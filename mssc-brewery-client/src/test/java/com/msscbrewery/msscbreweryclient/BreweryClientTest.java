package com.msscbrewery.msscbreweryclient;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.msscbrewery.msscbreweryclient.web.client.BreweryClient;
import com.msscbrewery.msscbreweryclient.web.model.BeerDto;
import com.msscbrewery.msscbreweryclient.web.model.CustomerDto;

@SpringBootTest
class BreweryClientTest {
	
	@Autowired
	BreweryClient client;

	@Test
	void getBeerById() {
		BeerDto dto = client.getBeerById(UUID.randomUUID());
		assertNotNull(dto);
	}
	
	@Test
	void saveNewBeer() {
		BeerDto beerDto = BeerDto.builder().beerName("E+Y Christmas Ale").beerStyle("SAISON").upc(1002L).price(new BigDecimal("12.4")).build();
		
		URI uri = client.saveNewBeer(beerDto);
		assertNotNull(uri);
		
		System.out.println(uri.toString());
		
	}
	
	@Test
	void testUpdateBeer() {
		//given
		BeerDto beerDto = BeerDto.builder().beerName("E+Y Christmas Ale").build();
		client.updateBeer(UUID.randomUUID(), beerDto);
	}
	
	@Test
	void testDeleteBeer() {
		//given
		
		client.deleteBeer(UUID.randomUUID());
	}
	@Test
	void getCustById() {
		CustomerDto dto = client.getCustomerById(UUID.randomUUID());
		assertNotNull(dto);
	}
	
	@Test
	void saveNewCust() {
		CustomerDto dto  = CustomerDto.builder().name("Yannick").build();
		
		URI uri = client.saveNewCustomer(dto);
		assertNotNull(uri);
		
		System.out.println(uri.toString());
		
	}
	
	@Test
	void testUpdateCust() {
		//given
		CustomerDto dto  = CustomerDto.builder().name("Yannick").build();
		client.updateCustomer(UUID.randomUUID(), dto);
	}
	
	@Test
	void testDeleteCustomer() {
		//given
		
		client.deleteCustomer(UUID.randomUUID());
	}

}
