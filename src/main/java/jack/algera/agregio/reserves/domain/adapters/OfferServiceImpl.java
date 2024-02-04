package jack.algera.agregio.reserves.domain.adapters;

import java.util.List;
import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
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
    return null;
  }

  @Override
  public List<Offer> getOffersForMarket(Market market) {
    return null;
  }
}
