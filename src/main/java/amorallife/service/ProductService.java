package amorallife.service;

import amorallife.dto.ProductDto;
import amorallife.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto getProduct(Long id);

    ProductDto saveProduct(ProductDto dto);

    List<ProductDto> findAll();

    void delete(Product product);

    void fillProduct(Product product, ProductDto dto);
}
