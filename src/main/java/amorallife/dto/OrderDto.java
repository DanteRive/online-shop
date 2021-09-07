package amorallife.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class OrderDto {

    private Long orderId;
    private Double generalPrice;
    private String userName;
    private List<ProductDto> products;
}
