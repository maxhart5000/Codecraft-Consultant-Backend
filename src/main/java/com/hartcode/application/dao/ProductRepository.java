/*
 * Repository interface for managing products.
 */
package com.hartcode.application.dao;

import com.hartcode.application.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by category ID
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    // Find products by name containing the given string
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
