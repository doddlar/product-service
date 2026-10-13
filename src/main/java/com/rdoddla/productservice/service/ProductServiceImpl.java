package com.rdoddla.productservice.service;

import com.rdoddla.productservice.entiry.Product;
import com.rdoddla.productservice.exception.ProductNotFoundException;
import com.rdoddla.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

  /*  public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }*/

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found with ID: " + id));

    }

    @Override
    @Transactional
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
/*        boolean exists = productRepository.existsById(id);
        if (!exists) {throw new RuntimeException("Product not found");}
        productRepository.deleteById(id);*/
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with Id: %s".formatted(id)));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public Product patchProduct(Long id, Product product) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with Id: %s".formatted(id)));
        if(product.getName() != null && !product.getName().isBlank()){oldProduct.setName(product.getName());}
        if(product.getDescription() !=null && !product.getDescription().isBlank()){oldProduct.setDescription(product.getDescription());}
        if(product.getPrice() != null){oldProduct.setPrice(product.getPrice());}
        if(product.getStock() != null ){oldProduct.setStock(product.getStock());}
        return productRepository.save(oldProduct);
    }

    @Override
    @Transactional()
    public Product updateProduct(Long id, Product product) {
        Product existedProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with Id: %s".formatted(id)));
        existedProduct.setName(product.getName());
        existedProduct.setPrice(product.getPrice());
        existedProduct.setStock(product.getStock());
        existedProduct.setDescription(product.getDescription());
        return productRepository.save(existedProduct);
    }
}
