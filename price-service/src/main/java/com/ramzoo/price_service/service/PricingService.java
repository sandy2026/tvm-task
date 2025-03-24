package com.ramzoo.price_service.service;

import com.ramzoo.price_service.entity.Price;
import com.ramzoo.price_service.repository.PriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PricingService {

    private final PriceRepository priceRepository;

    public PricingService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPriceByProductId(Long productId) {
        return priceRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Price not found for productId: " + productId));
    }

    @Transactional
    public Price updatePrice(Long productId, Double newPrice, String currency) {
        Price price = priceRepository.findByProductId(productId)
                .orElse(new Price(null, productId, newPrice, currency)); // Ensure productId is set

        price.setPrice(newPrice);
        price.setCurrency(currency);
        return priceRepository.save(price);
    }
}
