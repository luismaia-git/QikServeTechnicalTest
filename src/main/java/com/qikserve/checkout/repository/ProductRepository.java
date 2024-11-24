package com.qikserve.checkout.repository;

import com.qikserve.checkout.entities.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findAll();
    Product findById(String productId);
}