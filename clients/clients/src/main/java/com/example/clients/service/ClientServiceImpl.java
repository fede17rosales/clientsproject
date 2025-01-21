package com.example.clients.service;

import com.example.clients.dto.ClientResponse;
import dto.ClientRequest;
import com.example.clients.entity.Client;
import com.example.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void saveClient(ClientRequest client) {
        Client clientEntity = new Client();
        clientEntity.setName(client.getName());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setAge(client.getAge());
        clientEntity.setDateOfBirth(client.getDateOfBirth());
        clientRepository.save(clientEntity);
    }

    @Override
    public List<ClientResponse> viewClients() {
        List<Client> clients = clientRepository.findAll();
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

    @Override
    public  Map<String, Double> viewKpiClient() {
        List<Client> clients = clientRepository.findAll();
        double average = clients.stream().mapToInt(Client::getAge).average().orElse(0.0);
        double deviation = Math.sqrt(clients.stream()
                .mapToDouble(c -> Math.pow(c.getAge() - average, 2))
                .average().orElse(0.0));
        Map<String, Double> kpi = new HashMap<>();
        kpi.put("edad_promedio", average);
        kpi.put("edad_desviacion", deviation);
        return kpi;
    }

}
