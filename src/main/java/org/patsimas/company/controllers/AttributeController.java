package org.patsimas.company.controllers;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.company.dto.AttributeDto;
import org.patsimas.company.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/attributes")
@RestController
@Slf4j
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    /**
     * Retrieves all attributes
     *
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<AttributeDto> findAllAttributes() {

        log.info("Fetch all attributes");

        return attributeService.findAll();
    }

    /**
     * Retrieves the attribute specified by given id
     *
     * @param id the attribute id
     * @return the attribute specified by given id
     */
    @GetMapping("/{id}")
    AttributeDto fetchAttributeById(@PathVariable String id) {
        log.info("Fetch Attribute[id: {}]", id);
        return attributeService.fetchAttributeById(id);
    }

    /**
     * Inserts a new attribute in case given attributeDto does not provide an id
     * Updates an existing attribute in case given attributeDto provides an id
     *
     * @param attributeDto the attribute object to be saved
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity saveAttribute(@RequestBody AttributeDto attributeDto) {
        log.info("Save Attribute[{}]", attributeDto.toString());
        attributeService.save(attributeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Deletes the attribute specified by te given id
     *
     *
     * @param id the attribute id
     */
    @DeleteMapping("/{id}")
    ResponseEntity deleteAttribute(@PathVariable String id) {
        log.info("Delete Attribute with id [{}]", id);
        attributeService.deleteAttribute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
