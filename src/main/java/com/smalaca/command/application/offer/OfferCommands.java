package com.smalaca.command.application.offer;

import com.smalaca.command.domain.cart.NotEnoughProductsFoundException;
import com.smalaca.command.domain.productcatalogue.ProductsCatalogueRepository;

import java.util.Map;
import java.util.UUID;

public class OfferCommands {
    private final ProductsCatalogueRepository productsCatalogueRepository;

    public OfferCommands(ProductsCatalogueRepository productsCatalogueRepository) {
        this.productsCatalogueRepository = productsCatalogueRepository;
    }

    void buyProducts(Map<UUID, Integer> products) {
        throw new NotEnoughProductsFoundException();
    }
}
