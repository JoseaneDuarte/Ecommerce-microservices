package com.ecommerce.storefront;

import com.ecommerce.storefront.model.Cliente;
import com.ecommerce.storefront.model.ItemPedido;
import com.ecommerce.storefront.model.Pedido;
import com.ecommerce.storefront.model.Produto;
import com.ecommerce.storefront.repository.ClienteRepository;
import com.ecommerce.storefront.repository.PedidoRepository;
import com.ecommerce.storefront.repository.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StorefrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorefrontApplication.class, args);
    }

    
    @Bean
    public CommandLineRunner inicializarClientes(ClienteRepository clienteRepository) {
        return _ -> {
            clienteRepository.save(new Cliente("Maria", "maria@email.com"));
            clienteRepository.save(new Cliente("João", "joao@email.com"));
            clienteRepository.save(new Cliente("Ana", "ana@email.com"));
            clienteRepository.save(new Cliente("Carlos", "carlos@email.com"));
            clienteRepository.save(new Cliente("Diego", "diego@email.com"));
        };
    }

    
    @Bean
    public CommandLineRunner inicializarPedidos(
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository,
            PedidoRepository pedidoRepository) {
        return _ -> {

            // Buscar clientes 
            Cliente cliente1 = clienteRepository.findById(1L).orElseThrow();
            Cliente cliente2 = clienteRepository.findById(2L).orElseThrow();

            // Buscar produtos existentes
            Produto produto1 = produtoRepository.findById(1L).orElseThrow();
            Produto produto2 = produtoRepository.findById(2L).orElseThrow();
            Produto produto3 = produtoRepository.findById(3L).orElseThrow();

            // === Pedido 1 ===
            Pedido pedido1 = new Pedido();
            pedido1.setCliente(cliente1);
            pedido1.setData(LocalDateTime.now());

            ItemPedido item1 = new ItemPedido();
            item1.setProduto(produto1);
            item1.setQuantidade(2);
            item1.setPrecoUnitario(produto1.getPreco());
            item1.setPedido(pedido1);

            ItemPedido item2 = new ItemPedido();
            item2.setProduto(produto3);
            item2.setQuantidade(1);
            item2.setPrecoUnitario(produto3.getPreco());
            item2.setPedido(pedido1);

            pedido1.setItens(List.of(item1, item2));

            // === Pedido 2 ===
            Pedido pedido2 = new Pedido();
            pedido2.setCliente(cliente2);
            pedido2.setData(LocalDateTime.now());

            ItemPedido item3 = new ItemPedido();
            item3.setProduto(produto2);
            item3.setQuantidade(5);
            item3.setPrecoUnitario(produto2.getPreco());
            item3.setPedido(pedido2);

            pedido2.setItens(List.of(item3));

            // Salva tudo 
            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);
        };
    }
}
