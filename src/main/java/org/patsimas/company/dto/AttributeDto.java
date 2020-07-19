package org.patsimas.company.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDto {

    private String id;

    private String name;

    private String value;
}
