package jack.algera.agregio.reserves.domain.models;

public enum EnergyType {
  SOLAR, WIND, HYDRAULIC;

  public static boolean isValid(String energyType) {
    for (EnergyType e : EnergyType.values()) {
      if (e.name().equalsIgnoreCase(energyType)) {
        return true;
      }
    }
    return false;
  }
}
