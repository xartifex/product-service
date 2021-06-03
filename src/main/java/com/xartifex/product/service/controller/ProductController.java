package com.xartifex.product.service.controller;

import com.xartifex.product.service.dto.ProductDto;
import com.xartifex.product.service.entity.Product;
import com.xartifex.product.service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{sku}")
  private Mono<ResponseEntity<ProductDto>> getEmployeeById(@PathVariable Long sku) {
    return productService.findBySku(sku).map(ResponseEntity::ok)
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @PutMapping("/{sku}/add")
  private Mono<ResponseEntity<ProductDto>> saveEmployee(@PathVariable Long sku, ProductDto product) {
    product.setSku(sku);
    return productService.upsert(product).map(ResponseEntity::ok);
  }

}
