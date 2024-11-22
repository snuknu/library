package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lawromm.library.model.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {}
