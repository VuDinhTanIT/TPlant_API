
package com.vku.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vku.models.Product;
import com.vku.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAllCategories() {
		List<Product> products = productService.getAllCategories();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		Optional<Product> product = productService.getProductById(productId);
		return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/top10")
	public List<Product> getTop10Products() {
		return productService.getTopProducts(10);
	}

	@GetMapping("/top5")
	public List<Product> getTop5Products() {
		return productService.getTopProducts(5);
	}

	@GetMapping("/category/{categoryId}")
	public List<Product> getProductsByCategory(@PathVariable int categoryId) {
		return productService.getProductsByCategory(categoryId);
	}

	@GetMapping("/similar/{categoryId}")
	public List<Product> getSimilarProducts(@PathVariable int categoryId) {
		return productService.getSimilarProducts(categoryId, 5);
	}

}