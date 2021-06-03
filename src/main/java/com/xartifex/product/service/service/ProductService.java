package com.xartifex.product.service.service;

import com.xartifex.product.service.dto.ProductDto;
import com.xartifex.product.service.mapper.ProductMapper;
import com.xartifex.product.service.repository.ProductRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ProductService {

  private ProductRepository productRepository;
  private ProductMapper mapper;

  public ProductService(ProductRepository productRepository,
      ProductMapper mapper) {
    this.productRepository = productRepository;
    this.mapper = mapper;
  }

  public Mono<ProductDto> findBySku(Long sku) {
    return productRepository.findBySku(sku).map(mapper::toDto);
  }

  public Mono<ProductDto> upsert(ProductDto productDto) {
    return productRepository.findBySku(productDto.getSku())
        .flatMap(result -> {
          result.setSku(productDto.getSku());
          return productRepository.save(result);
        })
        .switchIfEmpty(productRepository.save(mapper.toEntity(productDto)))
        .map(mapper::toDto);
  }

}
