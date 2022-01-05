package com.design.ovm.catalog.repository;



import com.design.ovm.catalog.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
     List<Customer> findAll();
     Customer findById(long id);
     Customer findByEmail(String email);
}