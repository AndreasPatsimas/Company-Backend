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
@Table(name = "Attribute")
public class Attribute {

    @Id
    @Column(name = "ATTR_ID")
    private String id;

    @Column(name = "ATTR_Name", nullable = false)
    private String name;

    @Column(name = "ATTR_Value", nullable = false)
    private String value;
}
