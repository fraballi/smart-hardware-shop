package com.smart.shop.backend.resource;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.shop.backend.ProductsService;
import com.smart.shop.backend.domain.Product;
import com.smart.shop.backend.domain.SearchProductDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@RestController
@CrossOrigin(origins = "${app.cors.allowed}")
@RequestMapping("api/v1/products")
public record ProductsResource(ProductsService service) {

   @GetMapping
   public Flux<Product> getAll() {
      return service.getAll();
   }

   @GetMapping("search")
   public Flux<SearchProductDTO> search() {
      return service.search();
   }

   @GetMapping("/sku/{sku}")
   public Mono<Product> get(@PathVariable final String sku) {
      return service.get(sku);
   }

   @PostMapping
   public Mono<Product> post(@RequestBody @NotNull Product product) {
      return service.saveOrUpdate(product);
   }

   @DeleteMapping("/{id}")
   public Mono<Boolean> deleteById(@PathVariable final long id) {
      return service.deleteById(id);
   }

   @DeleteMapping("/sku/{sku}")
   public Mono<Boolean> delete(@PathVariable final String sku) {
      return service.delete(sku);
   }
}