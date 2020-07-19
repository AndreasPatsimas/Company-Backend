package org.patsimas.company.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String id;

    private String name;

    private String address;

    private boolean hasCar;

    private Instant dateOfHire;

    private SupervisorDto supervisor;

}
