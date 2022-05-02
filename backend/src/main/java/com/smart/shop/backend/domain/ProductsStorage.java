package com.smart.shop.backend.domain;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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

   AtomicInteger counter = new AtomicInteger();

   public synchronized Product get(final String sku) {
      return storage.get(sku);
   }

   public synchronized Set<Product> getAll() {
      return Set.copyOf(storage.values());
   }

   public synchronized void addOrUpdate(final Product product) {
      if (storage.containsKey(product.getSku())) {
         final var id = storage.get(product.getSku()).getId();
         product.setId(id);
         storage.put(product.getSku(), product);
      } else {
         final var id = counter.getAndAdd(1);
         product.setId(id);
         storage.putIfAbsent(product.getSku(), product);
      }
   }

   public synchronized Product remove(final String sku) {
      return storage.remove(sku);
   }

   public synchronized boolean removeById(final long id) {
      return storage.entrySet().removeIf(entry -> entry.getValue().getId() == id);
   }
}
