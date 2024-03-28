package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VatIdControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void postInvalidVatId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/validate/vat-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"vatId\":\"DE123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"isValid\":false}")));
    }

    @Test
    public void postNullIs400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/validate/vat-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void postEmptyIs400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/validate/vat-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}