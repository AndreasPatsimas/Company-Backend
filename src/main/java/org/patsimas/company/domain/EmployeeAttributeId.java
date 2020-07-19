package org.patsimas.company.domain;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeAttributeId implements Serializable {

    private String employeeId;
    private String attributeId;
}
