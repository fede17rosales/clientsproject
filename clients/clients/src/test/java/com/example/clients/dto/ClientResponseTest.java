package com.example.clients.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientResponseTest {

    private final ObjectMapper objectMapper;

    public ClientResponseTest() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Registrar módulo para manejar LocalDate
    }

    @Test
    void testSerialization() throws JsonProcessingException {
        // Arrange
        ClientResponse clientResponse = new ClientResponse(
                "Federico",
                "Garcia",
                30,
                LocalDate.of(1993, 1, 1),
                LocalDate.of(2073, 1, 1)
        );

        // Act
        String json = objectMapper.writeValueAsString(clientResponse);

        // Assert
        String expectedJson = """
                {"nombre":"Federico","apellido":"Garcia","edad":30,"fecha_de_nacimiento":[1993,1,1],"esperanza_de_vida":[2073,1,1]}
                """;
        assertEquals(expectedJson.replaceAll("\\s", ""), json, "El JSON serializado no coincide con lo esperado.");
    }

    @Test
    void testDeserialization() throws JsonProcessingException {
        // Arrange
        String json = """
                {"nombre":"Maria","apellido":"Lopez","edad":25,"fecha_de_nacimiento":"1998-05-12","esperanza_de_vida":"2078-05-12"}
                """;

        // Act
        ClientResponse clientResponse = objectMapper.readValue(json, ClientResponse.class);

        // Assert
        assertNotNull(clientResponse, "El objeto deserializado no debe ser nulo.");
        assertEquals("Maria", clientResponse.getName(), "El nombre deserializado no coincide.");
        assertEquals("Lopez", clientResponse.getLastName(), "El apellido deserializado no coincide.");
        assertEquals(25, clientResponse.getAge(), "La edad deserializada no coincide.");
        assertEquals(LocalDate.of(1998, 5, 12), clientResponse.getDateOfBirth(), "La fecha de nacimiento deserializada no coincide.");
        assertEquals(LocalDate.of(2078, 5, 12), clientResponse.getDateOfDeath(), "La esperanza de vida deserializada no coincide.");
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        // Arrange
        ClientResponse clientResponse = new ClientResponse(
                "Juan",
                "Perez",
                40,
                LocalDate.of(1983, 7, 20),
                LocalDate.of(2063, 7, 20)
        );

        // Assert
        assertEquals("Juan", clientResponse.getName(), "El nombre no coincide.");
        assertEquals("Perez", clientResponse.getLastName(), "El apellido no coincide.");
        assertEquals(40, clientResponse.getAge(), "La edad no coincide.");
        assertEquals(LocalDate.of(1983, 7, 20), clientResponse.getDateOfBirth(), "La fecha de nacimiento no coincide.");
        assertEquals(LocalDate.of(2063, 7, 20), clientResponse.getDateOfDeath(), "La esperanza de vida no coincide.");
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        // Arrange
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setName("Laura");
        clientResponse.setLastName("Ramirez");
        clientResponse.setAge(28);
        clientResponse.setDateOfBirth(LocalDate.of(1995, 3, 15));
        clientResponse.setDateOfDeath(LocalDate.of(2075, 3, 15));

        // Assert
        assertEquals("Laura", clientResponse.getName(), "El nombre no coincide.");
        assertEquals("Ramirez", clientResponse.getLastName(), "El apellido no coincide.");
        assertEquals(28, clientResponse.getAge(), "La edad no coincide.");
        assertEquals(LocalDate.of(1995, 3, 15), clientResponse.getDateOfBirth(), "La fecha de nacimiento no coincide.");
        assertEquals(LocalDate.of(2075, 3, 15), clientResponse.getDateOfDeath(), "La esperanza de vida no coincide.");
    }

}