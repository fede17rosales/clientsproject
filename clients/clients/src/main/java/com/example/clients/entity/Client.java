package com.example.clients.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private String lastName;
    private int age;
    private LocalDate dateOfBirth;

    public LocalDate calculateDateOfDeath() {
        return this.dateOfBirth.plusYears(80);
    }
}
