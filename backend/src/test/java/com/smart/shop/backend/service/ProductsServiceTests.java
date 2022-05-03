package com.smart.shop.backend.service;

import static com.smart.shop.backend.util.Constants.MOCK_PRODUCT;

import java.util.Arrays;
import java.util.Objects;

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
      StepVerifier.create(allProducts).assertNext(entity -> {
         final var products = Objects.requireNonNull(entity.getBody()).getMessage(Product[].class);
         final var condition = Arrays.stream(products).anyMatch(prd -> prd.getDescription().equalsIgnoreCase("Intel Processor"));
         Assertions.assertThat(condition).isTrue();
      }).verifyComplete();
   }

   @Test
   @DisplayName("Check Service - Create a Product")
   void check_create_a_product() {

      final var created = productsService.saveOrUpdate(MOCK_PRODUCT.get());
      StepVerifier.create(created).assertNext(entity -> {
         final var message = Objects.requireNonNull(entity.getBody()).getMessage(String.class);
         Assertions.assertThat(message).isEqualTo("Successfully created");
      }).verifyComplete();
   }

   @Test
   @DisplayName("Check Service - Delete a Product")
   void check_delete_a_product() {

      final var created = productsService.saveOrUpdate(MOCK_PRODUCT.get());
      StepVerifier.create(created).assertNext(entity -> {
         final var message = Objects.requireNonNull(entity.getBody()).getMessage(String.class);
         Assertions.assertThat(message).isEqualTo("Successfully created");
      }).verifyComplete();

      final var deleted = productsService.delete(MOCK_PRODUCT.get().getSku());
      StepVerifier.create(deleted).assertNext(entity -> {
         final var message = Objects.requireNonNull(entity.getBody()).getMessage(String.class);
         Assertions.assertThat(message).isEqualTo("Successfully deleted");
      }).verifyComplete();

      final var allProducts = productsService.getAll();
      StepVerifier.create(allProducts).assertNext(entity -> {
         final var products = Objects.requireNonNull(entity.getBody()).getMessage(Product[].class);
         final var condition = Arrays.stream(products).noneMatch(prd -> prd.getSku().equalsIgnoreCase(MOCK_PRODUCT.get().getSku()));
         Assertions.assertThat(condition).isTrue();
      }).verifyComplete();
   }
}
