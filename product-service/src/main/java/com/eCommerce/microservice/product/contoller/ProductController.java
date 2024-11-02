package com.eCommerce.microservice.product.contoller;

import com.eCommerce.microservice.product.service.ProductService;
import com.eCommerce.microservice.product.dto.ProductRequest;
import com.eCommerce.microservice.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createProduct(@RequestBody ProductRequest productRequest) {
//        productService.createProduct(productRequest);
//
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse createdProduct = productService.createProduct(productRequest); // Ensure this returns a ProductResponse
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

}
