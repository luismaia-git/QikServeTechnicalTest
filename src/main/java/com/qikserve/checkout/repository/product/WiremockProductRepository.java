package com.qikserve.checkout.repository.product;

import com.qikserve.checkout.model.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class WiremockProductRepository implements ProductRepository {

    private final String wiremockBaseUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public WiremockProductRepository(RestTemplate restTemplate, @Value("${wiremock.base.url}") String wiremockBaseUrl) {
        this.restTemplate = restTemplate;
        this.wiremockBaseUrl = wiremockBaseUrl;
    }

    @Override
    public Product findById(String productId) {
        String url = wiremockBaseUrl + "/products/" + productId;
        try {
            ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
            return response.getBody();
        } catch (ResourceAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> findAll() {
        String url = wiremockBaseUrl + "/products";
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        return Arrays.asList(response.getBody());
    }
}
