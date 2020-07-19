package org.patsimas.company;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.patsimas.company.dto.AttributeDto;
import org.patsimas.company.utils.Generator;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=AttributeControllerTest",
        "spring.jmx.default-domain=AttributeControllerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AttributeControllerTest extends BasicWiremockTest {

    private static final String ATTRIBUTE_ID = Generator.generateId();

    @Test
    public void a_createNewAttribute() throws Exception {

        AttributeDto newAttribute = AttributeDto.builder()
                .id(ATTRIBUTE_ID)
                .name("Team")
                .value("Naos")
                .build();

        this.mockMvc.perform(post("/attributes")
                .content(asJsonString(newAttribute)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void b_findAllAttributes() throws Exception {
        this.mockMvc.perform(get("/attributes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void c_findAttributeById() throws Exception {
        this.mockMvc.perform(get("/attributes/{id}", ATTRIBUTE_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ATTRIBUTE_ID));
    }

    @Test
    public void d_deleteAttribute() throws Exception {
        this.mockMvc.perform(delete("/attributes/{id}", ATTRIBUTE_ID))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
