package com.ecommerce.storefront.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "pedido.exchange";
    public static final String QUEUE_NAME = "fila-pedido";
    public static final String ROUTING_KEY = "pedido.routingkey";

    @Bean
    public Queue filaPedido() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue filaPedido, DirectExchange exchange) {
        return BindingBuilder.bind(filaPedido).to(exchange).with(ROUTING_KEY);
    }
}
