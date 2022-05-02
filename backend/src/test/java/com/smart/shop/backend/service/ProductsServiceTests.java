package com.smart.shop.backend.service;

import java.time.Duration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.smart.shop.backend.ProductsService;
import com.smart.shop.backend.StoreApplication;
import com.smart.shop.backend.domain.Product;

import reactor.test.StepVerifier;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@ExtendWith({ SpringExtension.class })
@ContextConfiguration(classes = { StoreApplication.class })
class ProductsServiceTests {

   @Autowired
   private ProductsService productsService;

   @Test
   @DisplayName("Check Service - Get All Products")
   void check_get_all_products() {
      final var allProducts = productsService.getAll();
      StepVerifier.create(allProducts).expectNextCount(15L).verifyComplete();

      final var found = allProducts.any(prd -> prd.getDescription().equalsIgnoreCase("Intel Processor")).blockOptional(Duration.ofSeconds(5));
      Assertions.assertThat(found).isNotEmpty().get().isEqualTo(true);
   }

   @Test
   @DisplayName("Check Service - Create a Product")
   void check_create_a_product() {

      final var mockProduct = new Product(0, "BBB", 10.0d, 10, "A Product",
            "https://icon-library" + ".com/images/icon-for-product/icon-for-product-23.jpg");

      final var created = productsService.saveOrUpdate(mockProduct);
      StepVerifier.create(created).expectNextMatches(prd -> prd.getSku().equals(mockProduct.getSku())).verifyComplete();

      final var allProducts = productsService.getAll();
      final var found = allProducts.any(prd -> prd.getSku().equalsIgnoreCase("BBB")).blockOptional(Duration.ofSeconds(5));

      Assertions.assertThat(found).isNotEmpty().get().isEqualTo(true);
   }

   @Test
   @DisplayName("Check Service - Delete a Product")
   void check_delete_a_product() {

      final var mockProduct = new Product(0, "BBB", 10.0d, 10, "A Product",
            "https://icon-library" + ".com/images/icon-for-product/icon-for-product-23.jpg");

      final var created = productsService.saveOrUpdate(mockProduct);
      StepVerifier.create(created).expectNextMatches(prd -> prd.getSku().equals(mockProduct.getSku())).verifyComplete();

      final var deleted = productsService.delete(mockProduct.getSku());
      StepVerifier.create(deleted).expectNext(true).verifyComplete();

      final var allProducts = productsService.getAll();
      final var notFound = allProducts.filter(prd -> prd.getSku().equalsIgnoreCase("BBB"));

      StepVerifier.create(notFound).expectNextCount(0L).verifyComplete();
   }
}
