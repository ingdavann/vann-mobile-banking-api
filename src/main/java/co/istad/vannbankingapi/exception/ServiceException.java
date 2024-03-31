package co.istad.vannbankingapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

public class ServiceException {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleExceptionError(ResponseStatusException exception){
        return ResponseEntity.status(exception.getStatusCode())
                .body(
                        Map.of("error", exception.getReason())
                );
    }
}
