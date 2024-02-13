/*
 * Repository interface for managing states.
 */
package com.hartcode.application.dao;

import com.hartcode.application.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {

    // Find states by country code
    List<State> findByCountryCode(@Param("code") String code);
}
