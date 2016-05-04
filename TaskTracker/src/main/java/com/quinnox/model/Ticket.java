package com.quinnox.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ticket")
public class Ticket {

	@Id
	private String id;
	
	private String subject;
	private String description;
	
	
}
