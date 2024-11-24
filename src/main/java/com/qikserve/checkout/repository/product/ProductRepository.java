package com.qikserve.checkout.repository.product;

import com.qikserve.checkout.model.entities.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findAll();
    Product findById(String productId);
}