package com.smart.shop.backend.domain;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

   private long id;

   @NotBlank
   private String sku;

   private double price;

   private int quantity;

   @NotBlank
   private String description;

   @URL
   private String url;
}
