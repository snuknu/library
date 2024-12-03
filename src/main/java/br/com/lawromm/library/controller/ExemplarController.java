package br.com.lawromm.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.lawromm.library.dto.DTOExemplar;
import br.com.lawromm.library.model.Exemplar;
import br.com.lawromm.library.model.IdExemplar;
import br.com.lawromm.library.repository.ExemplarRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/exemplar")
public class ExemplarController {
  @Autowired
  private ExemplarRepository exemplarRepository;

  @GetMapping("/listar")
  public ResponseEntity<List<DTOExemplar>> listar() {
    return ResponseEntity.ok(DTOExemplar.converter(exemplarRepository.findAll()));
  }

  @GetMapping("/detalhar/{obraCodigo}/{numExemplar}")
  public ResponseEntity<DTOExemplar> detalhar(
    @PathVariable Long obraCodigo,
    @PathVariable Long numExemplar
  ) {
    return ResponseEntity.ok(
      new DTOExemplar(
        exemplarRepository.getReferenceById(new IdExemplar(numExemplar, obraCodigo))
      )
    );
  }

  @PostMapping("/cadastrar")
  @Transactional
  public ResponseEntity<DTOExemplar> cadastrar(
    @RequestBody DTOExemplar dtoExemplar,
    UriComponentsBuilder uriBuilder
  ) {
    Exemplar exemplar = new Exemplar(dtoExemplar);
    exemplarRepository.save(exemplar);

    return ResponseEntity
      .created(
        uriBuilder
          .path("/detalhar/{obraCodigo}/{numExemplar}")
          .buildAndExpand(exemplar.getObraCodigo(), exemplar.getNumExemplar())
          .toUri()
      )
      .body(new DTOExemplar(exemplar));
  }
}
