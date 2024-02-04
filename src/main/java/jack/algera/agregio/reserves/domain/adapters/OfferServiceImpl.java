package jack.algera.agregio.reserves.domain.adapters;

import java.util.List;
import java.util.UUID;
import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
import jack.algera.agregio.reserves.domain.mappers.OfferBlockMapper;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;
import jack.algera.agregio.reserves.domain.ports.OfferRepository;
import jack.algera.agregio.reserves.domain.ports.OfferService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  @Override
  public Offer createOffer(Market market, List<OfferBlockDto> offerBlocks) {
    Offer offer = Offer.builder()
        .id(UUID.randomUUID())
        .market(market)
        .offerBlocks(offerBlocks.stream()
            .map(OfferBlockMapper::fromDto)
            .toList())
        .build();
    offerRepository.save(offer);
    return offer;
  }

  @Override
  public List<Offer> getOffersForMarket(Market market) {
    return offerRepository.findAllByMarket(market);
  }
}
