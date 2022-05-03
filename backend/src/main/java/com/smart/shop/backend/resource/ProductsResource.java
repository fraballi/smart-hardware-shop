package com.smart.shop.backend.resource;

import static com.smart.shop.backend.util.Constants.PRODUCTS_RESOURCE;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
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
import com.smart.shop.backend.util.api.ApiOperation;

import reactor.core.publisher.Mono;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@RestController
@CrossOrigin(origins = "${app.cors.allowed}")
@RequestMapping(PRODUCTS_RESOURCE)
public record ProductsResource(ProductsService service) {

   @GetMapping
   public Mono<ResponseEntity<ApiOperation>> getAll() {
      return service.getAll();
   }

   @GetMapping("search")
   public Mono<ResponseEntity<ApiOperation>> search() {
      return service.search();
   }

   @GetMapping("/sku/{sku}")
   public Mono<ResponseEntity<ApiOperation>> get(@PathVariable final String sku) {
      return service.get(sku);
   }

   @PostMapping
   public Mono<ResponseEntity<ApiOperation>> post(@RequestBody @NotNull Product product) {
      return service.saveOrUpdate(product);
   }

   @DeleteMapping("/{id}")
   public Mono<ResponseEntity<ApiOperation>> deleteById(@PathVariable final long id) {
      return service.deleteById(id);
   }

   @DeleteMapping("/sku/{sku}")
   public Mono<ResponseEntity<ApiOperation>> delete(@PathVariable final String sku) {
      return service.delete(sku);
   }
}