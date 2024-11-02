package com.eCommerce.microservice.product.service;

import com.eCommerce.microservice.product.dto.ProductRequest;
import com.eCommerce.microservice.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public ProductResponse createProduct(ProductRequest productRequest);

    public List<ProductResponse> getAllProducts();
}
