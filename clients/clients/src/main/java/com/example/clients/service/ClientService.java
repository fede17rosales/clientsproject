package com.example.clients.service;

import com.example.clients.dto.ClientResponse;
import com.example.clients.dto.ClientRequest;
import com.example.clients.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

public interface ClientService {
    void saveClient(ClientRequest client) throws Exception;
    List<ClientResponse> viewClients() throws NotFoundException;

    Map<String, Double> viewKpiClient() throws NotFoundException;
}
