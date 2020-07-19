package org.patsimas.company.services;

import org.patsimas.company.dto.AttributeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttributeService {

    List<AttributeDto> findAll();

    AttributeDto fetchAttributeById(String id);

    void save(AttributeDto attributeDto);

    void deleteAttribute(String id);
}
