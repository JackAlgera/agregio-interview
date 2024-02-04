package jack.algera.agregio.reserves.domain.daos;

import java.time.OffsetDateTime;
import java.util.List;
import jack.algera.agregio.reserves.domain.models.ParcAllocation;
import lombok.Builder;

@Builder
public record OfferBlockDto(OffsetDateTime start, OffsetDateTime end,
    double maxSellPrice, List<ParcAllocation> parkAllocations) {
}
