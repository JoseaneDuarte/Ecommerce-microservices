package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.model.Estoque;
import com.ecommerce.warehouse.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> buscarPorId(Long id) {
        return estoqueRepository.findById(id);
    }

    public Estoque buscarPorProdutoId(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId);
    }

    public Estoque salvar(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque atualizar(Long id, Estoque novoEstoque) {
        return estoqueRepository.findById(id)
            .map(estoque -> {
                estoque.setProdutoId(novoEstoque.getProdutoId());
                estoque.setQuantidade(novoEstoque.getQuantidade());
                return estoqueRepository.save(estoque);
            })
            .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    public void deletar(Long id) {
        estoqueRepository.deleteById(id);
    }
}
