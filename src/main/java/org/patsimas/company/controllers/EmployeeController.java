package org.patsimas.company.controllers;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.company.dto.EmployeeDto;
import org.patsimas.company.repositories.EmployeeRepository;
import org.patsimas.company.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/employees")
@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves all employees
     *
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<EmployeeDto> findAllEmployees() {

        log.info("Fetch all employees");

        return employeeService.findAll();
    }
}
