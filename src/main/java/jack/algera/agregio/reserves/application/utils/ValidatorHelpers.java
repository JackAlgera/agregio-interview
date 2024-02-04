package jack.algera.agregio.reserves.application.utils;

import java.util.UUID;

public class ValidatorHelpers {

  public static boolean isValidUuid(String uuid) {
    try {
      UUID.fromString(uuid);
      return true;
    } catch (IllegalArgumentException exception) {
      return false;
    }
  }
}
