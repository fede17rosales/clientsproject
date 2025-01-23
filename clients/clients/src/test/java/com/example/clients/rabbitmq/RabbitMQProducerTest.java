package com.example.clients.rabbitmq;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class RabbitMQProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitMQProducer rabbitMQProducer;

    public RabbitMQProducerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        // Arrange
        String message = "Mensaje de prueba";
        String exchange = "exchange-name";
        String routingKey = "routing-key";

        // Act
        rabbitMQProducer.sendMessage(message);

        // Assert
        verify(rabbitTemplate).convertAndSend(exchange, routingKey, message);
    }
}