package amorallife.mapper;

import amorallife.dto.OrderDto;
import amorallife.entity.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto orderToDto(Order order) {
        return new OrderDto()
                .setOrderId(order.getId())
                .setGeneralPrice(order.getGeneralPrice())
                .setUserName(order.getUser().getUsername())
                .setProducts(order.getProducts().stream()
                        .map(ProductMapper::productToDto)
                        .collect(Collectors.toList()));
    }
}
