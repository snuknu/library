package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lawromm.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
