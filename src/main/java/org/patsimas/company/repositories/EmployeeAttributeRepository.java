package org.patsimas.company.repositories;

import org.patsimas.company.domain.EmployeeAttribute;
import org.patsimas.company.domain.EmployeeAttributeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeAttributeRepository extends JpaRepository<EmployeeAttribute, EmployeeAttributeId> {

    @Transactional
    @Modifying
    @Query(value = "delete from EmployeeAttribute where EMPATTR_AttributeID = :attributeId",
            nativeQuery = true)
    void deleteAttributeFromEmployees(@Param("attributeId") String attributeId);

    @Query(value = "select * from EmployeeAttribute where EMPATTR_AttributeID = :attributeId",
            nativeQuery = true)
    List<String> findAttributes(@Param("attributeId") String attributeId);
}
