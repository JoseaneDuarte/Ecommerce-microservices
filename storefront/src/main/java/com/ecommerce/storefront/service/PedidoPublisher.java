package com.ecommerce.storefront.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

import com.ecommerce.storefront.dto.PedidoDTO;

@Service
public class PedidoPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PedidoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    public void enviarPedido(PedidoDTO pedidoDTO) {
        rabbitTemplate.convertAndSend(
            "pedido.exchange",
            "pedido.routingkey",
            pedidoDTO
        );
        System.out.println("Pedido publicado: " + pedidoDTO);
    }
}
