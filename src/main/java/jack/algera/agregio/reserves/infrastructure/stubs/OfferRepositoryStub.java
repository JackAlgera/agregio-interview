package jack.algera.agregio.reserves.infrastructure.stubs;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;
import jack.algera.agregio.reserves.domain.models.OfferBlock;
import jack.algera.agregio.reserves.domain.models.ParcAllocation;
import jack.algera.agregio.reserves.domain.ports.OfferRepository;

public class OfferRepositoryStub implements OfferRepository {

  public static final UUID OFFER_ID_1 = UUID.randomUUID();

  private final List<Offer> localDb = new ArrayList<>(List.of(
      Offer.builder()
          .id(OFFER_ID_1)
          .market(Market.RESERVE_PRIMAIRE)
          .offerBlocks(List.of(OfferBlock.builder()
              .start(OffsetDateTime.now())
              .end(OffsetDateTime.now().plusHours(1))
              .maxSellPrice(100)
              .quantity(100)
              .parkAllocations(List.of(ParcAllocation.builder()
                  .parc(ParcRepositoryStub.PARC_1)
                  .quantity(100)
                  .build()))
              .build()))
          .build()));

  @Override
  public void save(Offer offer) {
    localDb.add(offer);
  }

  @Override
  public List<Offer> findAllByMarket(Market market) {
    return localDb.stream()
        .filter(offer -> offer.market().equals(market))
        .toList();
  }
}
