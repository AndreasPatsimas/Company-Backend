package org.patsimas.company.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.company.domain.Employee;
import org.patsimas.company.dto.EmployeeDto;
import org.patsimas.company.exceptions.ResourceNotFoundException;
import org.patsimas.company.repositories.EmployeeAttributeRepository;
import org.patsimas.company.repositories.EmployeeRepository;
import org.patsimas.company.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeAttributeRepository employeeAttributeRepository;

    private ConversionService conversionService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeAttributeRepository employeeAttributeRepository,
                               ConversionService conversionService) {
        this.employeeRepository = employeeRepository;
        this.employeeAttributeRepository = employeeAttributeRepository;
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

    @Override
    public EmployeeDto fetchEmployeeById(String id) {

        log.info("Fetch employee[id: {}] start", id);

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        EmployeeDto employeeDto = optionalEmployee
                .map(employee -> conversionService.convert(employee, EmployeeDto.class))
                .orElse(null);

        if (ObjectUtils.isEmpty(employeeDto)) {
            log.error("Employee [id: {}] cannot be found", id);
            throw new ResourceNotFoundException(MessageFormat.format("Employee [id: {0}] does not exist", id));
        }

        log.info("Fetch employee[id: {}] end", id);

        return employeeDto;
    }

    @Override
    public void save(EmployeeDto employeeDto) {

        log.info("Save new employee start");

        if (ObjectUtils.isEmpty(employeeDto)) {
            throw new ResourceNotFoundException("Employee to be saved is empty");
        }
        // insert a new employee in case id is not provided
        if (employeeDto.getId() == null){

            String id = Generator.generateId();

            employeeDto.setId(id);
        }

        Employee employee = conversionService.convert(employeeDto, Employee.class);

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Save new employee[id: {}] end", savedEmployee.getId());
    }

    @Override
    public void deleteEmployee(String id) {

        log.info("Delete employee {}  process begins", id);

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        employeeOptional.ifPresent(employee -> {

            clearSubordinatesBySupervisor(employee);

            List<String> employeeList = employeeAttributeRepository.findEmployees(id);

            if (!employeeList.isEmpty())
                employeeAttributeRepository.deleteEmployeeFromAttributes(id);

            employeeRepository.delete(employee);
        });

        employeeOptional.orElseThrow(() -> new ResourceNotFoundException("Employee to be deleted not exist"));

        log.info("Delete attribute process completed");
    }

    private void clearSubordinatesBySupervisor(Employee supervisor){

        List<Employee> subordinates = employeeRepository.findEmployeeBySupervisor(supervisor);

        if (!subordinates.isEmpty())
            subordinates.forEach(subordinate -> {

                subordinate.setSupervisor(null);

                employeeRepository.save(subordinate);
            });
    }
}
