package ru.infologistics.docuforce365.shell.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.infologistics.docuforce365.shell.dto.BuildInfoResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BuildInfoController {
  private final BuildProperties buildProperties;

  @GetMapping("/")
  public BuildInfoResponse index() {
    log.debug("Returning build properties");
    return new BuildInfoResponse(
        buildProperties.getName(),
        buildProperties.getGroup(),
        buildProperties.getArtifact(),
        buildProperties.getVersion(),
        buildProperties.getTime());
  }
}
