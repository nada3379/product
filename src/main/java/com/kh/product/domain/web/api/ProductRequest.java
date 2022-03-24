package com.kh.product.domain.web.api;

import lombok.Data;

@Data
public class ProductRequest {
  private String pname;     //상품명
  private Long quantity;  //상품수량
  private Long price;       //단가
}
