package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lawromm.library.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {}
