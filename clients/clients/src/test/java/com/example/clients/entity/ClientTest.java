package com.example.clients.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    @Test
    void testCalculateDateOfDeath() {
        // Arrange
        Client client = new Client();
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1); // Fecha de nacimiento
        client.setDateOfBirth(dateOfBirth);

        // Act
        LocalDate expectedDateOfDeath = dateOfBirth.plusYears(80);
        LocalDate actualDateOfDeath = client.calculateDateOfDeath();

        // Assert
        assertEquals(expectedDateOfDeath, actualDateOfDeath, "La fecha de muerte calculada no es correcta.");
    }

    @Test
    void testCalculateDateOfDeathForLeapYear() {
        // Arrange
        Client client = new Client();
        LocalDate dateOfBirth = LocalDate.of(2000, 2, 29); // Año bisiesto
        client.setDateOfBirth(dateOfBirth);

        // Act
        LocalDate expectedDateOfDeath = dateOfBirth.plusYears(80);
        LocalDate actualDateOfDeath = client.calculateDateOfDeath();

        // Assert
        assertEquals(expectedDateOfDeath, actualDateOfDeath, "La fecha de muerte para un año bisiesto no es correcta.");
    }


}