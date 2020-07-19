package org.patsimas.company.convert;

import org.patsimas.company.domain.Attribute;
import org.patsimas.company.domain.Employee;
import org.patsimas.company.dto.AttributeDto;
import org.patsimas.company.dto.EmployeeDto;
import org.patsimas.company.dto.SupervisorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeToEmployeeDtoConverter implements Converter<Employee, EmployeeDto> {
    @Override
    public EmployeeDto convert(Employee employee) {

        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .address(employee.getAddress())
                .hasCar(employee.getHasCar() == 1)
                .dateOfHire(employee.getDateOfHire())
                .supervisor(!ObjectUtils.isEmpty(
                        employee.getSupervisor()) ? buildSuperVisor(employee.getSupervisor()) : null)
                .attributes(buildAttributes(employee.getAttributes()))
                .build();
    }

    private SupervisorDto buildSuperVisor(Employee supervisor){

        return SupervisorDto.builder()
                .id(supervisor.getId())
                .name(supervisor.getName())
                .build();
    }

    private List<AttributeDto> buildAttributes(List<Attribute> attributes){

        return attributes
                .stream()
                .map(attribute -> AttributeDto.builder()
                        .id(attribute.getId())
                        .name(attribute.getName())
                        .value(attribute.getValue())
                        .build())
                .collect(Collectors.toList());
    }
}
