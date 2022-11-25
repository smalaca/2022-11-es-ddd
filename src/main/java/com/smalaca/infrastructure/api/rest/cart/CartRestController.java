package com.smalaca.infrastructure.api.rest.cart;

import com.smalaca.query.cart.CartQueries;

public class CartRestController {
    private final CartQueries queries;

    public CartRestController(CartQueries queries) {
        this.queries = queries;
    }
}
