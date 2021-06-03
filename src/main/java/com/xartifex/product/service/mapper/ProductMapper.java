package com.xartifex.product.service.mapper;

import com.xartifex.product.service.dto.ProductDto;
import com.xartifex.product.service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto toDto(Product product);

  @Mapping(target = "id", ignore = true)
  Product toEntity(ProductDto productDto);
}
