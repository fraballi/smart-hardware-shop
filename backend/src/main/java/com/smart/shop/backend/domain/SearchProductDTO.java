package com.smart.shop.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductDTO {

   private long id;

   private String sku;

   private String description;
}
