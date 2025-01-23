package com.example.clients.service;

import com.example.clients.dto.ClientResponse;
import com.example.clients.dto.ClientRequest;
import com.example.clients.entity.Client;
import com.example.clients.exceptions.Exceptions;
import com.example.clients.exceptions.NotFoundException;
import com.example.clients.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void saveClient(ClientRequest clientRequest) {
        Client client = newClient(clientRequest);
        try{
            clientRepository.save(client);
            LOGGER.info("El cliente: " + clientRequest.getName() + " " + clientRequest.getLastName() + " se ha almacenado correctamente");
        } catch (Exception e) {
            LOGGER.error("Error: el cliente: " + clientRequest.getName() + " " + clientRequest.getLastName() + " no puedo almacenarse");
            throw new Exceptions("Error: el cliente: " + clientRequest.getName() + " " + clientRequest.getLastName() + " no puedo almacenarse");
        }
    }

    @Override
    public List<ClientResponse> viewClients() throws NotFoundException{
        List<Client> clients = checkClients();
        return newClientResponseList(clients);
    }

    @Override
    public  Map<String, Double> viewKpiClient() throws NotFoundException {
        List<Client> clients = checkClients();
        double average = clients.stream().mapToInt(Client::getAge).average().orElse(0.0);
        double deviation = Math.sqrt(clients.stream()
                .mapToDouble(c -> Math.pow(c.getAge() - average, 2))
                .average().orElse(0.0));
        Map<String, Double> kpi = new HashMap<>();
        kpi.put("edad_promedio", average);
        kpi.put("edad_desviacion", deviation);
        LOGGER.info("El promedio y la desviacion de las edades de los clientes se calcularon correctamente");
        return kpi;
    }

    private List<Client> checkClients() {
        List<Client> clients = clientRepository.findAll();
        LOGGER.info("Se han encontrado " + clients.size() + " clientes");
        if (clients.isEmpty()) {
            throw new NotFoundException("No hay clientes registrados");
        }
        return clients;
    }

    private List<ClientResponse> newClientResponseList(List<Client> clients) {
        List<ClientResponse> clientResponses = new ArrayList<>();
        clients.forEach(c -> {
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setName(c.getName());
            clientResponse.setLastName(c.getLastName());
            clientResponse.setAge(c.getAge());
            clientResponse.setDateOfBirth(c.getDateOfBirth());
            clientResponse.setDateOfDeath(c.calculateDateOfDeath());
            clientResponses.add(clientResponse);
        });
        return clientResponses;
    }
    private Client newClient(ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setLastName(clientRequest.getLastName());
        client.setAge(clientRequest.getAge());
        client.setDateOfBirth(clientRequest.getDateOfBirth());
        return client;
    }
}
