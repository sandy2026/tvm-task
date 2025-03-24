package com.ramzoo.product_server.controller;

import com.ramzoo.product_server.entity.Product;
import com.ramzoo.product_server.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
	    this.productRepository = productRepository;
	}


    // Get products by category with inventory > 0
    @Operation(summary = "Get all products by category and inventory")
    @ApiResponse(responseCode = "200", description = "Fetched products")
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productRepository.findByCategoryAndInventoryGreaterThan(category, 0);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products available in this category");
        }
        return ResponseEntity.ok(products);
    }

    // Create a new product
    @Operation(summary = "Add a new product")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }


    // Update product details
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    product.setId(id); // Ensure the correct ID is set
                    Product updatedProduct = productRepository.save(product);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Return 404 with no body
    }


    
    // Delete a product
    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with ID: " + id);
        }
    }



}