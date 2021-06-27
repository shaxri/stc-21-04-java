package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
@Value
public class Product {
    long id;
    String name;
    double price;
    String manufacturer;
}
