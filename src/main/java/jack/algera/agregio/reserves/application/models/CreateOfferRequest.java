package jack.algera.agregio.reserves.application.models;

import java.util.List;
import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
import lombok.Builder;

@Builder
public record CreateOfferRequest(String market, List<OfferBlockDto> offerBlocks) {
}
