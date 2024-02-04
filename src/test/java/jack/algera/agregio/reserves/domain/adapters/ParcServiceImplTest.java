package jack.algera.agregio.reserves.domain.adapters;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;
import jack.algera.agregio.reserves.domain.models.Parc;
import jack.algera.agregio.reserves.domain.ports.OfferRepository;
import jack.algera.agregio.reserves.domain.ports.ParcRepository;
import jack.algera.agregio.reserves.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParcServiceImplTest {

  @Mock
  private ParcRepository parcRepository;
  @Mock
  private OfferRepository offerRepository;

  @InjectMocks
  private ParcServiceImpl parcService;

  @Test
  void testCreateParcs() {
    Parc parc1 = parcService.createParc(Fixtures.PARC_1.energyType());
    Parc parc2 = parcService.createParc(Fixtures.PARC_2.energyType());

    verify(parcRepository, times(1)).save(parc1);
    verify(parcRepository, times(1)).save(parc2);
  }

  static Stream<Arguments> testGetParcsByMarket() {
    return Stream.of(
        // No offers for market
        Arguments.of(
            List.of(),
            Set.of()),
        // One offer with one parc, should return one parc
        Arguments.of(
            List.of(Fixtures.createOffer(Fixtures.OFFER_BLOCK_1)),
            Set.of(Fixtures.PARC_1)),
        // One offer with two parcs, should return two parcs
        Arguments.of(
            List.of(Fixtures.createOffer(Fixtures.OFFER_BLOCK_2)),
            Set.of(Fixtures.PARC_1, Fixtures.PARC_2)),
        // Two offers with the same parc, should return only one parc
        Arguments.of(
            List.of(Fixtures.createOffer(Fixtures.OFFER_BLOCK_1),
                Fixtures.createOffer(Fixtures.OFFER_BLOCK_1)),
            Set.of(Fixtures.PARC_1)),
        // Two offers with different parcs, should return two parcs
        Arguments.of(
            List.of(Fixtures.createOffer(Fixtures.OFFER_BLOCK_1),
                Fixtures.createOffer(Fixtures.OFFER_BLOCK_3)),
            Set.of(Fixtures.PARC_1, Fixtures.PARC_3)),
        // Two offers with the same parc and another offer with another parc, should return 3 parcs
        Arguments.of(
            List.of(
                Fixtures.createOffer(Fixtures.OFFER_BLOCK_1, Fixtures.OFFER_BLOCK_2),
                Fixtures.createOffer(Fixtures.OFFER_BLOCK_3)),
            Set.of(Fixtures.PARC_1, Fixtures.PARC_2, Fixtures.PARC_3)));
  }

  @ParameterizedTest
  @MethodSource
  void testGetParcsByMarket(List<Offer> offers, Set<Parc> expectedParcs) {
    Market market = Market.RESERVE_RAPIDE;

    when(offerRepository.findAllByMarket(market)).thenReturn(offers);

    assertEquals(expectedParcs, parcService.findAllByMarket(market));
  }
}
