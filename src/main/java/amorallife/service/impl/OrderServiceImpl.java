package amorallife.service.impl;

import amorallife.dto.OrderDto;
import amorallife.dto.ProductDto;
import amorallife.entity.Order;
import amorallife.entity.OrderToProduct;
import amorallife.entity.Product;
import amorallife.entity.User;
import amorallife.mapper.OrderMapper;
import amorallife.repository.OrderRepository;
import amorallife.repository.OrderToProductRepository;
import amorallife.repository.ProductRepository;
import amorallife.repository.UserRepository;
import amorallife.security.jwt.JwtTokenProvider;
import amorallife.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderToProductRepository orderToProductRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrder(Long id) {
        return OrderMapper.orderToDto(orderRepository.getOne(id));
    }

    @Override
    public OrderDto getOrderBycurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return OrderMapper.orderToDto(orderRepository.findByUserId(user.getId()));
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto dto) {
        Order order = new Order();
        fillOrderEntity(order, dto);
        orderRepository.saveAndFlush(order);

        List<OrderToProduct> orderToProducts = new ArrayList<>();

        List<Product> products = productRepository.findAllByIdIn(dto.getProducts().stream()
                .map(ProductDto::getId)
                .collect(Collectors.toList()));
        for (Product product : products) {
            orderToProducts.add(saveOrderToProduct(order, product));
        }
        orderToProductRepository.saveAll(orderToProducts);
        entityManager.flush();
        entityManager.persist(order);

        return OrderMapper.orderToDto(order);
    }

    private OrderToProduct saveOrderToProduct(Order order, Product product) {
        OrderToProduct orderToProduct = new OrderToProduct();
        orderToProduct.setOrder(order);
        orderToProduct.setProduct(product);
        return orderToProduct;
    }

    private void fillOrderEntity(Order order, OrderDto dto) {
        Double generalPrice = 0.0;
        for (ProductDto productDto : dto.getProducts()) {
            generalPrice += productDto.getPrice();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        order.setUser(user);
//        order.setUser(dto.getUserName() != null ? userRepository.findByUsername(dto.getUserName()) : null);
        order.setGeneralPrice(generalPrice);
    }

}
