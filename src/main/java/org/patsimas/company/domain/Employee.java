package org.patsimas.company.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "EMP_ID")
    private String id;

    @Column(name = "EMP_Name", nullable = false)
    private String name;

    @Column(name = "EMP_Address", nullable = false)
    private String address;

    @Column(name = "EMP_Car", nullable = false)
    private Short hasCar;

    @Column(name = "EMP_DateOfHire", nullable = false)
    private Instant dateOfHire;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "EMP_Supervisor")
    private Employee supervisor;
}
