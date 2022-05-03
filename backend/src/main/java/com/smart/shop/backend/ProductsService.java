package com.smart.shop.backend;

import static com.smart.shop.backend.util.Constants.PRODUCTS_RESOURCE;

import java.net.URI;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smart.shop.backend.domain.Product;
import com.smart.shop.backend.domain.ProductsStorage;
import com.smart.shop.backend.domain.SearchProductDTO;
import com.smart.shop.backend.util.api.ApiOperation;
import com.smart.shop.backend.util.mapper.ProductsMapper;

import reactor.core.publisher.Mono;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Service
public record ProductsService(ProductsStorage storage, ProductsMapper mapper) {

   public Mono<ResponseEntity<ApiOperation>> getAll() {
      final var data = storage.getAll().stream().sorted(Comparator.comparing(Product::getDescription)).toArray(Product[]::new);
      final var apiOperation = ApiOperation.builder().message(data).build();
      final var entity = ResponseEntity.ok().body(apiOperation);
      return Mono.just(entity);
   }

   public Mono<ResponseEntity<ApiOperation>> search() {
      final var data = storage
            .getAll()
            .stream()
            .map(mapper::toSearch)
            .sorted(Comparator.comparing(SearchProductDTO::getDescription))
            .collect(Collectors.toCollection(LinkedHashSet::new))
            .toArray(SearchProductDTO[]::new);

      final var apiOperation = ApiOperation.builder().message(data).build();
      final var entity = ResponseEntity.ok().body(apiOperation);
      return Mono.just(entity);
   }

   public Mono<ResponseEntity<ApiOperation>> get(final String sku) {
      final var product = storage.get(sku);

      final var apiOperation = ApiOperation.builder();
      final var entity = product.map(prd -> {
         apiOperation.message(prd);
         return new ResponseEntity<>(apiOperation.build(), HttpStatus.OK);
      }).orElseGet(() -> {
         apiOperation.statusCode(HttpStatus.NOT_FOUND).message(HttpStatus.NOT_FOUND.getReasonPhrase());
         return new ResponseEntity<>(apiOperation.build(), HttpStatus.NOT_FOUND);
      });

      return Mono.just(entity);
   }

   public Mono<ResponseEntity<ApiOperation>> saveOrUpdate(final Product product) {
      final var savedProduct = storage.addOrUpdate(product);
      final var apiOperation = ApiOperation.builder().statusCode(HttpStatus.CREATED).message("Successfully created").build();
      final var createdURI = URI.create(PRODUCTS_RESOURCE + "/" + savedProduct.getId());
      final var entity = ResponseEntity.created(createdURI).body(apiOperation);
      return Mono.just(entity);
   }

   public Mono<ResponseEntity<ApiOperation>> delete(final String sku) {
      final var apiOperation = ApiOperation.builder();
      final ResponseEntity<ApiOperation> entity;

      final var deleted = Objects.nonNull(storage.remove(sku));
      if (deleted) {
         apiOperation.message("Successfully deleted");
         entity = new ResponseEntity<>(apiOperation.build(), HttpStatus.OK);
      } else {
         apiOperation.statusCode(HttpStatus.INTERNAL_SERVER_ERROR).message("Error deleting");
         entity = new ResponseEntity<>(apiOperation.build(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return Mono.just(entity);
   }

   public Mono<ResponseEntity<ApiOperation>> deleteById(final long id) {
      final var apiOperation = ApiOperation.builder();
      final ResponseEntity<ApiOperation> entity;

      final var deleted = storage.removeById(id);
      if (deleted) {
         apiOperation.message("Successfully deleted");
         entity = new ResponseEntity<>(apiOperation.build(), HttpStatus.OK);
      } else {
         apiOperation.statusCode(HttpStatus.INTERNAL_SERVER_ERROR).message("Error deleting the record");
         entity = new ResponseEntity<>(apiOperation.build(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return Mono.just(entity);
   }
}
