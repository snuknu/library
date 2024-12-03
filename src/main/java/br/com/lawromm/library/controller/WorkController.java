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
import br.com.lawromm.library.dto.work.WorkDtoCreate;
import br.com.lawromm.library.dto.work.WorkDtoDetail;
import br.com.lawromm.library.dto.work.WorkDtoSummary;
import br.com.lawromm.library.dto.work.WorkDtoUpdate;
import br.com.lawromm.library.model.Work;
import br.com.lawromm.library.repository.WorkRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/work")
public class WorkController {
  @Autowired
  private WorkRepository workRepository;

  @GetMapping
  public ResponseEntity<List<WorkDtoSummary>> list() {
    return ResponseEntity.ok(WorkDtoSummary.toList(workRepository.findAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<WorkDtoDetail> detail(@PathVariable Long id) {
    return ResponseEntity.ok(new WorkDtoDetail(workRepository.getReferenceById(id)));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<WorkDtoDetail> create(
    @Valid @RequestBody WorkDtoCreate dtoCreate,
    UriComponentsBuilder uriBuilder
  ) {
    var dto = new WorkDtoDetail(workRepository.save(new Work(dtoCreate)));
    return ResponseEntity
      .created(uriBuilder.path("/work/{id}").buildAndExpand(dto.getId()).toUri())
      .body(dto);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<WorkDtoDetail> update(
    @Valid @RequestBody WorkDtoUpdate dtoUpdate
  ) {
    var work = workRepository.getReferenceById(dtoUpdate.getId());
    return ResponseEntity.ok().body(new WorkDtoDetail(work.update(dtoUpdate)));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Work> optional = workRepository.findById(id);
    if (optional.isPresent()) {
      workRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
