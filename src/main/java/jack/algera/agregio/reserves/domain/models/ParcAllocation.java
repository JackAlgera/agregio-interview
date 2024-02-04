package jack.algera.agregio.reserves.domain.models;

import lombok.Builder;

@Builder
public record ParcAllocation(Parc parc, double quantity) {
}
