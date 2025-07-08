package ru.infologistics.docuforce365.shell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.infologistics.docuforce365.shell.ErrorCode;

/** Standardized API error response. */
@Data
@AllArgsConstructor
public class ApiRestResponse {
  private String type;
  private String title;
  private int status;
  private String detail;
  private String instance;
  private String correlationId;
  private String debugMessage;
  private long timestamp;
  private ErrorCode errorCode;
}
