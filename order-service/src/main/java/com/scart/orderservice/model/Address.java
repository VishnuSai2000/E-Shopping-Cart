package com.scart.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    public int customerId;
    public String fullName;
    public String mobileNumber;
    public int doorNo;
    public  String city;
    public int pinCode;
    public String state;

}
