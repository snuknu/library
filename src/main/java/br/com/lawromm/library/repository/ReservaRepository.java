package br.com.lawromm.library.repository;

import br.com.lawromm.library.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}
