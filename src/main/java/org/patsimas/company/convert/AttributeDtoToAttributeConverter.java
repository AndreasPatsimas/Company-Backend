package org.patsimas.company.convert;

import org.patsimas.company.domain.Attribute;
import org.patsimas.company.dto.AttributeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttributeDtoToAttributeConverter implements Converter<AttributeDto, Attribute> {
    @Override
    public Attribute convert(AttributeDto attributeDto) {

        return Attribute.builder()
                .id(attributeDto.getId())
                .name(attributeDto.getName())
                .value(attributeDto.getValue())
                .build();
    }
}
