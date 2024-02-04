package jack.algera.agregio.reserves.domain.utils;

import java.util.UUID;
import java.util.stream.Stream;
import jack.algera.agregio.reserves.application.utils.ValidatorHelpers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

class ValidatorHelpersTest {

  static Stream<Arguments> testCorrectlyValidateUuid() {
    return Stream.of(
        Arguments.of(UUID.randomUUID().toString(), true),
        Arguments.of("Not a UUID", false),
        Arguments.of(UUID.fromString("b007ab1e-dead-c0de-feed-b1ab1ac0ffee").toString(), true));
  }

  @ParameterizedTest
  @MethodSource
  void testCorrectlyValidateUuid(String uuid, boolean isValid) {
    assertThat(ValidatorHelpers.isValidUuid(uuid)).isEqualTo(isValid);
  }
}
