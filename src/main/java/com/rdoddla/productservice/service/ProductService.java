package com.rdoddla.productservice.service;

import com.rdoddla.productservice.entiry.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product findProductById(Long id);
    List<Product> findAllProducts();
    void deleteProduct(Long id);
    Product patchProduct(Long id, Product product);
    Product updateProduct(Long id, Product product);
}
