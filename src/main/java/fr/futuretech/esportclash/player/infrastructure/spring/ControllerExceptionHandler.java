package fr.futuretech.esportclash.player.infrastructure.spring;

import fr.futuretech.esportclash.core.domain.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        LOGGER.error("LOADING handler for NotFoundException");
        return ResponseEntity.notFound().build();
    }
}
