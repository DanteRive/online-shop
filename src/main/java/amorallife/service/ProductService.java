package amorallife.service;

import amorallife.dto.ProductDto;
import amorallife.entity.ProductType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductDto getProduct(Long id);

    ProductDto saveProduct(ProductDto dto);

    List<ProductDto> findAll(Pageable pageable);

    List<ProductDto> findByType(ProductType productType, Pageable pageable);

    List<ProductDto> findByLikeName(String name, Pageable pageable);

    void delete(Long id);
}
