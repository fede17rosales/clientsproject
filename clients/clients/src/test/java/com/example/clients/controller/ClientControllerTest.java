package com.example.clients.controller;

import com.example.clients.dto.ClientResponse;
import com.example.clients.service.ClientService;
import com.example.clients.dto.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        Mockito.reset(clientService);
    }

    @Test
    void testCreateClient() throws Exception {
        // Arrange
        ClientRequest clientRequest = new ClientRequest("Federico", "Garcia", 30, LocalDate.of(1993, 1, 1));
        String json = """
                {
                    "nombre": "Federico",
                    "apellido": "Garcia",
                    "edad": 30,
                    "fecha_de_nacimiento": "1993-01-01"
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/creacliente")
                        .with(httpBasic("admin", "admin"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(clientService, times(1)).saveClient(clientRequest);
    }

    @Test
    void testGetClients() throws Exception {
        // Arrange
        List<ClientResponse> mockResponses = Arrays.asList(
                new ClientResponse("Federico", "Garcia", 30, LocalDate.of(1993, 1, 1), LocalDate.of(2073, 1, 1)),
                new ClientResponse("Maria", "Lopez", 25, LocalDate.of(1998, 5, 12), LocalDate.of(2078, 5, 12))
        );

        when(clientService.viewClients()).thenReturn(mockResponses);

        // Act & Assert
        mockMvc.perform(get("/listclientes")
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].nombre", is("Federico")))
                .andExpect(jsonPath("$[0].apellido", is("Garcia")))
                .andExpect(jsonPath("$[1].nombre", is("Maria")))
                .andExpect(jsonPath("$[1].apellido", is("Lopez")));

        verify(clientService, times(1)).viewClients();
    }

    @Test
    void testGetKpi() throws Exception {
        // Arrange
        Map<String, Double> mockKpi = new HashMap<>();
        mockKpi.put("edad_promedio", 27.5);
        mockKpi.put("edad_desviacion", 2.5);

        when(clientService.viewKpiClient()).thenReturn(mockKpi);

        // Act & Assert
        mockMvc.perform(get("/kpideclientes")
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.edad_promedio", is(27.5)))
                .andExpect(jsonPath("$.edad_desviacion", is(2.5)));

        verify(clientService, times(1)).viewKpiClient();
    }

}