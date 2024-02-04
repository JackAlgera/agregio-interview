package jack.algera.agregio.reserves.domain.models;

public enum Market {
  RESERVE_PRIMAIRE, RESERVE_SECONDAIRE, RESERVE_RAPIDE;

  public static boolean isValid(String market) {
    for (Market m : Market.values()) {
      if (m.name().equalsIgnoreCase(market)) {
        return true;
      }
    }
    return false;
  }
}
