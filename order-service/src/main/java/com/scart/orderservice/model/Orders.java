package com.scart.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="orders")
public class Orders {

    @Id
    public int orderId;
    public LocalDate orderDate;
    public int customerId;
    public double amountPaid;
    public String modeOfPayment;
    public String orderStatus;
    public int quantity;

}
