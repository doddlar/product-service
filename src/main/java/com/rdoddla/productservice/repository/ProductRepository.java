package com.rdoddla.productservice.repository;

import com.rdoddla.productservice.entiry.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
