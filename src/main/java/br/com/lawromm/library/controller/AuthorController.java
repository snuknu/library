package br.com.lawromm.library.controller;

import br.com.lawromm.library.dto.author.AuthorDtoCreate;
import br.com.lawromm.library.dto.author.AuthorDtoDetail;
import br.com.lawromm.library.dto.author.AuthorDtoSummary;
import br.com.lawromm.library.dto.author.AuthorDtoUpdate;
import br.com.lawromm.library.model.Author;
import br.com.lawromm.library.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/author")
public class AuthorController {
  @Autowired
  private AuthorRepository authorRepository;

  @GetMapping
  public ResponseEntity<List<AuthorDtoSummary>> list() {
    return ResponseEntity.ok(AuthorDtoSummary.toList(authorRepository.findAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDtoDetail> detail(@PathVariable Long id) {
    return ResponseEntity.ok(
      new AuthorDtoDetail(authorRepository.getReferenceById(id))
    );
  }

  @PostMapping
  @Transactional
  public ResponseEntity<AuthorDtoSummary> create(
    @Valid @RequestBody AuthorDtoCreate dtoCreate,
    UriComponentsBuilder uriBuilder
  ) {
    var dto = new AuthorDtoSummary(authorRepository.save(new Author(dtoCreate)));
    return ResponseEntity
      .created(uriBuilder.path("/author/{id}").buildAndExpand(dto.getId()).toUri())
      .body(dto);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<AuthorDtoSummary> update(
    @Valid @RequestBody AuthorDtoUpdate dtoUpdate
  ) {
    var author = authorRepository.getReferenceById(dtoUpdate.getId());
    return ResponseEntity.ok().body(new AuthorDtoSummary(author.update(dtoUpdate)));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Author> optional = authorRepository.findById(id);
    if (optional.isPresent()) {
      authorRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
