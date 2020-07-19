package org.patsimas.company.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.company.domain.Employee;
import org.patsimas.company.dto.EmployeeDto;
import org.patsimas.company.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ConversionService conversionService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ConversionService conversionService) {
        this.employeeRepository = employeeRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<EmployeeDto> findAll() {

        log.info("Find all employees process start");

        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDto> employeeDtoList = employees
                .stream()
                .map(employee -> conversionService.convert(employee, EmployeeDto.class))
                .collect(Collectors.toList());

        log.info("Find all employees process end");

        return employeeDtoList;
    }
}
