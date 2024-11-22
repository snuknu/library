package br.com.lawromm.library.controller;

import br.com.lawromm.library.dto.DTOReserva;
import br.com.lawromm.library.model.Reserva;
import br.com.lawromm.library.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
  @Autowired
  private ReservaRepository reservaRepository;

  @GetMapping("/listar")
  public ResponseEntity<List<DTOReserva>> listar() {
    return ResponseEntity.ok(DTOReserva.converter(reservaRepository.findAll()));
  }

  @GetMapping("/detalhar/{id}")
  public ResponseEntity<DTOReserva> detalhar(@PathVariable Long id) {
    return ResponseEntity.ok(new DTOReserva(reservaRepository.getReferenceById(id)));
  }

  @PostMapping("/cadastrar")
  @Transactional
  public ResponseEntity<DTOReserva> cadastrar(
    @RequestBody DTOReserva dtoReserva,
    UriComponentsBuilder uriBuilder
  ) {
    Reserva reserva = new Reserva(dtoReserva);
    reservaRepository.save(reserva);

    return ResponseEntity
      .created(
        uriBuilder
          .path("/reserva/detalhar/{id}")
          .buildAndExpand(reserva.getCodigo())
          .toUri()
      )
      .body(new DTOReserva(reserva));
  }

  @PostMapping("/atualizar")
  @Transactional
  public ResponseEntity<DTOReserva> atualizar(@RequestBody DTOReserva dtoReserva) {
    reservaRepository.getReferenceById(dtoReserva.getCodigo());
    return ResponseEntity
      .ok()
      .body(new DTOReserva(reservaRepository.save(new Reserva(dtoReserva))));
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<?> remover(@PathVariable Long id) {
    Optional<Reserva> optional = reservaRepository.findById(id);
    if (optional.isPresent()) {
      reservaRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
