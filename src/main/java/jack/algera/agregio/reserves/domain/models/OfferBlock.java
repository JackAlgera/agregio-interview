package jack.algera.agregio.reserves.domain.models;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record OfferBlock(OffsetDateTime start, OffsetDateTime end,
    double maxSellPrice, double quantity, List<ParcAllocation> parkAllocations) {
}
