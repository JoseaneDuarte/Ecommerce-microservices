package com.ecommerce.storefront.controller;

import com.ecommerce.storefront.model.Produto;
import com.ecommerce.storefront.service.ProdutoService;
import com.ecommerce.storefront.service.EstoqueClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final EstoqueClient estoqueClient;

    // Construtor com injeção de dependência
    public ProdutoController(ProdutoService produtoService, EstoqueClient estoqueClient) {
        this.produtoService = produtoService;
        this.estoqueClient = estoqueClient;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }

    
    @GetMapping("/{id}/estoque")
    public Integer consultarEstoque(@PathVariable Long id) {
        return estoqueClient.consultarEstoque(id);
    }
}
