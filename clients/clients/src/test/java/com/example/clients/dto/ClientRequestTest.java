package com.example.clients.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import com.example.clients.dto.ClientRequest;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientRequestTest {
    private final ObjectMapper objectMapper;

    public ClientRequestTest() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testSerialization() throws JsonProcessingException {
        // Arrange
        ClientRequest clientRequest = new ClientRequest("Federico", "Garcia", 30, LocalDate.of(1993, 1, 1));

        // Act
        String json = objectMapper.writeValueAsString(clientRequest);

        // Assert
        String expectedJson = """
                {"nombre":"Federico","apellido":"Garcia","edad":30,"fecha_de_nacimiento":[1993,1,1]}
                """;
        assertEquals(expectedJson.replaceAll("\\s", ""), json, "El JSON serializado no coincide con lo esperado.");
    }

    @Test
    void testDeserialization() throws JsonProcessingException {
        // Arrange
        String json = """
                {"nombre":"Maria","apellido":"Lopez","edad":25,"fecha_de_nacimiento":"1998-05-12"}
                """;

        // Act
        ClientRequest clientRequest = objectMapper.readValue(json, ClientRequest.class);

        // Assert
        assertNotNull(clientRequest, "El objeto deserializado no debe ser nulo.");
        assertEquals("Maria", clientRequest.getName(), "El nombre deserializado no coincide.");
        assertEquals("Lopez", clientRequest.getLastName(), "El apellido deserializado no coincide.");
        assertEquals(25, clientRequest.getAge(), "La edad deserializada no coincide.");
        assertEquals(LocalDate.of(1998, 5, 12), clientRequest.getDateOfBirth(), "La fecha de nacimiento deserializada no coincide.");
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        // Arrange
        ClientRequest clientRequest = new ClientRequest("Juan", "Perez", 40, LocalDate.of(1983, 7, 20));

        // Assert
        assertEquals("Juan", clientRequest.getName(), "El nombre no coincide.");
        assertEquals("Perez", clientRequest.getLastName(), "El apellido no coincide.");
        assertEquals(40, clientRequest.getAge(), "La edad no coincide.");
        assertEquals(LocalDate.of(1983, 7, 20), clientRequest.getDateOfBirth(), "La fecha de nacimiento no coincide.");
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        // Arrange
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setName("Laura");
        clientRequest.setLastName("Ramirez");
        clientRequest.setAge(28);
        clientRequest.setDateOfBirth(LocalDate.of(1995, 3, 15));

        // Assert
        assertEquals("Laura", clientRequest.getName(), "El nombre no coincide.");
        assertEquals("Ramirez", clientRequest.getLastName(), "El apellido no coincide.");
        assertEquals(28, clientRequest.getAge(), "La edad no coincide.");
        assertEquals(LocalDate.of(1995, 3, 15), clientRequest.getDateOfBirth(), "La fecha de nacimiento no coincide.");
    }
}