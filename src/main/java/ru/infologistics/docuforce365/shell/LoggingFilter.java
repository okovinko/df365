package ru.infologistics.docuforce365.shell;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter that enriches requests with correlation id and puts it in MDC
 * so that all log messages contain it.
 */
@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {
  private static final char[] ALPHANUMERIC =
      "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
  private static final SecureRandom RANDOM = new SecureRandom();

  private String generateCorrelationId() {
    char[] buffer = new char[8];
    for (int i = 0; i < buffer.length; i++) {
      buffer[i] = ALPHANUMERIC[RANDOM.nextInt(ALPHANUMERIC.length)];
    }
    return new String(buffer);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String correlationId = request.getHeader(Coonsts.HEADER_CORRELATION_ID);
    if (correlationId == null || !correlationId.matches(Coonsts.CORRELATION_ID_PATTERN)) {
      correlationId = generateCorrelationId();
    }
    MDC.put(Coonsts.CORRELATION_ID_LOG_VAR, correlationId);
    response.setHeader(Coonsts.HEADER_CORRELATION_ID, correlationId);
    try {
      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(Coonsts.CORRELATION_ID_LOG_VAR);
    }
  }
}
