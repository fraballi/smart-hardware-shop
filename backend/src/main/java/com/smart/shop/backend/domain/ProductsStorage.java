package com.smart.shop.backend.domain;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Value
public class ProductsStorage {

   @Getter(AccessLevel.PRIVATE)
   Map<String, Product> storage = new ConcurrentHashMap<>();

   public synchronized Optional<Product> get(final String sku) {
      return Optional.ofNullable(storage.get(sku));
   }

   public synchronized Set<Product> getAll() {
      return Set.copyOf(storage.values());
   }

   public synchronized Product addOrUpdate(final Product product) {
      if (storage.containsKey(product.getSku())) {
         final var id = storage.get(product.getSku()).getId();
         product.setId(id);
         storage.put(product.getSku(), product);
      } else {
         final var id = storage.size() + 1;
         product.setId(id);
         storage.putIfAbsent(product.getSku(), product);
      }
      return product;
   }

   public synchronized Product remove(final String sku) {
      return storage.remove(sku);
   }

   public synchronized boolean removeById(final long id) {
      return storage.entrySet().removeIf(entry -> entry.getValue().getId() == id);
   }
}
