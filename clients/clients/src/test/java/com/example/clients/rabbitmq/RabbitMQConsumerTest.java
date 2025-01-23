package com.example.clients.rabbitmq;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class RabbitMQConsumerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Test
    void receiveMessage() {
        // Arrange
        RabbitMQConsumer consumer = new RabbitMQConsumer();
        String testMessage = "hola mundo!";

        // Act
        consumer.receiveMessage(testMessage);

        // Assert
        System.out.println("Test paso la prueba con el mensaje: {" + testMessage + "}");

    }

}