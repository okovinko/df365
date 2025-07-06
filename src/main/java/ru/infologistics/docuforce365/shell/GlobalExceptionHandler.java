package ru.infologistics.docuforce365.shell;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.infologistics.docuforce365.shell.dto.ApiRestResponse;

/** Handles uncaught exceptions and converts them to API error responses. */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  private final MessageSource messageSource;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiRestResponse> handleException(Exception ex, Locale locale) {
    log.error("Unhandled exception", ex);
    String message = messageSource.getMessage("error.internal", null, locale);
    ApiRestResponse body = new ApiRestResponse(
        System.currentTimeMillis(),
        MDC.get(Coonsts.CORRELATION_ID_LOG_VAR),
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        message,
        ErrorCode.UNSPECIFIED,
        ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
