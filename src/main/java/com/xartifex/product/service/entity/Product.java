package com.xartifex.product.service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {

  @Id
  private Long id;

  private Long sku;

}
