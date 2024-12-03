package br.com.lawromm.library.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.lawromm.library.dto.publisher.PublisherDtoCreate;
import br.com.lawromm.library.dto.publisher.PublisherDtoDetail;
import br.com.lawromm.library.dto.publisher.PublisherDtoSummary;
import br.com.lawromm.library.dto.publisher.PublisherDtoUpdate;
import br.com.lawromm.library.model.Publisher;
import br.com.lawromm.library.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
  @Autowired
  private PublisherRepository authorRepository;

  @GetMapping
  public ResponseEntity<List<PublisherDtoSummary>> list() {
    return ResponseEntity.ok(PublisherDtoSummary.toList(authorRepository.findAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PublisherDtoDetail> detail(@PathVariable Long id) {
    return ResponseEntity.ok(
      new PublisherDtoDetail(authorRepository.getReferenceById(id))
    );
  }

  @PostMapping
  @Transactional
  public ResponseEntity<PublisherDtoDetail> create(
    @Valid @RequestBody PublisherDtoCreate dtoCreate,
    UriComponentsBuilder uriBuilder
  ) {
    var dto = new PublisherDtoDetail(authorRepository.save(new Publisher(dtoCreate)));
    return ResponseEntity
      .created(uriBuilder.path("/publisher/{id}").buildAndExpand(dto.getId()).toUri())
      .body(dto);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<PublisherDtoDetail> update(
    @Valid @RequestBody PublisherDtoUpdate dtoUpdate
  ) {
    var author = authorRepository.getReferenceById(dtoUpdate.getId());
    return ResponseEntity.ok().body(new PublisherDtoDetail(author.update(dtoUpdate)));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Publisher> optional = authorRepository.findById(id);
    if (optional.isPresent()) {
      authorRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
