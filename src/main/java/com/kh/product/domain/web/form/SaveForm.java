package com.kh.product.domain.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class SaveForm {
  @NotBlank(message = "상품명 입력은 필수입니다.")
  private String pname;     //상품명
  @NotNull(message = "상품수량 입력은 필수입니다")
  @PositiveOrZero(message = "상품수량 입력은 0이상 이어야 합니다.")
  private Long quantity;  //상품수량
  @NotNull(message = "상품가격 입력은 필수입니다")
  @PositiveOrZero(message = "상품가격 입력은 0이상 이어야 합니다.")
  private Long price;       //단가
}
