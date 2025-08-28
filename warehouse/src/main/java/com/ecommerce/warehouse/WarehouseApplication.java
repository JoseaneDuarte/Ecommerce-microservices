package com.ecommerce.warehouse;

import com.ecommerce.warehouse.model.Estoque;
import com.ecommerce.warehouse.service.EstoqueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Bean
public CommandLineRunner inicializarEstoque(EstoqueService estoqueService) {
    return _ -> {
        estoqueService.salvar(new Estoque(1L, 10));
        estoqueService.salvar(new Estoque(2L, 5));
        estoqueService.salvar(new Estoque(3L, 8));
        estoqueService.salvar(new Estoque(4L, 12));
        estoqueService.salvar(new Estoque(5L, 7));
        estoqueService.salvar(new Estoque(6L, 3)); 
        estoqueService.salvar(new Estoque(7L, 1)); 
        
        
    };
}
}
