package jack.algera.agregio.reserves.application.controllers;

import java.util.List;
import jack.algera.agregio.reserves.application.models.CreateOfferRequest;
import jack.algera.agregio.reserves.application.exceptions.InvalidMarketException;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Offer;
import jack.algera.agregio.reserves.domain.ports.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/offers")
public class OfferController {

  private OfferService offerService;

  @PostMapping
  public ResponseEntity<Offer> createOffer(@RequestBody CreateOfferRequest request) {
    if (!Market.isValid(request.market())) {
      throw new InvalidMarketException(request.market());
    }

    Offer offer = offerService.createOffer(Market.valueOf(request.market()), request.offerBlocks());
    return ResponseEntity.status(HttpStatus.CREATED).body(offer);
  }

  @GetMapping
  public ResponseEntity<List<Offer>> getOffers(@RequestParam String market) {
    if (!Market.isValid(market)) {
      throw new InvalidMarketException(market);
    }

    return ResponseEntity.ok(offerService.getOffersForMarket(Market.valueOf(market)));
  }
}
