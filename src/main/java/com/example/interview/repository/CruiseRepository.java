package com.example.interview.repository;

import com.example.interview.model.Cruise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CruiseRepository extends JpaRepository <Cruise, Long> {
    Optional<Cruise> findByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);
}
