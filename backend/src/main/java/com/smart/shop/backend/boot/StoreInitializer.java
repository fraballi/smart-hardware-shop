package com.smart.shop.backend.boot;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smart.shop.backend.domain.Product;
import com.smart.shop.backend.domain.ProductsStorage;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Configuration
public class StoreInitializer {

   private static final Random RND = new Random();

   @Bean
   public ProductsStorage storage() {
      final var storage = new ProductsStorage();
      initialize(storage);

      return storage;
   }

   private void initialize(final ProductsStorage storage) {
      final var picture = "https://icon-library.com/images/icon-for-product/icon-for-product-23.jpg";
      final var hardware = Arrays.asList("Intel Processor", "AMD Processor", "MSI Display", "MSI Laptop", "GigaByte MotherBoard",
            "Seagate Barracuda HDD", "Gamer Keyboard", "Gamer Mouse", "WebCam", "Chassis", "Smartphone", "Microphone", "Gamer Chair",
            "Gamer Headphones", "Raspberry PI");

      hardware.forEach(description -> {
         final var sku = UUID.randomUUID().toString();
         final var quantity = RND.nextInt(100);
         final var price = Math.round(RND.nextDouble(1000)) / 100d;
         final var product = new Product(0, sku, price, quantity, description, picture);
         storage.addOrUpdate(product);
      });
   }
}
