package com.smart.shop.backend;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smart.shop.backend.domain.Product;
import com.smart.shop.backend.domain.ProductsStorage;
import com.smart.shop.backend.domain.SearchProductDTO;
import com.smart.shop.backend.util.mapper.ProductsMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Service
public record ProductsService(ProductsStorage storage, ProductsMapper mapper) {

   public Flux<Product> getAll() {
      final var data = storage.getAll().stream().sorted(Comparator.comparing(Product::getDescription)).toArray(Product[]::new);
      return Flux.just(data);
   }

   public Flux<SearchProductDTO> search() {
      final var data = storage
            .getAll()
            .stream()
            .map(mapper::toSearch)
            .sorted(Comparator.comparing(SearchProductDTO::getDescription))
            .collect(Collectors.toCollection(LinkedHashSet::new))
            .toArray(SearchProductDTO[]::new);
      return Flux.just(data);
   }

   public Mono<Product> get(final String sku) {
      final var product = storage.get(sku);
      return Mono.just(product);
   }

   public Mono<Product> saveOrUpdate(final Product product) {
      storage.addOrUpdate(product);
      return Mono.just(product);
   }

   public Mono<Boolean> delete(final String sku) {
      final var deleted = Objects.nonNull(storage.remove(sku));
      return Mono.just(deleted);
   }

   public Mono<Boolean> deleteById(final long id) {
      final var deleted = storage.removeById(id);
      return Mono.just(deleted);
   }
}
