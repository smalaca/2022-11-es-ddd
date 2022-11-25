package com.smalaca.command.domain.productcatalogue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductCatalogue {
    private final Map<UUID, Integer> products = new HashMap<>();

    public void add(UUID productId, int amount) {
        products.put(productId, amount);
    }

    public boolean hasEnough(UUID productId, Integer amount) {
        return products.get(productId) < amount;
    }
}
