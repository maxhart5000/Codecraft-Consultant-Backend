/*
 * Repository interface for managing customer entities.
 */
package com.hartcode.application.dao;

import com.hartcode.application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Retrieve a customer by email
    Customer findByEmail(String email);
}
