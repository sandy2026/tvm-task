package com.ramzoo.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramzoo.inventory_service.entity.Inventory;

@Repository
public interface Inventoryrepository extends JpaRepository<Inventory, Long> {
	
	Inventory findByProductId(Long productId);

}
