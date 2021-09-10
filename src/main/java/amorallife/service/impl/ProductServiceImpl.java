package amorallife.service.impl;

import amorallife.dto.ProductDto;
import amorallife.entity.Product;
import amorallife.entity.ProductType;
import amorallife.mapper.ProductMapper;
import amorallife.repository.ProductRepository;
import amorallife.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByPrice(pageable).stream()
                .map(ProductMapper::productToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findByType(ProductType productType, Pageable pageable) {
        return productRepository.findAllByProductType(productType, pageable).stream()
                .map(ProductMapper::productToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findByLikeName(String name, Pageable pageable) {
        return productRepository.findAllByNameLike(name, pageable).stream()
                .map(ProductMapper::productToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return ProductMapper.productToDto(productOptional.get());
        }
        throw new RuntimeException("Exp");
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto dto) {
        Product product = new Product();
        if (dto.getId() != null) {
            product = productRepository.getOne(dto.getId());
        }
        fillProduct(product, dto);
        return ProductMapper.productToDto(productRepository.saveAndFlush(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.delete(productRepository.getOne(id));
    }

    private void fillProduct(Product product, ProductDto dto) {
        product.setPrice(dto.getPrice());
        product.setName(dto.getName());
        product.setType(dto.getType());
    }
}
