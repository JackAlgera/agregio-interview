package jack.algera.agregio.reserves.application.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {InvalidEnergyTypeException.class, InvalidMarketException.class})
  protected ResponseEntity<Object> handleAmountNegative(RuntimeException ex, WebRequest request) {
    ExceptionResponse response =
        new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST,
        request);
  }
}
