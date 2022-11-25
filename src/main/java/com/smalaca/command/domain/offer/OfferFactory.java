package com.smalaca.command.domain.offer;

import com.smalaca.command.domain.productcatalogue.NotEnoughProductsFoundException;
import com.smalaca.command.domain.productcatalogue.ProductCatalogue;
import com.smalaca.command.domain.productcatalogue.ProductsCatalogueRepository;

import java.util.Map;
import java.util.UUID;

public class OfferFactory {
    private final ProductsCatalogueRepository productsCatalogueRepository;

    public OfferFactory(ProductsCatalogueRepository productsCatalogueRepository) {
        this.productsCatalogueRepository = productsCatalogueRepository;
    }

    public Offer create(Map<UUID, Integer> products) {
        if (notEnoughAmountFor(products)) {
            throw new NotEnoughProductsFoundException();
        }

        return new Offer(products);
    }

    private boolean notEnoughAmountFor(Map<UUID, Integer> products) {
        ProductCatalogue productCatalogue = productsCatalogueRepository.get();

        return products.entrySet().stream()
                .anyMatch(entry -> productCatalogue.hasEnough(entry.getKey(), entry.getValue()));
    }
}
