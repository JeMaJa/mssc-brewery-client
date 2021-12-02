package com.msscbrewery.msscbreweryclient.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * data creates getters and setters
 * 
 * Builder makes a builder patern, what that may be...
 * 
 * Now i don't have to create these things myself!!!
 */
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
	private UUID id;
	private String BeerName;
	private String beerStyle;
	private long upc;

}
