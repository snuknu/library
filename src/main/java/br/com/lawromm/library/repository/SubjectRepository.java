package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lawromm.library.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}
