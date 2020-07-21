package org.patsimas.company.repositories;

import org.patsimas.company.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, String> {

    Optional<Attribute> findByNameAndValue(String name, String value);
}
