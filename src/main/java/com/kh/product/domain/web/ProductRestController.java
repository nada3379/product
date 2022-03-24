package com.kh.product.domain.web;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.domain.web.api.ApiResult;
import com.kh.product.domain.web.api.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductRestController {

  private final ProductSVC productSVC;

  //등록
  @PostMapping
  public ApiResult<Product> save(@RequestBody ProductRequest productRequest){
    Product product = new Product();
    BeanUtils.copyProperties(productRequest, product);
    Long productId = productSVC.save(product);

    Product findeProduct = null;
    if(productId > 0) {
      findeProduct = productSVC.findByProductId(productId);
      BeanUtils.copyProperties(findeProduct,product);
    }

    ApiResult<Product> result = new ApiResult<>("00","success",product);

    return result;
  }

  //목록
  @GetMapping
  public ApiResult<List<Product>> findAll(){

    List<Product> products = productSVC.findAll();

    ApiResult<List<Product>> result = null;
    if(products.size() != 0) {
      result = new ApiResult<>("00","success", products);
    }else {
      result = new ApiResult<>("99","데이터없음", products);
    }

    return result;
  }

  //조회
  @GetMapping("/{id}")
  public ApiResult<Product> findByProductId(@PathVariable("id") Long productId) {
    Product findedProduct = productSVC.findByProductId(productId);
    ApiResult<Product> result = new ApiResult<>("00", "success",findedProduct);

    return result;
  }

  //수정
  @PatchMapping("/{id}")
  public ApiResult<Object> update(
      @PathVariable("id") Long productId,
      @RequestBody ProductRequest productRequest ) {

    Product product = new Product();
    BeanUtils.copyProperties(productRequest, product);
    int affctedRow = productSVC.update(productId, product);

    ApiResult<Object> result = null;
    if(affctedRow == 1) {
      Product  updatedProduct = productSVC.findByProductId(productId);
        result = new ApiResult<>("00","success",updatedProduct);

    } else  {
      result = new ApiResult<>("00", "fail", "상품 아이디가 없습니다.");
    }
    return result;
  }

  //삭제
  @DeleteMapping("{id}")
  public  ApiResult<Boolean> delete(@PathVariable("id") Long productId) {
    int affedteRow = productSVC.deleteByProductId(productId);

    ApiResult<Boolean> result = null;
    if(affedteRow != 0) {

      result = new ApiResult<>("00","success", true);
    }else {
      result = new ApiResult<>("99","fail",false);
    }

    return result;

  }

}
