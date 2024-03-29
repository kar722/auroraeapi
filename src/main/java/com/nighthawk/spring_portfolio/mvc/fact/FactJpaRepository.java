package com.nighthawk.spring_portfolio.mvc.fact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
Extends the JpaRepository interface from Spring Data JPA.
-- Java Persistent API (JPA) - Hibernate: map, store, update and retrieve database
-- JpaRepository defines standard CRUD methods
-- Via JPA the developer can retrieve database from relational databases to Java objects and vice versa.
 */
public interface FactJpaRepository extends JpaRepository<Facto, Long> {
    Facto findByEmail(String email);

    List<Facto> findAllByOrderByNameAsc();

    // JPA query, findBy does JPA magic with "Name", "Containing", "Or", "Email", "IgnoreCase"
    List<Facto> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
    /* Custom JPA query articles, there are articles that show custom SQL as well
       https://springframework.guru/spring-data-jpa-query/
       https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
     */

    // Custom JPA query
    @Query(
            value = "SELECT * FROM Fact f WHERE f.name LIKE ?1 or f.email LIKE ?1",
            nativeQuery = true)
    List<Facto> findByLikeTermNative(String term);
    /*
        https://www.baeldung.com/spring-data-jpa-query
     */
}