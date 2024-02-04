package jack.algera.agregio.reserves.domain.mappers;

import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
import jack.algera.agregio.reserves.domain.models.OfferBlock;
import jack.algera.agregio.reserves.domain.models.ParcAllocation;

public class OfferBlockMapper {

  public static OfferBlock fromDto(OfferBlockDto dto) {
    double quantity = dto.parkAllocations().stream()
        .mapToDouble(ParcAllocation::quantity)
        .sum();

    return OfferBlock.builder()
        .start(dto.start())
        .end(dto.end())
        .maxSellPrice(dto.maxSellPrice())
        .quantity(quantity)
        .parkAllocations(dto.parkAllocations())
        .build();
  }
}
