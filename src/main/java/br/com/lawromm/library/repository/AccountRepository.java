package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lawromm.library.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {}
