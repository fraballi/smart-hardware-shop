package com.smart.shop.backend.http;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.smart.shop.backend.domain.Product;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsResourceTests {

   @LocalServerPort
   private int port;

   @Autowired
   private WebTestClient client;

   private String url;

   @BeforeEach
   void startUp() {
      url = String.format("http://localhost:%d/api/v1/products", port);
   }

   @Test
   @DisplayName("Check Resource - Get All Products")
   void check_get_all_products() {
      final var products = client
            .get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Product[].class)
            .returnResult()
            .getResponseBody();

      final var descriptions = Set.of("Intel Processor", "AMD Processor", "Gamer Chair");
      Assertions.assertThat(products).isNotEmpty().extracting("description", String.class).contains(descriptions.toArray(String[]::new));
   }

   @Test
   @DisplayName("Check Resource - Create a Product")
   void check_create_product() {
      final var mockProduct = new Product(0, "AAA", 10.0d, 10, "A Product",
            "https://icon-library" + ".com/images/icon-for-product/icon-for-product-23.jpg");

      final Product product = client
            .post()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(mockProduct)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Product.class)
            .returnResult()
            .getResponseBody();

      Assertions.assertThat(product).isNotNull().hasFieldOrPropertyWithValue("sku", mockProduct.getSku());
   }

   @Test
   @DisplayName("Check Resource - Delete a Product")
   void check_delete_product() {

      var products = client
            .get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Product[].class)
            .returnResult()
            .getResponseBody();

      Assertions.assertThat(products).isNotEmpty();
      final var selectedProduct = products[0];

      final var deleteResource = url + "/sku/" + selectedProduct.getSku();
      final var deleted = client
            .delete()
            .uri(deleteResource)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Boolean.class)
            .returnResult()
            .getResponseBody();

      Assertions.assertThat(deleted).isTrue();

      products = client
            .get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Product[].class)
            .returnResult()
            .getResponseBody();

      Assertions.assertThat(products).isNotEmpty().extracting("sku").doesNotContain(selectedProduct.getSku());
   }
}
