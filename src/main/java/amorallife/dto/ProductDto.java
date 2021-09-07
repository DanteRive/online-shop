package amorallife.dto;

import amorallife.entity.ProductType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private ProductType type;
}
