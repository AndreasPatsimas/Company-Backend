package org.patsimas.company.convert;

import org.patsimas.company.domain.Employee;
import org.patsimas.company.dto.EmployeeDto;
import org.patsimas.company.dto.SupervisorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class EmployeeToEmployeeDtoConverter implements Converter<Employee, EmployeeDto> {
    @Override
    public EmployeeDto convert(Employee employee) {

        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .dateOfHire(employee.getDateOfHire())
                .supervisor(!ObjectUtils.isEmpty(
                        employee.getSupervisor()) ? buildSuperVisor(employee.getSupervisor()) : null)
                .build();
    }

    private SupervisorDto buildSuperVisor(Employee supervisor){

        return SupervisorDto.builder()
                .id(supervisor.getId())
                .name(supervisor.getName())
                .build();
    }
}
