package org.patsimas.company.controllers;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.company.dto.EmployeeDto;
import org.patsimas.company.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Retrieves the employee specified by given id
     *
     * @param id the employee id
     * @return the employee specified by given id
     */
    @GetMapping("/{id}")
    EmployeeDto fetchEmployeeById(@PathVariable String id) {
        log.info("Fetch Employee[id: {}]", id);
        return employeeService.fetchEmployeeById(id);
    }

    /**
     * Inserts a new employee in case given employeeDto does not provide an id
     * Updates an existing employee in case given employeeDto provides an id
     *
     * @param employeeDto the employee object to be saved
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("Save Employee[{}]", employeeDto.toString());
        employeeService.save(employeeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Deletes the employee specified by the given id
     *
     * @param id the employee id
     */
    @DeleteMapping("/{id}")
    ResponseEntity deleteEmployee(@PathVariable String id) {
        log.info("Delete Employee with id [{}]", id);
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
