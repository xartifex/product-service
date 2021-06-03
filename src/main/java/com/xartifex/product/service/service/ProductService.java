package com.xartifex.product.service.service;

import com.xartifex.product.service.entity.Product;
import com.xartifex.product.service.repository.ProductRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Mono<Product> findBySku(Long sku) {
    return productRepository.findBySku(sku);
  }

  public Mono<Product> upsert(Product product) {
    return productRepository.findBySku(product.getSku())
        .flatMap(result -> {
          result.setSku(product.getSku());
          return productRepository.save(result);
        })
        .switchIfEmpty(productRepository.save(product));
  }

}
