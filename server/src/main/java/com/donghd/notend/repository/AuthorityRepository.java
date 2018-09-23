package com.donghd.notend.repository;

import com.donghd.notend.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findByName(String name);
}
