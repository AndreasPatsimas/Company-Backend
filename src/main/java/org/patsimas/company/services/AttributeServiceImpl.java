package org.patsimas.company.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.company.domain.Attribute;
import org.patsimas.company.dto.AttributeDto;
import org.patsimas.company.exceptions.ResourceNotFoundException;
import org.patsimas.company.repositories.AttributeRepository;
import org.patsimas.company.repositories.EmployeeAttributeRepository;
import org.patsimas.company.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AttributeServiceImpl implements AttributeService {

    private AttributeRepository attributeRepository;

    private EmployeeAttributeRepository employeeAttributeRepository;

    private ConversionService conversionService;

    @Autowired
    public AttributeServiceImpl(AttributeRepository attributeRepository,
                                EmployeeAttributeRepository employeeAttributeRepository,
                                ConversionService conversionService) {
        this.attributeRepository = attributeRepository;
        this.employeeAttributeRepository = employeeAttributeRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<AttributeDto> findAll() {

        log.info("Find all attributes process start");

        List<Attribute> attributes = attributeRepository.findAll();

        List<AttributeDto> attributeDtoList = attributes
                .stream()
                .map(attribute -> conversionService.convert(attribute, AttributeDto.class))
                .collect(Collectors.toList());

        log.info("Find all attributes process end");

        return attributeDtoList;
    }

    @Override
    public AttributeDto fetchAttributeById(String id) {

        log.info("Fetch attribute[id: {}] start", id);

        Optional<Attribute> optionalAttribute = attributeRepository.findById(id);

        AttributeDto attributeDto = optionalAttribute
                .map(attribute -> conversionService.convert(attribute, AttributeDto.class))
                .orElse(null);

        if (ObjectUtils.isEmpty(attributeDto)) {
            log.error("Attribute [id: {}] cannot be found", id);
            throw new ResourceNotFoundException(MessageFormat.format("Attribute [id: {0}] does not exist", id));
        }

        log.info("Fetch attribute[id: {}] end", id);

        return attributeDto;
    }

    @Override
    public void save(AttributeDto attributeDto) {

        log.info("Save new attribute start");

        if (ObjectUtils.isEmpty(attributeDto)) {
            throw new ResourceNotFoundException("Attribute to be saved is empty");
        }
        // insert a new attribute in case id is not provided
        if (attributeDto.getId() == null){

            String id = Generator.generateId();

            attributeDto.setId(id);
        }

            Attribute attribute = conversionService.convert(attributeDto, Attribute.class);

            Attribute savedAttribute = attributeRepository.save(attribute);

            log.info("Save new attribute[id: {}] end", savedAttribute.getId());
    }

    @Override
    public void deleteAttribute(String id) {

        log.info("Delete attribute {}  process begins", id);

        Optional<Attribute> attributeOptional = attributeRepository.findById(id);

        attributeOptional.ifPresent(attribute -> {

            List<String> attributeList = employeeAttributeRepository.findAttributes(id);

            if (!attributeList.isEmpty())
                employeeAttributeRepository.deleteAttributeFromEmployees(id);

            attributeRepository.delete(attribute);
        });

        attributeOptional.orElseThrow(() -> new ResourceNotFoundException("Attribute to be deleted not exist"));

        log.info("Delete attribute process completed");
    }
}
