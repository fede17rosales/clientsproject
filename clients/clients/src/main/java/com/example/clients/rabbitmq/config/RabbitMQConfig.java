package com.example.clients.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue()
    {
        return QueueBuilder.durable("queue-name")
                .build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("exchange-name", true, false);
    }
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("routing-key");
    }
}
