package com.msscbrewery.msscbreweryclient;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.msscbrewery.msscbreweryclient.web.client.BreweryClient;
import com.msscbrewery.msscbreweryclient.web.model.BeerDto;

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
		BeerDto beerDto = BeerDto.builder().BeerName("E+Y Christmas Ale").build();
		
		URI uri = client.saveNewBeer(beerDto);
		assertNotNull(uri);
		
		System.out.println(uri.toString());
		
	}
	
	@Test
	void testUpdateBeer() {
		//given
		BeerDto beerDto = BeerDto.builder().BeerName("E+Y Christmas Ale").build();
		client.updateBeer(UUID.randomUUID(), beerDto);
	}
	
	@Test
	void testDeleteBeer() {
		//given
		
		client.deleteBeer(UUID.randomUUID());
	}

}
