package amorallife.controller;
import amorallife.dto.ProductDto;
import amorallife.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import amorallife.dto.UserDto;
import amorallife.entity.Product;
import amorallife.entity.User;
import amorallife.service.ProductService;
import amorallife.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@CrossOrigin
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public AdminController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserMapper.userToDto(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "products")
    private List<ProductDto> findAll(){
        return productService.findAll();
    }

    @GetMapping(value = "products/{id}")
    private Product getOne(@PathVariable("id") Product product){
        return productService.getOne(product);
    }

    @PostMapping(value = "products")
    private Product create(
            @RequestBody Product product){
        return productService.create(product);
    }

    @PutMapping("products/{id}")
    private Product update(
            @PathVariable("id") Product productFromDB,
            @RequestBody Product product){
        return productService.update(productFromDB, product);
    }

    @DeleteMapping("products/{id}")
    private void delete(@PathVariable("id") Product product){
        productService.delete(product);
    }
}