package org.patsimas.company.services;

import org.patsimas.company.dto.AttributeDto;
import org.patsimas.company.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeDto> findAll();

    List<EmployeeDto> findEmployeeByAttributes(List<AttributeDto> attributeDtoList);

    EmployeeDto fetchEmployeeById(String id);

    void save(EmployeeDto employeeDto);

    void deleteEmployee(String id);
}
