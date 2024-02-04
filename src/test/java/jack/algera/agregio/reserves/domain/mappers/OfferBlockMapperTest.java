package jack.algera.agregio.reserves.domain.mappers;

import java.util.List;
import java.util.stream.Stream;
import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
import jack.algera.agregio.reserves.domain.models.OfferBlock;
import jack.algera.agregio.reserves.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OfferBlockMapperTest {

  static Stream<Arguments> testFromDto() {
    return Stream.of(
        // Parc allocations are correctly summed
        Arguments.of(
            OfferBlockDto.builder()
                .start(Fixtures.OFFER_BLOCK_1.start())
                .end(Fixtures.OFFER_BLOCK_1.end())
                .maxSellPrice(Fixtures.OFFER_BLOCK_1.maxSellPrice())
                .parkAllocations(List.of(Fixtures.PARC_ALLOCATION_1, Fixtures.PARC_ALLOCATION_2))
                .build(),
            OfferBlock.builder()
                .start(Fixtures.OFFER_BLOCK_1.start())
                .end(Fixtures.OFFER_BLOCK_1.end())
                .maxSellPrice(Fixtures.OFFER_BLOCK_1.maxSellPrice())
                .quantity(300)
                .parkAllocations(List.of(Fixtures.PARC_ALLOCATION_1, Fixtures.PARC_ALLOCATION_2))
                .build()));
  }

  @ParameterizedTest
  @MethodSource
  void testFromDto(OfferBlockDto dto, OfferBlock expected) {
    assertEquals(expected, OfferBlockMapper.fromDto(dto));
  }
}
