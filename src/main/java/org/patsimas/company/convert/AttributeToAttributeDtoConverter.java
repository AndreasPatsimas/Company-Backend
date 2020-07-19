package org.patsimas.company.convert;

import org.patsimas.company.domain.Attribute;
import org.patsimas.company.dto.AttributeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttributeToAttributeDtoConverter implements Converter<Attribute, AttributeDto> {

    @Override
    public AttributeDto convert(Attribute attribute) {

        return AttributeDto.builder()
                .id(attribute.getId())
                .name(attribute.getName())
                .value(attribute.getValue())
                .build();
    }
}
