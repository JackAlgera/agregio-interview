package jack.algera.agregio.reserves.application.exceptions;

public class InvalidMarketException extends RuntimeException {

  public InvalidMarketException(String market) {
    super(String.format("Invalid market: %s", market));
  }
}
