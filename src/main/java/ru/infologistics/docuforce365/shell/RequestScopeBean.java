package ru.infologistics.docuforce365.shell;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/** Holds request-specific context like correlation id. */
@Getter
@Setter
@Component
@RequestScope
public class RequestScopeBean {
  private String correlationId;
}

