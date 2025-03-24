package com.ramzoo.inventory_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramzoo.inventory_service.entity.Inventory;
import com.ramzoo.inventory_service.repository.Inventoryrepository;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	Inventoryrepository inventoryRepository;
	
	// Get inventory for a product
    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    // Add or update inventory for a product
    @PostMapping("/")
    public Inventory updateInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
