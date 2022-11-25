package com.smalaca.command.application.offer;

import com.smalaca.command.domain.offer.Offer;
import com.smalaca.command.domain.offer.OfferFactory;
import com.smalaca.command.domain.offer.OfferRepository;
import com.smalaca.command.domain.productcatalogue.ProductsCatalogueRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

public class OfferCommands {
    private final OfferRepository offerRepository;
    private final ProductsCatalogueRepository productsCatalogueRepository;
    private final OfferFactory offerFactory;

    public OfferCommands(OfferRepository offerRepository, ProductsCatalogueRepository productsCatalogueRepository) {
        this.offerRepository = offerRepository;
        this.productsCatalogueRepository = productsCatalogueRepository;
        offerFactory = new OfferFactory(this.productsCatalogueRepository);
    }

    @Transactional
    void buyProducts(Map<UUID, Integer> products) {
        Offer offer = offerFactory.create(products);

        offerRepository.save(offer);
    }
}
