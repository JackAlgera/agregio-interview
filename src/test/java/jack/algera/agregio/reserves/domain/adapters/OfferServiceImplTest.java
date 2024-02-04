package jack.algera.agregio.reserves.domain.adapters;

import java.time.OffsetDateTime;
import java.util.List;
import jack.algera.agregio.reserves.domain.daos.OfferBlockDto;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;
import jack.algera.agregio.reserves.domain.ports.OfferRepository;
import jack.algera.agregio.reserves.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {

  @Mock
  private OfferRepository offerRepository;

  @InjectMocks
  private OfferServiceImpl offerService;

  @Test
  void testCorrectlyCreateOffer() {
    Offer offer = offerService.createOffer(Market.RESERVE_PRIMAIRE, List.of(
        OfferBlockDto.builder()
            .start(OffsetDateTime.now())
            .end(OffsetDateTime.now().plusHours(1))
            .maxSellPrice(100)
            .parkAllocations(List.of(
                Fixtures.PARC_ALLOCATION_1,
                Fixtures.PARC_ALLOCATION_2))
            .build()));

    verify(offerRepository, times(1)).save(offer);
  }

  @Test
  void testGetOffersForMarket() {
    Market market = Market.RESERVE_PRIMAIRE;
    List<Offer> offers = List.of(Fixtures.createOffer(Fixtures.OFFER_BLOCK_2));

    when(offerRepository.findAllByMarket(market)).thenReturn(offers);

    assertEquals(offers, offerService.getOffersForMarket(market));
  }
}
