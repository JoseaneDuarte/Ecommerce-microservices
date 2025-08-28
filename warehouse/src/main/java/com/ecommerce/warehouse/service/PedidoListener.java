package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.dto.PedidoDTO;
import com.ecommerce.warehouse.model.Estoque;
import com.ecommerce.warehouse.repository.EstoqueRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoListener {

    private final EstoqueRepository estoqueRepository;

    public PedidoListener(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @RabbitListener(queues = "fila-pedido")
public void receberPedido(PedidoDTO pedido) {
    System.out.println("=== Pedido recebido do cliente " + pedido.getClienteId() + " ===");

    pedido.getItens().forEach(item -> {
        System.out.println("[DEBUG] Chegou item com produtoId=" + item.getProdutoId() +
                ", quantidade=" + item.getQuantidade());

        Estoque estoque = estoqueRepository.findByProdutoId(item.getProdutoId());
        if (estoque != null) {
            System.out.println("[DEBUG] Estoque encontrado! Antes: " + estoque.getQuantidade());

            if (estoque.getQuantidade() < item.getQuantidade()) {
                System.out.println("⚠ Estoque insuficiente para o produto " + item.getProdutoId() +
                        " — disponível: " + estoque.getQuantidade() +
                        ", solicitado: " + item.getQuantidade());
                
            } else {
                estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
                estoqueRepository.save(estoque);
                System.out.println("[DEBUG] Estoque depois: " + estoque.getQuantidade());
            }
        } else {
            System.out.println("[DEBUG] Nenhum estoque encontrado para produtoId=" + item.getProdutoId());
        }
    });
}

}

