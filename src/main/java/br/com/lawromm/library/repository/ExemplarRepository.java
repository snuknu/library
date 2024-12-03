package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lawromm.library.model.Exemplar;
import br.com.lawromm.library.model.IdExemplar;

public interface ExemplarRepository extends JpaRepository<Exemplar, IdExemplar> {}
