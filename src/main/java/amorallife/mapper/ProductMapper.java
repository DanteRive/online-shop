package amorallife.mapper;

import amorallife.dto.ProductDto;
import amorallife.entity.Product;

public class ProductMapper {

    public static ProductDto productToDto(Product product) {
        return new ProductDto()
                .setId(product.getId())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setType(product.getType());
    }
}
