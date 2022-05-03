package com.smart.shop.backend.util.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Felix Aballi <felixaballi@gmail.com>
 */
@Data
@Builder
@AllArgsConstructor
public class ApiOperation {

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

   @Builder.Default
   private HttpStatus statusCode = HttpStatus.OK;

   private Object message;

   public <T> T getMessage(Class<T> clazz) {
      return clazz.cast(message);
   }
}
