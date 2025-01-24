package com.example.clients.controller;

import com.example.clients.dto.ClientRequest;
import com.example.clients.dto.ClientResponse;
import com.example.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/creacliente")
    public ResponseEntity<?> createClient(@RequestBody ClientRequest client) throws Exception {
        clientService.saveClient(client);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listclientes")
    public ResponseEntity<List<ClientResponse>> getClients() {
        return ResponseEntity.ok(clientService.viewClients());
    }

    @GetMapping("/kpideclientes")
    public ResponseEntity<Map<String, Double>> getKpi() {
        return ResponseEntity.ok(clientService.viewKpiClient());
    }
}
