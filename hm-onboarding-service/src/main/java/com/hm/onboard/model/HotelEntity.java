package com.hm.onboard.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("hotel_entity")
public class HotelEntity {
	
	private String id;
	
	private String name;
	
	private String status;
	
	private String userId;

}
