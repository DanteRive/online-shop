package amorallife.service;

import amorallife.dto.OrderDto;

public interface OrderService {

    OrderDto save(OrderDto dto);

    OrderDto getOrder(Long id);

    OrderDto getOrderBycurrentUser();

}
