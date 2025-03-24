package com.ramzoo.price_service.controller;

import com.ramzoo.price_service.entity.Price;
import com.ramzoo.price_service.service.PricingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prices")
public class PricingController {

    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Price> getPrice(@PathVariable Long productId) {
        Price price = pricingService.getPriceByProductId(productId);
        return ResponseEntity.ok(price);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Price> updatePrice(@PathVariable Long productId,
                                             @RequestParam Double price,
                                             @RequestParam String currency) {
        Price updatedPrice = pricingService.updatePrice(productId, price, currency);
        return ResponseEntity.ok(updatedPrice);
    }
}
