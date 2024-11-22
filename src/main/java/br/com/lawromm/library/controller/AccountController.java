package br.com.lawromm.library.controller;

import br.com.lawromm.library.dto.account.AccountDtoCreate;
import br.com.lawromm.library.dto.account.AccountDtoDetail;
import br.com.lawromm.library.dto.account.AccountDtoSummary;
import br.com.lawromm.library.dto.account.AccountDtoUpdate;
import br.com.lawromm.library.model.Account;
import br.com.lawromm.library.repository.AccountRepository;
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
@RequestMapping("/account")
public class AccountController {
  @Autowired
  private AccountRepository accountRepository;

  @GetMapping
  public ResponseEntity<List<AccountDtoSummary>> list() {
    return ResponseEntity.ok(AccountDtoSummary.toList(accountRepository.findAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccountDtoDetail> detail(@PathVariable Long id) {
    return ResponseEntity.ok(new AccountDtoDetail(accountRepository.getReferenceById(id)));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<AccountDtoDetail> create(
    @Valid @RequestBody AccountDtoCreate dtoCreate,
    UriComponentsBuilder uriBuilder
  ) {
    var dto = new AccountDtoDetail(accountRepository.save(new Account(dtoCreate)));
    return ResponseEntity
      .created(uriBuilder.path("/account/{id}").buildAndExpand(dto.getId()).toUri())
      .body(dto);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<AccountDtoDetail> update(
    @Valid @RequestBody AccountDtoUpdate dtoUpdate
  ) {
    var author = accountRepository.getReferenceById(dtoUpdate.getId());
    return ResponseEntity.ok().body(new AccountDtoDetail(author.update(dtoUpdate)));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Account> optional = accountRepository.findById(id);
    if (optional.isPresent()) {
      accountRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
