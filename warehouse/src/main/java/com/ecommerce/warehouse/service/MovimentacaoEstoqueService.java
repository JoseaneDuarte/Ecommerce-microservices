package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.model.MovimentacaoEstoque;
import com.ecommerce.warehouse.repository.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    public List<MovimentacaoEstoque> listarTodos() {
        return repository.findAll();
    }

    public Optional<MovimentacaoEstoque> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public MovimentacaoEstoque salvar(MovimentacaoEstoque movimentacao) {
        return repository.save(movimentacao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
