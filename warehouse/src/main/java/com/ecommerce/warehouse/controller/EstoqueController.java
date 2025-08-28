package com.ecommerce.warehouse.controller;

import com.ecommerce.warehouse.model.Estoque;
import com.ecommerce.warehouse.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public List<Estoque> listarTodos() {
        return estoqueService.listarTodos();
    }

    @GetMapping("/{id}")
    public Estoque buscarPorId(@PathVariable Long id) {
        return estoqueService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Estoque> consultarPorProdutoId(@PathVariable Long produtoId) {
        Estoque estoque = estoqueService.buscarPorProdutoId(produtoId);
        if (estoque != null) {
            return ResponseEntity.ok(estoque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Estoque criar(@RequestBody Estoque estoque) {
        return estoqueService.salvar(estoque);
    }

    @PutMapping("/{id}")
    public Estoque atualizar(@PathVariable Long id, @RequestBody Estoque estoque) {
        return estoqueService.atualizar(id, estoque);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        estoqueService.deletar(id);
    }
}
