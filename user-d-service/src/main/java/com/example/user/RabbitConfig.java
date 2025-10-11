/*
 * Spring Boot Docker Microservices - User Service
 * 
 * https://github.com/egalli64/dockerized-microservices
 */
package com.example.user;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    Exchange userExchange() {
        return new TopicExchange("user.exchange");
    }
}