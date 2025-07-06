package ru.infologistics.docuforce365.shell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.infologistics.docuforce365.shell.ErrorCode;

/** Error response structure matching the API specification. */
@Data
@AllArgsConstructor
public class ApiRestResponse {
  private long timestamp;
  private String correlationId;
  private String error;
  private String message;
  private ErrorCode errorCode;
  private String debugMessage;
}
