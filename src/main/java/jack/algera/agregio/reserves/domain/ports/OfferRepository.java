package jack.algera.agregio.reserves.domain.ports;

import java.util.List;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;

public interface OfferRepository {

  void save(Offer offer);

  List<Offer> findAllByMarket(Market market);
}
