package jack.algera.agregio.reserves.domain.ports;

import jack.algera.agregio.reserves.domain.models.Parc;

public interface ParcRepository {

  void save(Parc parc);
}
