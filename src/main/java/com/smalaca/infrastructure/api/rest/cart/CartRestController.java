package com.smalaca.infrastructure.api.rest.cart;

import com.smalaca.command.application.cart.CartCommands;
import com.smalaca.query.cart.CartQueries;

public class CartRestController {
    private final CartQueries queries;
    private final CartCommands commands;

    public CartRestController(CartQueries queries, CartCommands commands) {
        this.queries = queries;
        this.commands = commands;
    }
}
