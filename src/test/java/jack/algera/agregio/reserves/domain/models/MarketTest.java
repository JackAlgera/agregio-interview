package jack.algera.agregio.reserves.domain.models;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MarketTest {

  static Stream<Arguments> isValidMarket() {
    return Stream.of(
        Arguments.of("RESERVE_PRIMAIRE", true),
        Arguments.of("RESERVE_SECONDAIRE", true),
        Arguments.of("RESERVE_RAPIDE", true),
        Arguments.of("", false),
        Arguments.of("null ", false));
  }

  @ParameterizedTest
  @MethodSource
  void isValidMarket(String market, boolean expected) {
    assertEquals(expected, Market.isValid(market));
  }
}
