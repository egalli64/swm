/*
 * Spring Boot Docker Microservices - Product Service
 * 
 * https://github.com/egalli64/dockerized-microservices
 */
package com.example.product;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    Exchange productExchange() {
        return new TopicExchange("product.exchange");
    }

    // To listen for user events (inter-service communication example)
    @Bean
    Queue productUserQueue() {
        return new Queue("product.user.queue", true);
    }

    @Bean
    Binding productUserBinding(Queue productUserQueue) {
        return BindingBuilder.bind(productUserQueue).to(new TopicExchange("user.exchange")).with("user.created");
    }
}
