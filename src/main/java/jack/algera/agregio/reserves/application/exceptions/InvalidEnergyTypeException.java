package jack.algera.agregio.reserves.application.exceptions;

public class InvalidEnergyTypeException extends RuntimeException {

  public InvalidEnergyTypeException(String energyType) {
    super("Invalid energy type: " + energyType);
  }
}
