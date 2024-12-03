package br.com.lawromm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lawromm.library.model.Address;
import br.com.lawromm.library.model.AddressId;

public interface AddressRepository extends JpaRepository<Address, AddressId> {}
