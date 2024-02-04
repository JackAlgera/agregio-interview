package jack.algera.agregio.reserves.application.configurations;

import jack.algera.agregio.reserves.domain.adapters.OfferServiceImpl;
import jack.algera.agregio.reserves.domain.adapters.ParcServiceImpl;
import jack.algera.agregio.reserves.domain.ports.OfferRepository;
import jack.algera.agregio.reserves.domain.ports.OfferService;
import jack.algera.agregio.reserves.domain.ports.ParcRepository;
import jack.algera.agregio.reserves.domain.ports.ParcService;
import jack.algera.agregio.reserves.infrastructure.stubs.OfferRepositoryStub;
import jack.algera.agregio.reserves.infrastructure.stubs.ParcRepositoryStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdaptersConfiguration {

  @Bean
  protected OfferRepository offerRepository() {
    return new OfferRepositoryStub();
  }

  @Bean
  protected ParcRepository parcRepository() {
    return new ParcRepositoryStub();
  }

  @Bean
  protected OfferService offerService() {
    return new OfferServiceImpl(offerRepository());
  }

  @Bean
  protected ParcService parcService() {
    return new ParcServiceImpl(parcRepository(), offerRepository());
  }
}
