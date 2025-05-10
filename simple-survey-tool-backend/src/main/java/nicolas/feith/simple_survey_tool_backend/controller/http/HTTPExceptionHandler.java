package nicolas.feith.simple_survey_tool_backend.controller.http;

import static org.slf4j.LoggerFactory.getLogger;

import jakarta.servlet.http.HttpServletRequest;
import nicolas.feith.simple_survey_tool_backend.core.exceptions.CouldNotSaveSurveyResponseException;
import nicolas.feith.simple_survey_tool_backend.core.exceptions.SurveyNotFoundException;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HTTPExceptionHandler {

    private static final Logger LOGGER = getLogger(HTTPExceptionHandler.class);

    @ExceptionHandler(SurveyNotFoundException.class)
    ResponseEntity<String> handleSurveyNotFoundException(
        final HttpServletRequest request, final SurveyNotFoundException exception
    ) {
        final var url = request.getRequestURL();
        final var message = exception.getMessage();
        final var resultMessage = "Request: %s raised %s".formatted(url, message);

        LOGGER.error(resultMessage, exception);

        return ResponseEntity.status(404).body(resultMessage);
    }

    @ExceptionHandler(CouldNotSaveSurveyResponseException.class)
    ResponseEntity<String> handleCouldNotSaveSurveyResponseException(
        final HttpServletRequest request, final CouldNotSaveSurveyResponseException exception
    ) {
        final var url = request.getRequestURL();
        final var message = exception.getMessage();
        final var resultMessage = "Request: %s raised %s".formatted(url, message);

        LOGGER.error(resultMessage, exception);

        return ResponseEntity.status(500).body(resultMessage);
    }

    // Fallback for unhandled exceptions
    // This will catch all exceptions that are not handled by other exception handlers
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleException(
        final HttpServletRequest request, final Exception exception
    ) {
        final var url = request.getRequestURL();
        final var message = exception.getMessage();
        final var resultMessage = "Request: %s raised %s".formatted(url, message);

        LOGGER.error(resultMessage, exception);

        return ResponseEntity.status(500).body(resultMessage);
    }
}