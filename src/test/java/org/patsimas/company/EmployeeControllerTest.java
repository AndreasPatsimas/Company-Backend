package org.patsimas.company;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.patsimas.company.dto.AttributeDto;
import org.patsimas.company.dto.EmployeeDto;
import org.patsimas.company.utils.Generator;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=EmployeeControllerTest",
        "spring.jmx.default-domain=EmployeeControllerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest extends BasicWiremockTest {

    private static final String EMPLOYEE_ID = Generator.generateId();

    @Test
    public void a_createNewEmployee() throws Exception {

        EmployeeDto newEmployee = EmployeeDto.builder()
                .id(EMPLOYEE_ID)
                .name("Andreas")
                .address("Agidos 8-10")
                .hasCar(true)
                .dateOfBirth(LocalDate.of(1993, 2, 16))
                .dateOfHire(Instant.now())
                .attributes(Arrays.asList(AttributeDto.builder()
                        .id("82FF24BB-0180-40F9-B68E-15799556A5C2")
                        .build()))
                .build();

        this.mockMvc.perform(post("/employees")
                .content(asJsonString(newEmployee)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void b_findAllEmployees() throws Exception {
        this.mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void c_findEmployeeById() throws Exception {
        this.mockMvc.perform(get("/employees/{id}", EMPLOYEE_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(EMPLOYEE_ID));
    }

    @Test
    public void d_deleteEmployee() throws Exception {
        this.mockMvc.perform(delete("/employees/{id}", "8CEE7A83-A9EB-4170-B7E8-5D4F0440C074"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
