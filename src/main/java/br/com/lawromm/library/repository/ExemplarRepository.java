package br.com.lawromm.library.repository;

import br.com.lawromm.library.model.Exemplar;
import br.com.lawromm.library.model.IdExemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, IdExemplar> {}
