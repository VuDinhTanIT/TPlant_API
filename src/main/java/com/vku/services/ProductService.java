package com.vku.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vku.models.Product;
import com.vku.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllCategories() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
    
    public List<Product> getTopProducts(int count) {
        PageRequest pageable = PageRequest.of(0, count);
        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> getSimilarProducts(int categoryId, int count) {
        PageRequest pageable = PageRequest.of(0, count);
        return productRepository.findByCategoryCategoryId(categoryId, pageable);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }
}