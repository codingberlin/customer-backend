package com.example.backend;

import com.example.backend.controller.CreateSubCustomerRequest;
import com.example.backend.controller.SubCustomerRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
                                "Deutschland"
                        ))))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertThat(responseBody).contains("DEbroken");

    }

    @Test
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
        assertThat(createdSubCustomer.addressCountry()).isEqualTo("Deutschland");
    }
}