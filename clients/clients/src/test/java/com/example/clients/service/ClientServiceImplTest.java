package com.example.clients.service;

import com.example.clients.dto.ClientResponse;
import com.example.clients.dto.ClientRequest;
import com.example.clients.entity.Client;
import com.example.clients.exceptions.Exceptions;
import com.example.clients.exceptions.NotFoundException;
import com.example.clients.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;


    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClient() throws Exception {
        // Arrange
        ClientRequest clientRequest = new ClientRequest("Federico", "Garcia", 30, LocalDate.of(1993, 1, 1));
        Client clientEntity = new Client();
        clientEntity.setName("Federico");
        clientEntity.setLastName("Garcia");
        clientEntity.setAge(30);
        clientEntity.setDateOfBirth(LocalDate.of(1993, 1, 1));

        when(clientRepository.save(any(Client.class))).thenReturn(clientEntity);

        // Act
        clientService.saveClient(clientRequest);

        // Assert
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testSaveClientException() {
        // Arrange
        ClientRequest clientRequest = new ClientRequest("Federico", "Garcia", 30, LocalDate.of(1993, 1, 1));
        when(clientRepository.save(any(Client.class))).thenThrow(new RuntimeException("Error"));

        // Act and Assert
        assertThrows(Exceptions.class, () -> clientService.saveClient(clientRequest));
    }

    @Test
    void testViewClients() {
        // Arrange
        Client client1 = new Client();
        client1.setName("Federico");
        client1.setLastName("Garcia");
        client1.setAge(30);
        client1.setDateOfBirth(LocalDate.of(1993, 1, 1));

        Client client2 = new Client();
        client2.setName("Maria");
        client2.setLastName("Lopez");
        client2.setAge(25);
        client2.setDateOfBirth(LocalDate.of(1998, 5, 12));

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        // Act
        List<ClientResponse> clientResponses = clientService.viewClients();

        // Assert
        assertNotNull(clientResponses, "La lista de clientes no debe ser nula.");
        assertEquals(2, clientResponses.size(), "El tamaño de la lista no coincide.");
        assertEquals("Federico", clientResponses.get(0).getName(), "El nombre del primer cliente no coincide.");
        assertEquals("Maria", clientResponses.get(1).getName(), "El nombre del segundo cliente no coincide.");
    }

    @Test
    void testViewClientsWhenEmpty() {
        // Arrange
        when(clientRepository.findAll()).thenReturn(Arrays.asList());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> clientService.viewClients());
    }

    @Test
    void testViewKpiClient() {
        // Arrange
        Client client1 = new Client();
        client1.setName("Federico");
        client1.setLastName("Garcia");
        client1.setAge(30);

        Client client2 = new Client();
        client2.setName("Maria");
        client2.setLastName("Lopez");
        client2.setAge(25);

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        // Act
        Map<String, Double> kpi = clientService.viewKpiClient();

        // Assert
        assertNotNull(kpi, "El mapa de KPI no debe ser nulo.");
        assertEquals(27.5, kpi.get("edad_promedio"), 0.01, "La edad promedio no coincide.");
        assertEquals(2.5, kpi.get("edad_desviacion"), 0.01, "La desviación estándar no coincide.");
    }

}