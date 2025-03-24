package com.ramzoo.product_server.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor  // Generates constructor with all fields, including id
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message = "Category is mandatory")
    @Column(name = "category", nullable = false)
    private String category;

    @NotNull(message = "Price is mandatory")  // Changed from @NotBlank to @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull(message = "Inventory is mandatory")  // Changed from @NotBlank to @NotNull
    @Min(value = 0, message = "Inventory must be a non-negative number")
    @Column(name = "inventory", nullable = false)
    private Integer inventory;

    //  Constructor used for JSON deserialization
    @JsonCreator
    public Product(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("category") String category,
        @JsonProperty("inventory") Integer inventory,
        @JsonProperty("price") Double price
    ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.inventory = inventory;
        this.price = price;
    }
    public Product() {} //default constructor
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {this.id=id;}
}
