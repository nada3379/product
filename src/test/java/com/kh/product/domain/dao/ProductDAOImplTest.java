package com.kh.product.domain.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {
  @Autowired  //SC(스프링컨테이너)에서 동일타입의 객체를 주입받는다.
  private ProductDAO productDAO;

  @Test
  @DisplayName("등록")
  void save() {
    Product product = new Product();
    product.setPname("노트북");
    product.setQuantity(3L);
    product.setPrice(2000000L);
    Long productId =  productDAO.save(product);
    Product findedProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(findedProduct.getPname()).isEqualTo("노트북");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(3L);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(2000000L);
  }

  @Test
  @DisplayName("목록")
  void findAll() {
    List<Product> products = productDAO.findAll();
    Assertions.assertThat(products.size()).isEqualTo(4);
  }

  @Test
  @DisplayName("조회")
  void findByProductId() {
    Long productId = 2L;
    Product findedProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(findedProduct.getProductId())
              .isEqualTo(productId);
  }

  @Test
  @DisplayName("변경")
  void update() {
    Long productId = 3L;
    Product findedProduct =  productDAO.findByProductId(productId);
    findedProduct.setPname("삼성노트북");
    findedProduct.setQuantity(100L);
    findedProduct.setPrice(2500000L);
    productDAO.update(productId,findedProduct);
    Product updatedProduct =  productDAO.findByProductId(productId);
    Assertions.assertThat(updatedProduct.getPname()).isEqualTo("삼성노트북");
    Assertions.assertThat(updatedProduct.getQuantity()).isEqualTo(100L);
    Assertions.assertThat(updatedProduct.getPrice()).isEqualTo(2500000L);


  }
  @Test
  @DisplayName("삭제")
  void deleteByProductId() {
    Long productId = 5L;
   productDAO.deleteByProductId(productId);
    Assertions.catchThrowableOfType(
        () -> productDAO.findByProductId(productId),
    EmptyResultDataAccessException.class);
  }
}