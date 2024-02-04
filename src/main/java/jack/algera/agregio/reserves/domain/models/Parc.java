package jack.algera.agregio.reserves.domain.models;

import java.util.UUID;
import lombok.Builder;

@Builder
public record Parc(UUID id, EnergyType energyType) {
}
