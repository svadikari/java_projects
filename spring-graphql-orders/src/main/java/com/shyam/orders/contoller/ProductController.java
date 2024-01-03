package com.shyam.orders.contoller;

import com.shyam.orders.entity.Product;
import com.shyam.orders.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class ProductController {

    private ProductRepository productRepository;

    @QueryMapping
    public Collection<Product> products() {
        return productRepository.findAll();
    }
}
