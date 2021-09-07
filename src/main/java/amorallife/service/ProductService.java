package amorallife.service;

import amorallife.dto.ProductDto;
import amorallife.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto getProduct(Long id);

    ProductDto saveProduct(ProductDto dto);

    Product getOne(Product product);

    List<ProductDto> findAll();

    Product create(Product product);

    Product update(Product productFromDB, Product product);

    void delete(Product product);
}
