package com.example.clients.controller;

import com.example.clients.service.ClientService;
import com.example.clients.dto.ClientRequest;
import com.example.clients.dto.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/creacliente")
    public ResponseEntity<?> createClient(@RequestBody ClientRequest client) {
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
