package com.smart.shop.backend.http;

import static com.smart.shop.backend.util.Constants.MOCK_PRODUCT;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
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
      final var response = client
            .get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(new ParameterizedTypeReference<Map<String, Object>>() {

            })
            .returnResult()
            .getResponseBody();

      final var descriptions = Set.of("Intel Processor", "AMD Processor", "Gamer Chair");
      Assertions
            .assertThat(response)
            .containsEntry("statusCode", "OK")
            .containsKey("message")
            .extracting("message")
            .asInstanceOf(InstanceOfAssertFactories.list(Product[].class))
            .isNotEmpty()
            .extracting("description", String.class)
            .contains(descriptions.toArray(String[]::new));
   }

   @Test
   @DisplayName("Check Resource - Create a Product")
   void check_create_product() {

      final var response = client
            .post()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(MOCK_PRODUCT.get())
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(new ParameterizedTypeReference<Map<String, String>>() {

            })
            .returnResult()
            .getResponseBody();

      Assertions.assertThat(response).containsEntry("statusCode", "CREATED");
   }

   @Test
   @DisplayName("Check Resource - Delete a Product")
   void check_delete_product() throws JsonProcessingException {

      Map<String, Object> response = getAllResponse();
      Assertions
            .assertThat(response)
            .containsEntry("statusCode", "OK")
            .containsKey("message")
            .asInstanceOf(InstanceOfAssertFactories.map(String.class, Object.class))
            .isNotEmpty();

      var products = (List<Map<String, Object>>) response.get("message");
      final var sku = products.get(0).get("sku");

      final var deleteResource = url + "/sku/" + sku;
      final var deleted = client
            .delete()
            .uri(deleteResource)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(new ParameterizedTypeReference<Map<String, Object>>() {

            })
            .returnResult()
            .getResponseBody();

      Assertions.assertThat(deleted).containsEntry("statusCode", "OK").containsEntry("message", "Successfully deleted");

      response = getAllResponse();
      products = (List<Map<String, Object>>) response.get("message");

      final var notFound = products.stream().noneMatch(map -> map.get("sku").equals(sku));
      Assertions.assertThat(notFound).isTrue();
   }

   private Map<String, Object> getAllResponse() {
      return client
            .get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(new ParameterizedTypeReference<Map<String, Object>>() {

            })
            .returnResult()
            .getResponseBody();
   }
}
