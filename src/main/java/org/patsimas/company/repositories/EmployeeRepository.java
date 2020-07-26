package org.patsimas.company.repositories;

import org.patsimas.company.domain.Attribute;
import org.patsimas.company.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findEmployeeBySupervisor(Employee supervisor);

    List<Employee> findDistinctEmployeesByAttributesIn(List<Attribute> attributes);
}
