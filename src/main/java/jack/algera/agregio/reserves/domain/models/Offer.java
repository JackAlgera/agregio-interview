package jack.algera.agregio.reserves.domain.models;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record Offer(UUID id, Market market, List<OfferBlock> offerBlocks) {
}
