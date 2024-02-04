package jack.algera.agregio.reserves.domain.adapters;

import java.util.Set;
import jack.algera.agregio.reserves.domain.models.EnergyType;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Parc;
import jack.algera.agregio.reserves.domain.ports.OfferRepository;
import jack.algera.agregio.reserves.domain.ports.ParcRepository;
import jack.algera.agregio.reserves.domain.ports.ParcService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParcServiceImpl implements ParcService {

  private final ParcRepository parcRepository;
  private final OfferRepository offerRepository;

  @Override
  public Parc createParc(EnergyType energyType) {
    return null;
  }

  @Override
  public Set<Parc> findAllByMarket(Market market) {
    return null;
  }
}
