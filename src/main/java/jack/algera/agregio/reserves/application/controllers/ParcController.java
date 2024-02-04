package jack.algera.agregio.reserves.application.controllers;

import java.util.Set;
import jack.algera.agregio.reserves.application.models.CreateParcRequest;
import jack.algera.agregio.reserves.application.exceptions.InvalidEnergyTypeException;
import jack.algera.agregio.reserves.application.exceptions.InvalidMarketException;
import jack.algera.agregio.reserves.domain.models.EnergyType;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.domain.models.Parc;
import jack.algera.agregio.reserves.domain.ports.ParcService;
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
@RequestMapping("/parcs")
@AllArgsConstructor
public class ParcController {

  private final ParcService parcService;

  @PostMapping
  public ResponseEntity<Parc> createParc(@RequestBody CreateParcRequest request) {
    if (!EnergyType.isValid(request.energyType())) {
      throw new InvalidEnergyTypeException(request.energyType());
    }

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(parcService.createParc(EnergyType.valueOf(request.energyType())));
  }

  @GetMapping
  public ResponseEntity<Set<Parc>> getParcs(@RequestParam String market) {
    if (!Market.isValid(market)) {
      throw new InvalidMarketException(market);
    }

    return ResponseEntity.ok(parcService.findAllByMarket(Market.valueOf(market)));
  }
}
