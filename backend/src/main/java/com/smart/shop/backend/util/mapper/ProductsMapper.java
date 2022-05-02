package com.smart.shop.backend.util.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.smart.shop.backend.domain.Product;
import com.smart.shop.backend.domain.SearchProductDTO;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductsMapper {

   @Mapping(target = "id")
   @Mapping(target = "sku")
   @Mapping(target = "description")
   public SearchProductDTO toSearch(Product product);
}
