package br.com.lawromm.library.controller;

import br.com.lawromm.library.enumeration.WorkType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class Teste {

  @GetMapping("/locale")
  public ResponseEntity<String> locale() {
    List<String> typesWork = new ArrayList<>();

    Arrays
      .asList(WorkType.values())
      .stream()
      .forEach(type -> typesWork.add(type.getDescription()));

    return ResponseEntity.ok(typesWork.toString());
  }
}
