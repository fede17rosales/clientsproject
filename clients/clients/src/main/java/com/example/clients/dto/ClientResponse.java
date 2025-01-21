package com.example.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    @JsonProperty("nombre")
    private String Name;
    @JsonProperty("apellido")
    private String lastName;
    @JsonProperty("edad")
    private int age;
    @JsonProperty("fecha_de_nacimiento")
    private LocalDate dateOfBirth;
    @JsonProperty("esperanza_de_vida")
    private LocalDate dateOfDeath;
}
