package com.ecommerce.storefront.controller;

import com.ecommerce.storefront.dto.PedidoDTO;
import com.ecommerce.storefront.dto.ItemPedidoDTO;
import com.ecommerce.storefront.service.PedidoPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoTesteController {

    private final PedidoPublisher pedidoPublisher;

    public PedidoTesteController(PedidoPublisher pedidoPublisher) {
        this.pedidoPublisher = pedidoPublisher;
    }

    @PostMapping("/teste")
    public String criarPedidoTeste(@RequestParam Long clienteId) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setClienteId(clienteId);

        
        ItemPedidoDTO item1 = new ItemPedidoDTO();
        item1.setProdutoId(6L);
        item1.setQuantidade(1);

        
        ItemPedidoDTO item2 = new ItemPedidoDTO();
        item2.setProdutoId(7L);
        item2.setQuantidade(3);


        
        pedidoDTO.setItens(List.of(item1, item2));

        
        pedidoPublisher.enviarPedido(pedidoDTO);

        return "Pedido de teste enviado com cliente ID " + clienteId +
               " contendo produtos 6 e 7!";
    }
}
