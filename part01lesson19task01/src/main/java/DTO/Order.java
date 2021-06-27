package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.sql.Date;

@Data
@AllArgsConstructor
@Value
public class Order {
    long id;
    long number;
    User user;
    Product product;
    OrderStatus status;
    Date order_date;
    String delivery_information;
}

