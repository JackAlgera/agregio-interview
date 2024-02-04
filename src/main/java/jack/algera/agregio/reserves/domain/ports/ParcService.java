package jack.algera.agregio.reserves.domain.ports;

import java.util.Set;
import jack.algera.agregio.reserves.domain.models.EnergyType;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Parc;

public interface ParcService {

  Parc createParc(EnergyType energyType);

  Set<Parc> findAllByMarket(Market market);
}
