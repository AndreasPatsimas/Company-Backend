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

    private Instant dateOfHire;

    private SupervisorDto supervisor;

}
