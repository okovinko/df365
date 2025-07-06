package ru.infologistics.docuforce365.shell.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuildInfoResponse {
  private String name;
  private String group;
  private String artifact;
  private String version;
  private Instant time;
}
