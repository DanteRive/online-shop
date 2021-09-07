package amorallife.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import amorallife.dto.OrderDto;
import amorallife.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private OrderServiceImpl orderService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getOder(id);
    }

    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public OrderDto save(@RequestBody OrderDto dto) {
        return orderService.save(dto);
    }
}
