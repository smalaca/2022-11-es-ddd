package com.smalaca.command.application.offer;

import com.google.common.collect.ImmutableMap;
import com.smalaca.command.domain.cart.NotEnoughProductsFoundException;
import com.smalaca.command.domain.offer.Offer;
import com.smalaca.command.domain.offer.OfferRepository;
import com.smalaca.command.domain.productcatalogue.ProductCatalogue;
import com.smalaca.command.domain.productcatalogue.ProductsCatalogueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OfferCommandsTest {
    private static final UUID PRODUCT_ID_ONE = UUID.randomUUID();

    private final ProductsCatalogueRepository productsCatalogueRepository = mock(ProductsCatalogueRepository.class);
    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final OfferCommands offerCommands = new OfferCommands(productsCatalogueRepository);

    @Test
    void shouldRecognizeNotEnoughProductsAvailable() {
        givenAmountOfProduct(PRODUCT_ID_ONE, 12);
        Map<UUID, Integer> products = ImmutableMap.of(PRODUCT_ID_ONE, 13);

        Executable actual = () -> offerCommands.buyProducts(products);

        assertThrows(NotEnoughProductsFoundException.class, actual);
        thenOfferNotCreated();
    }

    private void thenOfferNotCreated() {
        BDDMockito.then(offerRepository).should(Mockito.never()).save(ArgumentMatchers.any());
    }

    @Test
    void shouldCreateAnOffer() {
        givenAmountOfProduct(PRODUCT_ID_ONE, 14);
        Map<UUID, Integer> products = ImmutableMap.of(PRODUCT_ID_ONE, 13);

        offerCommands.buyProducts(products);

        thenOfferCreated();
    }

    private void thenOfferCreated() {
        BDDMockito.then(offerRepository).should().save(ArgumentMatchers.any(Offer.class));
    }

    private void givenAmountOfProduct(UUID productId, int amount) {
        ProductCatalogue productCatalogue = new ProductCatalogue();
        productCatalogue.add(productId, amount);
        given(productsCatalogueRepository.get()).willReturn(productCatalogue);
    }
}