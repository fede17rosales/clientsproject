package com.example.clients.service;

import com.example.clients.dto.ClientResponse;
import com.example.clients.dto.ClientRequest;

import java.util.List;
import java.util.Map;

public interface ClientService {
    void saveClient(ClientRequest client);
    List<ClientResponse> viewClients();

    Map<String, Double> viewKpiClient();
}
