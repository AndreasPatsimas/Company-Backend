package org.patsimas.company.convert;

import org.patsimas.company.domain.Attribute;
import org.patsimas.company.domain.Employee;
import org.patsimas.company.dto.AttributeDto;
import org.patsimas.company.dto.EmployeeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDtoToEmployeeConverter implements Converter<EmployeeDto, Employee> {
    @Override
    public Employee convert(EmployeeDto employeeDto) {

        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .address(employeeDto.getAddress())
                .hasCar(employeeDto.isHasCar() ? (short) 1 : (short) 0)
                .dateOfBirth(employeeDto.getDateOfBirth())
                .dateOfHire(employeeDto.getDateOfHire())
                .attributes(buildAttributes(employeeDto.getAttributes()))
                .build();
    }

    private List<Attribute> buildAttributes(List<AttributeDto> attributes){

        if (!ObjectUtils.isEmpty(attributes))
            return attributes
                    .stream()
                    .map(attribute -> Attribute.builder()
                            .id(attribute.getId())
                            .build())
                    .collect(Collectors.toList());

        return new ArrayList<>();
    }
}