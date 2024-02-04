package jack.algera.agregio.reserves.domain.ports;

import java.util.List;
import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;

public interface OfferService {

  Offer createOffer(Market market, List<OfferBlockDto> offerBlocks);

  List<Offer> getOffersForMarket(Market market);
}
