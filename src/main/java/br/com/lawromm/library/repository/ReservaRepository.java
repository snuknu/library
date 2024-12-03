package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lawromm.library.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}
