package com.smalaca.command.application.cart;

import com.google.common.collect.ImmutableMap;
import com.smalaca.command.domain.cart.NotEnoughProductsFoundException;
import com.smalaca.command.domain.offer.OfferRepository;
import com.smalaca.command.domain.productcatalogue.ProductCatalogue;
import com.smalaca.command.domain.productcatalogue.ProductsCatalogueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class CartCommandsTest {
    private static final UUID PRODUCT_ID_ONE = UUID.randomUUID();

    private final ProductsCatalogueRepository productsCatalogueRepository = mock(ProductsCatalogueRepository.class);
    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final CartCommands cartCommands = new CartCommands(productsCatalogueRepository);

    @Test
    void shouldRecognizeNotEnoughProductsAvailable() {
        givenAmountOfProduct(PRODUCT_ID_ONE, 12);
        Map<UUID, Integer> products = ImmutableMap.of(PRODUCT_ID_ONE, 13);

        Executable actual = () -> cartCommands.buyProducts(products);

        assertThrows(NotEnoughProductsFoundException.class, actual);
        thenOfferNotCreated();
    }

    private void givenAmountOfProduct(UUID productId, int amount) {
        ProductCatalogue productCatalogue = new ProductCatalogue();
        productCatalogue.add(productId, amount);
        given(productsCatalogueRepository.get()).willReturn(productCatalogue);
    }

    private void thenOfferNotCreated() {
        then(offerRepository).should(never()).save(any());
    }
}