package com.example.backend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SubCustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createCustomerWithInvalidVatId() throws Exception {
        final var responseBody = mvc.perform(MockMvcRequestBuilders.post("/sub-customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateSubCustomerRequest(
                                "DEbroken",
                                "Jane",
                                "Doe",
                                "Kommentar",
                                "Hauptstr.",
                                "1A",
                                "12345",
                                "Freiburg",
                                "Deutschland"
                        ))))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(responseBody).contains("DEbroken");

    }

    @Test
    @Sql("/db/truncate-all-tables.sql")
    public void createSubCustomer() throws Exception {
        final var createdSubCustomer = objectMapper.readValue(mvc.perform(MockMvcRequestBuilders.post("/sub-customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateSubCustomerRequest(
                                "DE999999999",
                                "Jane",
                                "Doe",
                                "Kommentar",
                                "Hauptstr.",
                                "1A",
                                "12345",
                                "Freiburg",
                                "Deutschland"
                        ))))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), SubCustomerRecord.class);

        assertThat(createdSubCustomer.id()).isNotNull();
        assertThat(createdSubCustomer.customerId()).isEqualTo(UUID.fromString("06fbb35a-eba7-4179-a856-62da9c3b9375"));
        assertThat(createdSubCustomer.taxId()).isEqualTo("DE999999999");
        assertThat(createdSubCustomer.firstName()).isEqualTo("Jane");
        assertThat(createdSubCustomer.lastName()).isEqualTo("Doe");
        assertThat(createdSubCustomer.comment()).isEqualTo("Kommentar");
        assertThat(createdSubCustomer.addressStreet()).isEqualTo("Hauptstr.");
        assertThat(createdSubCustomer.addressHouseNumber()).isEqualTo("1A");
        assertThat(createdSubCustomer.addressZipCode()).isEqualTo("12345");
        assertThat(createdSubCustomer.addressCity()).isEqualTo("Freiburg");
        assertThat(createdSubCustomer.addressCountry()).isEqualTo("Deutschland");
    }

    @Test
    @Sql({"/db/truncate-all-tables.sql", "/db/list-sub-customers.sql"})
    public void listSubCustomers() throws Exception {
        final var subCustomers = objectMapper.readValue(mvc.perform(MockMvcRequestBuilders.get("/sub-customers")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),

                new TypeReference<List<SubCustomerRecord>>() {
                });

        assertThat(subCustomers).hasSize(2);
        assertThat(subCustomers.getFirst().id().toString()).isEqualTo("af499571-75a5-4ab6-8403-42c5c5506a51");
    }
}