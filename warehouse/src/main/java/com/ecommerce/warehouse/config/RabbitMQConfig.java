package com.ecommerce.warehouse.config;

import com.ecommerce.warehouse.dto.PedidoDTO;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    // Configuração da fila, exchange e binding 
    @Bean
    public Queue pedidoQueue() {
        return new Queue("fila-pedido");
    }

    @Bean
    public DirectExchange pedidoExchange() {
        return new DirectExchange("pedido.exchange");
    }

    @Bean
    public Binding binding(Queue pedidoQueue, DirectExchange pedidoExchange) {
        return BindingBuilder.bind(pedidoQueue)
                             .to(pedidoExchange)
                             .with("pedido.routingkey");
    }

    // ClassMapper para mapear o TypeId do storefront para o DTO local 
    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.ecommerce.storefront.dto.PedidoDTO", PedidoDTO.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    // Conversor JSON com o classMapper 
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(DefaultClassMapper classMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper);
        return converter;
    }

    //  Integrar o conversor no listener 
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}
