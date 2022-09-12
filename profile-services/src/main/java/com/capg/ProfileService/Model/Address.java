package com.capg.ProfileService.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="address")

public class Address {

	private Integer houseNumber;

	private String streetName;

	private String colonyName;

	private String city;

	private String state;

	private int pincode;


}

