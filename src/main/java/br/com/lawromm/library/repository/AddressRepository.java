package br.com.lawromm.library.repository;

import br.com.lawromm.library.model.Address;
import br.com.lawromm.library.model.AddressId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, AddressId> {}
