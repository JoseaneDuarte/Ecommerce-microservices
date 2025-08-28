package com.ecommerce.warehouse.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idProduto;
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo; // ENTRADA ou SAIDA

    private LocalDateTime dataMovimentacao;

    public MovimentacaoEstoque(Long id, Long idProduto, Integer quantidade, TipoMovimentacao tipo,
            LocalDateTime dataMovimentacao) {
        this.id = id;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataMovimentacao = dataMovimentacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    

    
}