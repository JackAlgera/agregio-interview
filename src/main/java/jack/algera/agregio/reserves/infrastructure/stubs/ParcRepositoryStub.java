package jack.algera.agregio.reserves.infrastructure.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jack.algera.agregio.reserves.domain.models.EnergyType;
import jack.algera.agregio.reserves.domain.models.Parc;
import jack.algera.agregio.reserves.domain.ports.ParcRepository;

public class ParcRepositoryStub implements ParcRepository {

  public static final Parc PARC_1 = Parc.builder()
      .id(UUID.randomUUID())
      .energyType(EnergyType.HYDRAULIC)
      .build();
  public static final Parc PARC_2 = Parc.builder()
      .id(UUID.randomUUID())
      .energyType(EnergyType.SOLAR)
      .build();

  private final List<Parc> localDb = new ArrayList<>(List.of(PARC_1, PARC_2));

  @Override
  public void save(Parc parc) {
    localDb.add(parc);
  }
}
