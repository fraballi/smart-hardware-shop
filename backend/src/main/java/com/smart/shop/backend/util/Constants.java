package com.smart.shop.backend.util;

import com.smart.shop.backend.domain.Product;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
public final class Constants {

   public static final String PRODUCTS_RESOURCE = "api/v1/products";

   public static final ThreadLocal<Product> MOCK_PRODUCT = new ThreadLocal<>();

   static {
      final var product = new Product(0, "AAA", 10.0d, 10, "A Product",
            "https://icon-library" + ".com/images/icon-for-product/icon-for-product-23.jpg");
      MOCK_PRODUCT.set(product);
   }

   private Constants() {
   }
}
