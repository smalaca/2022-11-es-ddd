package com.smalaca.command.domain.offer;

import java.util.Map;
import java.util.UUID;

public class Offer {
    private final Map<UUID, Integer> products;

    Offer(Map<UUID, Integer> products) {
        this.products = products;
    }
}
