package com.ecommerce.storefront.service;

import com.ecommerce.storefront.model.Produto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DataInitializerService {

    private final ProdutoService produtoService;

    public DataInitializerService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostConstruct
    public void init() {
        if (produtoService.listarTodos().isEmpty()) {
            produtoService.salvar(new Produto(null, "Camiseta Branca", new BigDecimal("59.90")));
            produtoService.salvar(new Produto(null, "Calça Jeans", new BigDecimal("120.00")));
            produtoService.salvar(new Produto(null, "Tênis Esportivo", new BigDecimal("199.90")));
            produtoService.salvar(new Produto(null, "Boné Masculino", new BigDecimal("99.90")));
            produtoService.salvar(new Produto(null, "Óculos de sol", new BigDecimal("79.90")));

            System.out.println("📦 Produtos iniciais cadastrados no storefront.");
        }
    }
}
