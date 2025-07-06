package ru.infologistics.docuforce365.shell;

import jakarta.servlet.http.HttpServletRequest;
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
import ru.infologistics.docuforce365.shell.ErrorCode;

/** Handles uncaught exceptions and converts them to API error responses. */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  private final MessageSource messageSource;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiRestResponse> handleException(
      Exception ex, Locale locale, HttpServletRequest request) {
    log.error("Unhandled exception", ex);
    String message = messageSource.getMessage("error.internal", null, locale);
    ApiRestResponse body = new ApiRestResponse(
        "about:blank",
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        message,
        request.getRequestURI(),
        MDC.get(Coonsts.CORRELATION_ID_LOG_VAR),
        ex.getMessage(),
        System.currentTimeMillis(),
        ErrorCode.UNSPECIFIED);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
