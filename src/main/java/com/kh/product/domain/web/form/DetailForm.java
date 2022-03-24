package com.kh.product.domain.web.form;

import lombok.Data;

@Data
public class DetailForm {
  private Long productId;   //식별자
  private String pname;     //상품명
  private Long quantity;  //상품수량
  private Long price;       //단가
}

