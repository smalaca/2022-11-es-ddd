package com.smalaca.command.application.cart;

import com.smalaca.command.domain.cart.NotEnoughProductsFoundException;
import com.smalaca.command.domain.productcatalogue.ProductsCatalogueRepository;

import java.util.Map;
import java.util.UUID;

public class CartCommands {
    private final ProductsCatalogueRepository productsCatalogueRepository;

    public CartCommands(ProductsCatalogueRepository productsCatalogueRepository) {
        this.productsCatalogueRepository = productsCatalogueRepository;
    }

    void buyProducts(Map<UUID, Integer> products) {
        throw new NotEnoughProductsFoundException();
    }
}
