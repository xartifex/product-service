package com.xartifex.product.service.repository;

import com.xartifex.product.service.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

  Mono<Product> findBySku(Long sku);

}
