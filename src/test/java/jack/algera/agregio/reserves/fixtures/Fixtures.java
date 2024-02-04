package jack.algera.agregio.reserves.fixtures;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
import jack.algera.agregio.reserves.domain.models.EnergyType;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;
import jack.algera.agregio.reserves.domain.models.OfferBlock;
import jack.algera.agregio.reserves.domain.models.Parc;
import jack.algera.agregio.reserves.domain.models.ParcAllocation;

public class Fixtures {

  public static Parc PARC_1 = Parc.builder()
      .id(UUID.randomUUID())
      .energyType(EnergyType.WIND)
      .build();
  public static Parc PARC_2 = Parc.builder()
      .id(UUID.randomUUID())
      .energyType(EnergyType.SOLAR)
      .build();
  public static Parc PARC_3 = Parc.builder()
      .id(UUID.randomUUID())
      .energyType(EnergyType.HYDRAULIC)
      .build();

  public static ParcAllocation PARC_ALLOCATION_1 = ParcAllocation.builder()
      .parc(PARC_1)
      .quantity(100)
      .build();
  public static ParcAllocation PARC_ALLOCATION_2 = ParcAllocation.builder()
      .parc(PARC_2)
      .quantity(200)
      .build();
  public static ParcAllocation PARC_ALLOCATION_3 = ParcAllocation.builder()
      .parc(PARC_3)
      .quantity(150)
      .build();

  public static OfferBlockDto OFFER_BLOCK_DTO_1 = OfferBlockDto.builder()
      .start(OffsetDateTime.now())
      .end(OffsetDateTime.now().plusHours(1))
      .maxSellPrice(100)
      .parkAllocations(List.of(PARC_ALLOCATION_1))
      .build();

  public static OfferBlock OFFER_BLOCK_1 = OfferBlock.builder()
      .start(OffsetDateTime.now())
      .end(OffsetDateTime.now().plusHours(1))
      .maxSellPrice(100)
      .quantity(100)
      .parkAllocations(List.of(PARC_ALLOCATION_1))
      .build();
  public static OfferBlock OFFER_BLOCK_2 = OfferBlock.builder()
      .start(OffsetDateTime.now())
      .end(OffsetDateTime.now().plusHours(2))
      .maxSellPrice(200)
      .quantity(300)
      .parkAllocations(List.of(PARC_ALLOCATION_1, PARC_ALLOCATION_2))
      .build();
  public static OfferBlock OFFER_BLOCK_3 = OfferBlock.builder()
      .start(OffsetDateTime.now())
      .end(OffsetDateTime.now().plusHours(3))
      .maxSellPrice(300)
      .quantity(150)
      .parkAllocations(List.of(PARC_ALLOCATION_3))
      .build();

  public static Offer createOffer(OfferBlock... offerBlocks) {
    return Offer.builder()
        .id(UUID.randomUUID())
        .market(Market.RESERVE_RAPIDE)
        .offerBlocks(Arrays.asList(offerBlocks))
        .build();
  }
}
