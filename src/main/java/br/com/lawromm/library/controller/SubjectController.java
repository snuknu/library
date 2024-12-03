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
import br.com.lawromm.library.dto.subject.SubjectDtoCreate;
import br.com.lawromm.library.dto.subject.SubjectDtoDetail;
import br.com.lawromm.library.dto.subject.SubjectDtoSummary;
import br.com.lawromm.library.dto.subject.SubjectDtoUpdate;
import br.com.lawromm.library.model.Subject;
import br.com.lawromm.library.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/subject")
public class SubjectController {
  @Autowired
  private SubjectRepository subjectRepository;

  @GetMapping
  public ResponseEntity<List<SubjectDtoSummary>> list() {
    return ResponseEntity.ok(SubjectDtoSummary.toList(subjectRepository.findAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubjectDtoDetail> detail(@PathVariable Long id) {
    return ResponseEntity.ok(
      new SubjectDtoDetail(subjectRepository.getReferenceById(id))
    );
  }

  @PostMapping
  @Transactional
  public ResponseEntity<SubjectDtoSummary> create(
    @Valid @RequestBody SubjectDtoCreate dtoCreate,
    UriComponentsBuilder uriBuilder
  ) {
    var dto = new SubjectDtoSummary(subjectRepository.save(new Subject(dtoCreate)));
    return ResponseEntity
      .created(uriBuilder.path("/subject/{id}").buildAndExpand(dto.getId()).toUri())
      .body(dto);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<SubjectDtoSummary> update(
    @Valid @RequestBody SubjectDtoUpdate dtoUpdate
  ) {
    var subject = subjectRepository.getReferenceById(dtoUpdate.getId());
    return ResponseEntity.ok().body(new SubjectDtoSummary(subject.update(dtoUpdate)));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> remover(@PathVariable Long id) {
    Optional<Subject> optional = subjectRepository.findById(id);
    if (optional.isPresent()) {
      subjectRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
