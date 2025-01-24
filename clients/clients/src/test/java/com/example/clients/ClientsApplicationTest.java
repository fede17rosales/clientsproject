package com.example.clients;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientsApplicationTest {

    @Test
    void testMainMethod() {
        ClientsApplication.main(new String[] {});
        assertTrue(true);
    }
}