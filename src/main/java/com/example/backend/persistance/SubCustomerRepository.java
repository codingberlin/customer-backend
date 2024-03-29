package com.example.backend.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubCustomerRepository extends CrudRepository<SubCustomer, UUID> {
    List<SubCustomer> findAllByCustomerIdOrderByLastNameAscFirstNameAsc(final UUID customerId);
}
