package br.com.lawromm.library.repository;

import br.com.lawromm.library.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}
