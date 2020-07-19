package org.patsimas.company.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@IdClass(EmployeeAttributeId.class)
@Table(name = "EmployeeAttribute")
public class EmployeeAttribute {

    @Id
    @Column(name = "EMPATTR_EmployeeID")
    private String employeeId;

    @Id
    @Column(name = "EMPATTR_AttributeID")
    private String attributeId;
}
