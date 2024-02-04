package jack.algera.agregio.reserves.domain.models;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class EnergyTypeTest {

  static Stream<Arguments> isValidEnergyType() {
    return Stream.of(
        Arguments.of("SOLAR", true),
        Arguments.of("WIND", true),
        Arguments.of("HYDRAULIC", true),
        Arguments.of("", false),
        Arguments.of(null, false));
  }

  @ParameterizedTest
  @MethodSource
  void isValidEnergyType(String energyType, boolean expected) {
    assertEquals(expected, EnergyType.isValid(energyType));
  }
}
