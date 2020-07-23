package org.patsimas.company.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String id;

    private String name;

    private String address;

    private boolean hasCar;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;

    private Instant dateOfHire;

    private SupervisorDto supervisor;

    private List<AttributeDto> attributes;

    private String attributesFormat;
}
